package com.batis.application.service.impl.audit;

import com.batis.application.database.entity.audit.Log;
import com.batis.application.database.repository.mongo.LogRepository;
import com.batis.application.service.audit.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogRepository logRepository;

    @Override
    public Log save(Log log) {
        return logRepository.save(log);
    }
}
