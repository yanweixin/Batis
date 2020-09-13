package com.batis.application.database.entity.management;

import com.batis.application.database.entity.base.BaseEntity;
import com.batis.application.database.entity.system.MultiLangText;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Role extends BaseEntity {
    @NotNull
    @Column(unique = true)
    private String roleCode;

    @NotNull
    private String roleName;

    /**
     * Refer to {@link MultiLangText#getTextId()}
     */
    private Integer description;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }
}
