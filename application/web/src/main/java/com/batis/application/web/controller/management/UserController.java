package com.batis.application.web.controller.management;

import com.batis.application.database.entity.management.User;
import com.batis.application.service.management.UserService;
import com.batis.library.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiVersion(1)
@RestController
@RequestMapping("management/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public Page<User> getPagedUser(Pageable pageable) {
//        Sort sort = Sort.by("id").ascending();
//        Sort sort = Sort.sort(User.class).by(User::getId).ascending();
        return userService.findAll(pageable);
    }

    @GetMapping("/u/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/u/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        return userService.updateById(id, user);
    }

    @DeleteMapping("/u/{id}")
    public int deleteUserById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/{username}")
    public User getUserByUserName(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PutMapping("/{username}")
    public User updateUserByUserName(@PathVariable String username, @RequestBody User user) {
        return userService.updateByUsername(username, user);
    }

    @DeleteMapping("/{username}")
    public int deleteByUserName(@PathVariable String username) {
        return userService.deleteByUsername(username);
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * Only used for test, use {@link #addUser} instead in production.
     *
     * @param users
     * @return
     */
    @Deprecated
    @PostMapping("/multiple")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userService.saveAll(users);
    }

}
