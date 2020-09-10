package com.batis.application.web.controller.management;

import com.batis.application.database.entity.management.User;
import com.batis.application.service.management.UserService;
import com.batis.application.service.system.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;

    @PutMapping("/password")
    public ResponseEntity<Object> updatePassword(@RequestBody Map<String, String> map) {
        String userId = map.get("id");
        String oldPassword = map.get("old");
        String newPassword = map.get("new");
        if (Stream.of(userId, oldPassword, newPassword).anyMatch(Objects::isNull)) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.findById(Long.parseLong(userId));
        if (user != null && securityService.matches(oldPassword, user.getPassword()) && securityService.checkPassword(newPassword)) {
            user.setPassword(newPassword);
            userService.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/email")
    public int updateEmail(@RequestBody Map<String, String> map) {
        return 0;
    }

    @PutMapping("/phonenumber")
    public int updatePhoneNumber(@RequestBody Map<String, String> map) {
        return 0;
    }
}
