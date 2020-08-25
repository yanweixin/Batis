package com.batis.application.database.entity.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Note:
 * 1. If not implements Serializable ,the {@link org.springframework.data.redis.serializer.JdkSerializationRedisSerializer}
 * will not serialize id filed ,but other serialization class works well without it.
 */
@MappedSuperclass
public abstract class IdEntity implements Serializable {

    @Id
//    @MongoId
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.batis.application.database.common.IdGenerator")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}