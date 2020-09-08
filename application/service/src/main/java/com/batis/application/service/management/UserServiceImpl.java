package com.batis.application.service.management;

import com.batis.application.database.entity.management.User;
import com.batis.application.database.repository.jpa.management.UserRepository;
import com.batis.application.database.repository.mongo.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MongoUserRepository mongoUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username is not present");
        }
        return user.get();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
//    @Caching(cacheable = {@Cacheable(key = "#result?.id", condition = "#result!=null"),
//            @Cacheable(key = "#result?.userName", condition = "#result!=null")})
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
//    @Caching(put = {@CachePut(key = "#p0"), @CachePut(key = "#result.userName")})
    public User updateById(Long userId, User user) {
        user.setId(userId);
        return userRepository.updateById(userId, user);
    }

    @Override
    public int deleteById(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
//    @Caching(cacheable = {@Cacheable(key = "#result?.id", condition = "#result!=null"),
//            @Cacheable(key = "#result?.userName", condition = "#result!=null")})
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    @Override
    public User updateByUserName(String userName, User user) {
        user.setId(findByUserName(userName).getId());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public int deleteByUserName(String userName) {
        User user = Objects.requireNonNull(findByUserName(userName));
        mongoUserRepository.delete(user);
        userRepository.delete(user);
        return 1;
    }

    @Override
    @Transactional
//    @Caching(put = {@CachePut(key = "#result.id"), @CachePut(key = "#result.userName")})
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        mongoUserRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public List<User> saveAll(List<User> users) {
        users.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
        userRepository.saveAll(users);
        mongoUserRepository.saveAll(users);
        return users;
    }
}
