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
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class Roles extends Restful {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void index(Map<String, Object> context) {
        User user = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        List<Role> roleList = this.roleService.findAllRoles();
        context.put("roleList", roleList);
        context.put(WebConstants.CURRENT_USER_KEY, user);
    }

    public void add(Long id, Map<String, Object> context) {
        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        context.put(WebConstants.CURRENT_USER_KEY, userExtend);
    }

    public void edit(Long id, Map<String, Object> context) {
        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        Role role = this.roleService.findRoleById(id);
        context.put("role", role);
        context.put(WebConstants.CURRENT_USER_KEY, userExtend);
    }

    public boolean create(Role role, Map<String, Object> context) {
        role.setCreated(new Date());

        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        role.setCreatorId(userExtend.getId() + "");
        role.setCreatorName(userExtend.getUsername());
        boolean success = this.roleService.addRole(role);
        if (success) {
            context.put("message", "<font color='green' size='3'>角色添加成功</font>");
            context.put("redirect", "/account/roles");
        } else {
            context.put("message", "<font color='red' size='3'>角色添加失败</font>");
        }
        return success;
    }

    public boolean modify(Role role, Map<String, Object> context) {
        role.setModified(new Date());

        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        role.setCreatorId(userExtend.getId() + "");
        role.setCreatorName(userExtend.getUsername());
        boolean success = this.roleService.updateRoleById(role, role.getId());
        if (success) {
            context.put("message", "<font color='green' size='3'>修改角色信息成功</font>");
            context.put("redirect", "/account/roles");
        } else {
            context.put("message", "<font color='red' size='3'>修改角色信息失败</font>");
        }
        return success;
    }

    public void privileges(Map<String, Object> context) {
        String uids = request.getParameter("uids");
        String rids = request.getParameter("rids");

        UserExtend userExtend = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        if (("," + uids + ",").indexOf("," + userExtend.getId() + ",") > -1) {
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
            context.put("redirect", "/account/roles");
        } else {
            context.put("message", "<font color='red' size='3'>权限修改失败</font>");
        }
        return success;
    }

    public boolean enable(Long[] ids, Map<String, Object> context) {
        Role role = new Role();
        role.setStatus("1");
        boolean success = this.roleService.updateRoleByIds(role, Arrays.asList(ids));
        if (success) {
            context.put("message", "<font color='green' size='3'>角色启用成功</font>");
            context.put("redirect", "/account/roles");
        } else {
            context.put("message", "<font color='red' size='3'>角色启用失败</font>");
        }
        return success;
    }

    public boolean disable(Long[] ids, Map<String, Object> context) {
        Role role = new Role();
        role.setStatus("0");
        boolean success = this.roleService.updateRoleByIds(role, Arrays.asList(ids));
        if (success) {
            context.put("message", "<font color='green' size='3'>角色禁用成功</font>");
            context.put("redirect", "/account/roles");
        } else {
            context.put("message", "<font color='red' size='3'>角色禁用失败</font>");
        }
        return success;
    }

    public boolean delete(Long[] ids, Map<String, Object> context) {
        boolean success = this.roleService.deleteRoleByIds(Arrays.asList(ids));
        if (success) {
            context.put("message", "<font color='green' size='3'>角色删除成功</font>");
            context.put("redirect", "/account/roles");
        } else {
            context.put("message", "<font color='red' size='3'>角色删除失败</font>");
        }
        return success;
    }
}
