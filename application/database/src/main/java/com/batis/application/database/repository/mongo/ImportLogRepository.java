package com.batis.application.database.repository.mongo;

import com.batis.application.database.entity.audit.ImportLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImportLogRepository extends MongoRepository<ImportLog,String> {
}
