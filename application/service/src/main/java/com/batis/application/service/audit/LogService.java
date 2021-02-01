package com.batis.application.service.audit;

import com.batis.application.database.entity.audit.Log;
import com.batis.application.database.repository.mongo.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log save(Log log) {
        return logRepository.save(log);
    }
}
