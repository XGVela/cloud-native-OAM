package org.xgvela.oam.entity.alarm.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleVO implements Serializable {

    private static final long serialVersionUID = -6428774508083268028L;

    private static final String ADMIN = "admin";
    private static final String SUPER_ADMIN = "super administrator ";

    @ApiModelProperty(value = "User unique identifier ")
    private String userLogin;

    @ApiModelProperty(value = "user nickname ")
    private String userNiceName;

    @ApiModelProperty(value = "User role ")
    private String userRole;

    public void setUserRole(String userRole) {
        if (ADMIN.equals(userRole)) {
            this.userRole = SUPER_ADMIN;
        } else {
            this.userRole = userRole;
        }
    }
}