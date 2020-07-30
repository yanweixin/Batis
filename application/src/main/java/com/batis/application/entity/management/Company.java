package com.batis.application.entity.management;

import com.batis.application.entity.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Company extends BaseEntity {

    @NotNull
    private String companyCode;

    @NotNull
    private String companyName;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
