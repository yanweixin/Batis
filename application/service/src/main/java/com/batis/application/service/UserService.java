package com.batis.application.service;

import com.batis.application.database.entity.management.User;

public interface UserService extends CommonService<User> {
    User findByUserName(String userName);

    int deleteByUserName(String userName);

    User updateById(Long userId, User user);

    User updateByUserName(String userName, User user);
}
