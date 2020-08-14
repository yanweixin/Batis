package com.batis.application.database.entity.management;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User extends Person {
    @NotNull
    @Column(unique = true)
    private String userCode;

    @NotNull
    private String userName;

    @Override
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return super.getId();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
