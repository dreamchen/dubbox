package com.alibaba.dubbo.governance.web.account.module.screen;

import com.alibaba.dubbo.governance.service.RoleService;
import com.alibaba.dubbo.governance.service.UserService;
import com.alibaba.dubbo.governance.web.common.module.screen.Restful;
import com.alibaba.dubbo.governance.web.common.pulltool.Tool;
import com.alibaba.dubbo.governance.web.util.WebConstants;
import com.alibaba.dubbo.registry.common.domain.UserExtend;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Infos extends Restful {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void index(Map<String, Object> context) {
        com.alibaba.dubbo.registry.common.domain.User user = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        context.put(WebConstants.CURRENT_USER_KEY, user);
    }

    public void edit(Long id, Map<String, Object> context) {
        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        context.put(WebConstants.CURRENT_USER_KEY, userExtend);
    }

    public void passwds(Long id, Map<String, Object> context) {
        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        context.put(WebConstants.CURRENT_USER_KEY, userExtend);
    }


    public boolean modify(UserExtend userExtend, Map<String, Object> context) {
        boolean success = this.userService.modifyUser(userExtend);
        if (success) {
            Tool tool = new Tool();
            tool.setUserService(userService);
            tool.setRoleService(roleService);
            com.alibaba.dubbo.registry.common.domain.User user = tool.getUserWithAllPrivilege(userExtend.getId());
            request.getSession().setAttribute(WebConstants.CURRENT_USER_KEY, user);

            context.put("message", "修改个人信息成功");
            context.put("redirect", "/account/infos");
        } else {
            context.put("message", "修改个人信息失败");
        }
        return success;
    }
}
