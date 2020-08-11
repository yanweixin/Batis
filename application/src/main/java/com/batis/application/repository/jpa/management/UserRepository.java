package com.batis.application.repository.jpa.management;

import com.batis.application.entity.management.User;
import com.batis.library.jpa.CustomQuery;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CustomQuery<User, Long> {

    Optional<User> findByUserCode(String userCode);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
