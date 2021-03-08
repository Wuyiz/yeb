package com.wuyiz.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created with IntelliJ IDEA
 * @author: suhai
 * @date: 2021-03-08 22:08
 * @description: Redis配置类，配置了redisTemplate的序列化之后， 客户端也能正确显示键值对
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // String 类型 key 序列器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // String 类型 value 序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // Hash 类型 key 序列器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // Hash 类型 value 序列器
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}
