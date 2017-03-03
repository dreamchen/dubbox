package com.alibaba.dubbo.registry.common.domain;

import com.alibaba.dubbo.registry.common.route.ParseUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/20.
 */
public class UserExtend extends User {
    private static final long serialVersionUID = -667603800401465441L;

    private String roleId;
    private String roleName;
    private String appPrivilege;
    private String managePrivilege;

    private List<String> appPrivileges;
    private List<String> managePrivileges;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAppPrivilege() {
        return appPrivilege;
    }

    public void setAppPrivilege(String appPrivilege) {
        this.appPrivilege = appPrivilege;
        if (appPrivilege != null && appPrivilege.length() > 0) {
            appPrivileges = Arrays.asList(appPrivilege.trim().split("\\s*,\\s*"));
        }
    }

    public String getManagePrivilege() {
        return managePrivilege;
    }

    public void setManagePrivilege(String managePrivilege) {
        this.managePrivilege = managePrivilege;
        if (managePrivilege != null && managePrivilege.length() > 0) {
            managePrivileges = Arrays.asList(managePrivilege.trim().split("\\s*,\\s*"));
        }
    }

    public boolean hasAppPrivilege(String application) {
        if (application == null || application.length() == 0)
            return false;
        if (managePrivilege == null || managePrivilege.equalsIgnoreCase(GUEST)) {
            return false;
        }
        if (managePrivilege.indexOf(ROOT) > -1) {
            return true;
        }

        if (appPrivileges != null && appPrivileges.size() > 0) {
            for (String privilege : appPrivileges) {
                boolean ok = ParseUtils.isMatchGlobPattern(privilege, application);
                if (ok) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasServicePrivilege(String service) {
        if (service == null || service.length() == 0)
            return false;
        if (managePrivilege == null || managePrivilege.equalsIgnoreCase(GUEST)) {
            return false;
        }
        if (managePrivilege.indexOf(ROOT) > -1) {
            return true;
        }

        if (servicePrivileges != null && servicePrivileges.size() > 0) {
            for (String privilege : servicePrivileges) {
                boolean ok = ParseUtils.isMatchGlobPattern(privilege, service);
                if (ok) {
                    return true;
                }
            }
        }
        return false;
    }
}
