package com.batis.application.database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }

    /**
     * customize a redisTemplate . the defect of GenericJackson2JsonRedisSerializer is when value is within Integer scope,
     * it will convert value to Integer instead of Long. To manually convert Integer to Long ,please use {@code
     * new GenericJackson2JsonRedisSerializer(new ObjectMapper().enable(DeserializationFeature.USE_LONG_FOR_INTS))}
     * @param connectionFactory
     * @param <K>
     * @param <V>
     * @return
     */
    @Bean
    public <K, V> RedisTemplate<K, V> redisTemplate(RedisConnectionFactory connectionFactory) {
        final RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
