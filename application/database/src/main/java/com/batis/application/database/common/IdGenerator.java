package com.batis.application.database.common;

import com.batis.library.generator.SnowFlakeGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class IdGenerator implements IdentifierGenerator {
    private final static SnowFlakeGenerator generator = new SnowFlakeGenerator(1);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return generator.nextId();
    }
}
