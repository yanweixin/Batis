package com.batis.application.service.system;

import com.batis.application.database.entity.system.BlockList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class SecurityService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    BlockListService blockListService;
    @Qualifier("blockList")
    @Autowired
    Map<String, List<Pattern>> patterns;

    public String encode(String raw) {
        return passwordEncoder.encode(raw);
    }

    public Boolean matches(String raw, String encoded) {
        return passwordEncoder.matches(raw, encoded);
    }

    public Boolean checkUsername(String username) {
        List<BlockList> blockList = blockListService.findAllByType("username");
        if (blockList.stream().filter(it -> !it.isPattern()).anyMatch(it -> username.contains(it.getValue()))) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean checkPassword(String password) {
        if (!password.isEmpty() && !password.isBlank()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean checkEmail(String email) {
        List<BlockList> blockList = blockListService.findAllByType("email");
        if (blockList.stream().filter(it -> !it.isPattern()).anyMatch(it -> email.contains(it.getValue()))) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean checkPhoneNumber(String phoneNumber) {
        if (patterns.get("phonenumber").stream().anyMatch(it -> it.matcher(phoneNumber).matches())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean filterIp(String ip) {
        List<BlockList> blockList = blockListService.findAllByType("ip");
        if (blockList.stream().filter(it -> !it.isPattern()).anyMatch(it -> ip.equals(it.getValue()))) {
            return Boolean.FALSE;
        }
        if (patterns.get("ip").stream().anyMatch(it -> it.matcher(ip).matches())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
