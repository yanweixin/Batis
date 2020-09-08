package com.batis.application.service.audit;

import com.batis.application.database.entity.audit.Log;
import com.batis.application.database.repository.mongo.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    LogRepository logRepository;

    public Log save(Log log) {
        return logRepository.save(log);
    }
}
