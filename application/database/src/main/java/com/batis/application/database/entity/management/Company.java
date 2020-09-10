package com.batis.application.database.entity.management;

import com.batis.application.database.entity.base.BaseEntity;
import com.batis.application.database.entity.system.Country;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Company extends BaseEntity {

    @NotNull
    private String companyCode;

    @NotNull
    private String companyName;

    @ManyToOne
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
