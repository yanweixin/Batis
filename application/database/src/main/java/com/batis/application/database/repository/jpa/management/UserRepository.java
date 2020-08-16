package com.batis.application.database.repository.jpa.management;

import com.batis.application.database.entity.management.User;
import com.batis.application.database.repository.jpa.CustomQuery;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CustomQuery<User, Long> {

    Optional<User> findByUserName(String userName);

    List<User> findByDisplayName(String displayName);
}
