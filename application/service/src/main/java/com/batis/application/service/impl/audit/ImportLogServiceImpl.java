package com.batis.application.service.impl.audit;

import com.batis.application.database.entity.audit.ImportLog;
import com.batis.application.database.repository.mongo.ImportLogRepository;
import com.batis.application.service.audit.ImportLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportLogServiceImpl implements ImportLogService {
    @Autowired
    ImportLogRepository importLogRepository;

    @Override
    public ImportLog save(ImportLog importLog) {
        return importLogRepository.save(importLog);
    }
}
