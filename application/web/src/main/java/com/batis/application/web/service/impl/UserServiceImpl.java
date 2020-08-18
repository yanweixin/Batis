//package com.batis.application.web.service.impl;
//
//import com.batis.application.database.entity.management.User;
//import com.batis.application.database.repository.jpa.management.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserDetailsService {
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByUserName(username);
//        if (optionalUser.isEmpty()){
//            throw new UsernameNotFoundException("用户名未找到");
//        }
//        return null;
//    }
//}
