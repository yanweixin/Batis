package com.batis.application.service.audit;

import com.batis.application.database.entity.audit.Log;

public interface LogService {
    Log save(Log log);
}
