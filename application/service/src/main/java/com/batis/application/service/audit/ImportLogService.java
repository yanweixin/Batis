package com.batis.application.service.audit;

import com.batis.application.database.entity.audit.ImportLog;

public interface ImportLogService {
    ImportLog save(ImportLog importLog);
}
