package org.xgvela.oam.entity.alarm.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * UserRole entity
 * </p>
 */
@Data
public class UserRoleVO implements Serializable {

    private static final long serialVersionUID = -6428774508083268028L;

    private static final String ADMIN = "admin";
    private static final String SUPER_ADMIN = "superuser";

    @ApiModelProperty(value = "userLogin")
    private String userLogin;

    @ApiModelProperty(value = "userNiceName")
    private String userNiceName;

    @ApiModelProperty(value = "userRole")
    private String userRole;

    public void setUserRole(String userRole) {
        if (ADMIN.equals(userRole)) {
            this.userRole = SUPER_ADMIN;
        } else {
            this.userRole = userRole;
        }
    }
}
