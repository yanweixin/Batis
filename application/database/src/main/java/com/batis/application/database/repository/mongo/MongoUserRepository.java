package com.batis.application.database.repository.mongo;

import com.batis.application.database.entity.management.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<User,Long> {
}
