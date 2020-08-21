package com.batis.application.database.entity.management;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Document(collection = "user")
public class User extends Person {
    @NotNull
    @Column(unique = true)
    @NaturalId
    private String userName;

    @Override
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return super.getId();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
