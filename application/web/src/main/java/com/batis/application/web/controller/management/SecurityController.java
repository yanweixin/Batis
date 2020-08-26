package com.batis.application.web.controller.management;

import com.batis.application.database.entity.management.User;
import com.batis.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    PasswordEncoder passwordEncoder;

    @PutMapping("/password")
    public ResponseEntity<Object> updatePassword(@RequestBody Map<String, String> map) {
        String userId = map.get("id");
        String oldPassword = map.get("old");
        String newPassword = map.get("new");
        if (Stream.of(userId, oldPassword, newPassword).anyMatch(Objects::isNull)) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.findById(Long.parseLong(userId));
        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword()) && checkPassword(newPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/email")
    public int updateEmail(@RequestBody Map<String, String> map) {
        return 0;
    }

    @PutMapping("/honenumber")
    public int updatePhoneNumber(@RequestBody Map<String, String> map) {
        return 0;
    }

    private Boolean checkPassword(String password){
        if (!password.isEmpty()&&!password.isBlank()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean checkEmail(String email){
        return Boolean.FALSE;
    }

    private Boolean checkPhoneNumber(String phoneNumber){
        return Boolean.FALSE;
    }
}
