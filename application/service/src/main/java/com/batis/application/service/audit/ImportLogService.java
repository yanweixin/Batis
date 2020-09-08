package com.batis.application.service.audit;

import com.batis.application.database.entity.audit.ImportLog;
import com.batis.application.database.repository.mongo.ImportLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportLogService {
    @Autowired
    ImportLogRepository importLogRepository;

    public ImportLog save(ImportLog importLog) {
        return importLogRepository.save(importLog);
    }
}
