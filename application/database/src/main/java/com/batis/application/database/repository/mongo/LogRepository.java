package com.batis.application.database.repository.mongo;

import com.batis.application.database.entity.audit.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
}
