package com.batis.application.service.management;

import com.batis.application.database.entity.management.User;
import com.batis.application.service.CommonService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CommonService<User>, UserDetailsService {
    User findByUsername(String username);

    int deleteByUsername(String username);

    User updateById(Long userId, User user);

    User updateByUsername(String username, User user);
}
