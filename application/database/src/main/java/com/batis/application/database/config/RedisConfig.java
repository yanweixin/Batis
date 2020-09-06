package com.batis.application.database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class RedisConfig {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    MessageListener messageListener;
    @Qualifier("default")
    @Autowired
    Executor executor;

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }

    /**
     * Customize a redisTemplate . the defect of GenericJackson2JsonRedisSerializer is when value is within Integer scope,
     * it will convert value to Integer instead of Long. To manually convert Integer to Long ,please use {@code
     * new GenericJackson2JsonRedisSerializer(new ObjectMapper().enable(DeserializationFeature.USE_LONG_FOR_INTS))}
     *
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

    /**
     * Not required, MessageListener can be used directly
     *
     * @return
     */
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(messageListener);
    }

    @Bean
    public RedisMessageListenerContainer container() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListener(), new PatternTopic("__keyspace@*:*"));
        container.setTaskExecutor(executor);
        return container;
    }
}
