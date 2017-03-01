package com.alibaba.dubbo.governance.web.account.module.screen;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.governance.service.RoleService;
import com.alibaba.dubbo.governance.service.UserService;
import com.alibaba.dubbo.governance.web.common.module.screen.Restful;
import com.alibaba.dubbo.governance.web.common.pulltool.Tool;
import com.alibaba.dubbo.governance.web.util.WebConstants;
import com.alibaba.dubbo.registry.common.domain.Role;
import com.alibaba.dubbo.registry.common.domain.User;
import com.alibaba.dubbo.registry.common.domain.UserExtend;
import com.alibaba.dubbo.registry.common.util.Coder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class Manage extends Restful {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void index(Map<String, Object> context) {
        User user = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        List<UserExtend> userList = this.userService.findAllUsers();
        context.put("userList", userList);
        context.put(WebConstants.CURRENT_USER_KEY, user);
    }

    public void add(Long id, Map<String, Object> context) {
        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        context.put(WebConstants.CURRENT_USER_KEY, userExtend);
    }

    public void roles(Map<String, Object> context) {
        String ids = request.getParameter("ids");
        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        if (("," + ids + ",").indexOf("," + userExtend.getId() + ",") > -1) {

            Tool tool = new Tool();
            tool.setUserService(userService);
            tool.setRoleService(roleService);
            userExtend = tool.getUserWithAllPrivilege(userExtend.getId());

            request.getSession().setAttribute(WebConstants.CURRENT_USER_KEY, userExtend);
        }

        List<Role> roleList = this.roleService.findAllRoles();
        context.put("ids", ids);
        context.put("roleList", roleList);
        context.put(WebConstants.CURRENT_USER_KEY, userExtend);
    }

    public boolean editRoles(Map<String, Object> context) {
        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        String idsStr = request.getParameter("ids");
        String roleIds = request.getParameter("roleIds");
        String roleNames = request.getParameter("roleNames");
        List<Long> ids = new ArrayList<Long>();
        for (String id : idsStr.split(",")) {
            ids.add(Long.parseLong(id));
        }
        UserExtend userDomain = new UserExtend();
        userDomain.setRoleId(StringUtils.isBlank(roleIds) ? "" : roleIds);
        userDomain.setRoleName(StringUtils.isBlank(roleNames) ? "" : roleNames);
        userDomain.setModified(new Date());
        userDomain.setOperator(userExtend.getUsername());
        boolean success = this.userService.updateUser(userDomain, ids);
        if (success) {
            context.put("message", "<font color='green' size='3'>权限修改成功</font>");
            context.put("redirect", "/account/manage");
        } else {
            context.put("message", "<font color='red' size='3'>权限修改失败</font>");
        }
        return success;
    }

    public void privileges(Map<String, Object> context) {
        String uids = request.getParameter("uids");
        String rids = request.getParameter("rids");

        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        if (("," + uids + ",").indexOf("," + userExtend.getId() + ",") > -1) {
            userExtend = this.userService.findById(userExtend.getId());

            Tool tool = new Tool();
            tool.setUserService(userService);
            tool.setRoleService(roleService);
            userExtend = tool.getUserWithAllPrivilege(userExtend.getId());
            request.getSession().setAttribute(WebConstants.CURRENT_USER_KEY, userExtend);
        }

        if (!StringUtils.isBlank(uids)) {
            context.put("uids", uids);
            if (uids.indexOf(",") < 0) {
                UserExtend targetUser = this.userService.findById(Long.parseLong(uids));
                context.put("targetUser", targetUser);
            }
        }
        if (!StringUtils.isBlank(rids)) {
            context.put("rids", rids);
            if (rids.indexOf(",") < 0) {
                Role targetRole = this.roleService.findRoleById(Long.parseLong(rids));
                context.put("targetRole", targetRole);
            }
        }
        context.put(WebConstants.CURRENT_USER_KEY, userExtend);
    }

    public boolean editPrivileges(Map<String, Object> context) {
        String uids = request.getParameter("uids");
        String rids = request.getParameter("rids");

        String appPrivileges = request.getParameter("appPrivileges");
        String servicePrivileges = request.getParameter("servicePrivileges");
        String managePrivileges = request.getParameter("managePrivileges");

        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);

        boolean success = false;
        List<Long> ids = new ArrayList<Long>();
        if (!StringUtils.isBlank(uids)) {
            for (String id : uids.split(",")) {
                ids.add(Long.parseLong(id));
            }
            UserExtend targetUser = new UserExtend();
            targetUser.setAppPrivilege(StringUtils.isBlank(appPrivileges) ? "" : appPrivileges);
            targetUser.setServicePrivilege(StringUtils.isBlank(servicePrivileges) ? "" : servicePrivileges);
            targetUser.setManagePrivilege(StringUtils.isBlank(managePrivileges) ? "" : managePrivileges);
            targetUser.setModified(new Date());
            targetUser.setOperator(userExtend.getUsername());
            success = this.userService.updateUser(targetUser, ids);
        }
        if (!StringUtils.isBlank(rids)) {
            for (String id : rids.split(",")) {
                ids.add(Long.parseLong(id));
            }
            Role targetRole = new Role();
            targetRole.setAppPrivilege(StringUtils.isBlank(appPrivileges) ? "" : appPrivileges);
            targetRole.setServicePrivilege(StringUtils.isBlank(servicePrivileges) ? "" : servicePrivileges);
            targetRole.setManagePrivilege(StringUtils.isBlank(managePrivileges) ? "" : managePrivileges);
            targetRole.setModified(new Date());
            targetRole.setOperator(userExtend.getUsername());
            success = this.roleService.updateRoleByIds(targetRole, ids);
        }

        if (success) {
            context.put("message", "<font color='green' size='3'>权限修改成功</font>");
            context.put("redirect", "/account/manage");
        } else {
            context.put("message", "<font color='red' size='3'>权限修改失败</font>");
        }
        return success;
    }

    public void passwds(Long id, Map<String, Object> context) {
        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        context.put(WebConstants.CURRENT_USER_KEY, userExtend);
    }


    public boolean create(UserExtend userExtend, Map<String, Object> context) {
        String passwordDigest = Coder.encodeMd5(userExtend.getUsername() + ":" + User.REALM + ":123456");
        userExtend.setPassword(passwordDigest);
        userExtend.setCreated(new Date());

//        User user = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
//        userExtend.setCreator(user.getUsername());
        boolean success = this.userService.createUser(userExtend);
        if (success) {
            context.put("message", "<font color='green' size='3'>用户添加成功</font>");
            context.put("redirect", "/account/manage");
        } else {
            context.put("message", "<font color='red' size='3'>用户添加失败</font>");
        }
        return success;
    }

    public boolean modify(UserExtend userExtend, Map<String, Object> context) {
        boolean success = this.userService.modifyUser(userExtend);
        if (success) {
            Tool tool = new Tool();
            tool.setUserService(userService);
            tool.setRoleService(roleService);
            User user = tool.getUserWithAllPrivilege(userExtend.getId());
            request.getSession().setAttribute(WebConstants.CURRENT_USER_KEY, user);

            context.put("message", "<font color='green' size='3'>修改个人信息成功</font>");
            context.put("redirect", "/account/infos");
        } else {
            context.put("message", "<font color='red' size='3'>修改个人信息失败</font>");
        }
        return success;
    }

    public boolean enable(Long[] ids, Map<String, Object> context) {
        UserExtend userExtend = new UserExtend();
        userExtend.setStatus("1");
        boolean success = this.userService.updateUser(userExtend, Arrays.asList(ids));
        if (success) {
            context.put("message", "<font color='green' size='3'>用户启用成功</font>");
            context.put("redirect", "/account/manage");
        } else {
            context.put("message", "<font color='red' size='3'>用户启用失败</font>");
        }
        return success;
    }

    public boolean disable(Long[] ids, Map<String, Object> context) {
        UserExtend userExtend = new UserExtend();
        userExtend.setStatus("0");
        boolean success = this.userService.updateUser(userExtend, Arrays.asList(ids));
        if (success) {
            context.put("message", "<font color='green' size='3'>用户禁用成功</font>");
            context.put("redirect", "/account/manage");
        } else {
            context.put("message", "<font color='red' size='3'>用户禁用失败</font>");
        }
        return success;
    }

    public boolean delete(Long[] ids, Map<String, Object> context) {
        boolean success = this.userService.deleteUserByIds(Arrays.asList(ids));
        if (success) {
            context.put("message", "<font color='green' size='3'>用户删除成功</font>");
            context.put("redirect", "/account/manage");
        } else {
            context.put("message", "<font color='red' size='3'>用户删除失败</font>");
        }
        return success;
    }
}
