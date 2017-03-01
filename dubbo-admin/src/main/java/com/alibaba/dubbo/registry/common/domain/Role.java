package com.alibaba.dubbo.registry.common.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/22.
 */
public class Role extends Entity {
    private static final long serialVersionUID = 2650022407106004318L;

    private String roleName;
    private String appPrivilege;
    private String managePrivilege;
    private String servicePrivilege;
    private String creatorId;
    private String creatorName;

    private List<String> servicePrivileges;
    private List<String> appPrivileges;
    private List<String> managePrivileges;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getServicePrivilege() {
        return servicePrivilege;
    }

    public void setServicePrivilege(String servicePrivilege) {
        this.servicePrivilege = servicePrivilege;
        if (servicePrivilege != null && servicePrivilege.length() > 0) {
            servicePrivileges = Arrays.asList(servicePrivilege.trim().split("\\s*,\\s*"));
        }
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
