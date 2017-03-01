package com.alibaba.dubbo.governance.web.account.module.screen;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.governance.service.UserService;
import com.alibaba.dubbo.governance.web.common.module.screen.Restful;
import com.alibaba.dubbo.governance.web.util.WebConstants;
import com.alibaba.dubbo.registry.common.domain.User;
import com.alibaba.dubbo.registry.common.domain.UserExtend;
import com.alibaba.dubbo.registry.common.util.Coder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Passwds extends Restful {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    public void index(Map<String, Object> context) {
        User user = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        context.put(WebConstants.CURRENT_USER_KEY, user);
    }

    public boolean create(Map<String, Object> context) {
        User user = new User();
        user.setOperator(operator);
        user.setOperatorAddress(operatorAddress);
        user.setPassword((String) context.get("newPassword"));
        user.setUsername(operator);

        boolean sucess = false;//userService.updatePassword(user, (String) context.get("oldPassword"));
        if (!sucess)
            context.put("message", getMessage("passwd.oldwrong"));
        return sucess;
    }

    public boolean resetPwd(Long[] ids, Map<String, Object> context) {

        String failList = "";
        for (Long id : ids) {
            UserExtend userExtend = this.userService.findById(id);
            if (userExtend == null) {
                failList += id + ",";
                continue;
            }
            String passwordDigest = Coder.encodeMd5(userExtend.getUsername() + ":" + User.REALM + ":123456");
            userExtend = new UserExtend();
            userExtend.setPassword(passwordDigest);
            boolean bool = this.userService.updatePassword(userExtend, id);
            if (!bool) {
                failList += id + ",";
            }
        }
        String[] failedStr = StringUtils.isBlank(failList) ? new String[]{} : failList.split(",");
        context.put("message", "<font color='green' size='3'>初始化密码</font><br/><font color='blue' size='3'>成功:" + (ids.length - failedStr.length) + "</font><font color='red' size='3'>失败:" + failedStr.length + "</font>");
        context.put("redirect", "/account/manage");
        return true;
    }

    public boolean changePwd(Long id, Map<String, Object> context) {
        String oldPwd = (String) context.get("oldPwd");

        String newPwd = (String) context.get("newPwd");
        String rePwd = (String) context.get("rePwd");
        if (!newPwd.equals(rePwd)) {
            context.put("message", "<font color='red' size='3'>密码输入不一致！</font>");
            context.put("redirect", "/account/infos/passwds");
            return false;
        }

        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        String oldPasswordDigest = Coder.encodeMd5(userExtend.getUsername() + ":" + User.REALM + ":" + oldPwd);
        if (userExtend == null || userExtend.getId() != id) {
            context.put("message", "<font color='red' size='3'>请重新登录！</font>");
            context.put("redirect", "/logout");
            return false;
        } else if (!userExtend.getPassword().equals(oldPasswordDigest)) {
            context.put("message", "<font color='red' size='3'>旧密码输入错误！</font>");
            context.put("redirect", "/account/infos/passwds");
            return false;
        } else {
            String newPasswordDigest = Coder.encodeMd5(userExtend.getUsername() + ":" + User.REALM + ":" + oldPwd);
            userExtend = new UserExtend();
            userExtend.setPassword(newPasswordDigest);
        }

        boolean sucess = userService.updatePassword(userExtend, id);
        if (!sucess) {
            context.put("message", "<font color='red' size='3'>" + getMessage("passwd.oldwrong") + "</font>");
            context.put("redirect", "/account/infos/passwds");
        } else {
            context.put("message", "<font color='green' size='3'>密码修改成功！</font>");
            context.put("redirect", "/logout");
        }
        return sucess;
    }

}
