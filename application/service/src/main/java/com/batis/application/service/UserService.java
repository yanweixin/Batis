package com.batis.application.service;

import com.batis.application.database.entity.management.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CommonService<User>, UserDetailsService {
    User findByUserName(String userName);

    int deleteByUserName(String userName);

    User updateById(Long userId, User user);

    User updateByUserName(String userName, User user);
}
