package com.batis.application.web.controller.management;

import com.batis.application.database.entity.management.User;
import com.batis.application.database.repository.jpa.management.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public Page<User> getPagedUser(Pageable pageable) {
//        Sort sort = Sort.by("id").ascending();
//        Sort sort = Sort.sort(User.class).by(User::getId).ascending();
        return userRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/{userName}")
    public Optional<User> getUserByUserName(@PathVariable String userName) {
        return userRepository.findByUserName(userName);
    }

    @PostMapping("/")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userRepository.saveAll(users);
    }

}
