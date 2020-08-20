package com.batis.application.web.controller.audit;

import com.batis.application.database.entity.audit.Log;
import com.batis.application.service.audit.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("audit/log")
public class LogController {
    @Autowired
    LogService logService;

    @PostMapping("/")
    public Log addLog(@RequestBody Log log) {
        return logService.save(log);
    }
}
