package com.batis.application.controller.management;

import com.batis.application.entity.management.User;
import com.batis.application.repository.management.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @PostMapping("/")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userRepository.saveAll(users);
    }

}
