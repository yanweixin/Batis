package com.batis.application.service.impl;

import com.batis.application.database.entity.management.User;
import com.batis.application.database.repository.jpa.management.UserRepository;
import com.batis.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Cacheable
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    @Caching(put = {@CachePut(key = "#p0"), @CachePut(key = "#result.userName")})
    public User updateById(Long userId, User user) {
        user.setId(userId);
        return save(user);
    }

    @Override
    @CacheEvict
    public int deleteById(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    @Cacheable
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    @Override
    public User updateByUserName(String userName, User user) {
        user.setId(findByUserName(userName).getId());
        return userRepository.save(user);
    }

    @Override
    @CacheEvict
    public int deleteByUserName(String userName) {
        User user = Objects.requireNonNull(findByUserName(userName));
        userRepository.delete(user);
        return 1;
    }

    @Override
    @Caching(put = {@CachePut(key = "#result.id"), @CachePut(key = "#result.userName")})
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }
}
