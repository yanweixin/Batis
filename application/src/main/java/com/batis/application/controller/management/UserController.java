package com.batis.application.controller.management;

import com.batis.application.entity.management.User;
import com.batis.application.repository.management.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("management/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public List<User> getUserList(){
        return userRepository.findAll();
    }

//    @GetMapping("/")
//    public List<User> findUserByUserCodeAndName(){
//
//    }
}
