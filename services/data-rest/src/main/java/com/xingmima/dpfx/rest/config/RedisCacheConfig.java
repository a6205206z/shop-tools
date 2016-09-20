package com.xingmima.dpfx.rest.config;

import com.xingmima.dpfx.rest.util.GenericJackson2JsonRedisSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version RedisCacheConfig, v 0.1
 * @date 2016 /9/13 19:24
 */
@Configuration
@EnableCaching
@PropertySource(value = {"classpath:redis.properties"})
public class RedisCacheConfig extends CachingConfigurerSupport {
    private static Logger log = LoggerFactory.getLogger(RedisCacheConfig.class);
    @Value("${redis.host:127.0.0.1}")
    private String host;

    @Value("${redis.port:6379}")
    private Integer port;

    @Value("${redis.password:test}")
    private String password;

    @Value("${redis.timeout:100000}")
    private Integer timeout;

    /**
     * Redis connection factory jedis connection factory.
     *
     * @return the jedis connection factory
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        // Defaults
        redisConnectionFactory.setHostName(host);
        redisConnectionFactory.setPort(port);
        redisConnectionFactory.setPassword(password);
        redisConnectionFactory.setTimeout(timeout);
        //JedisPoolConfig config = new JedisPoolConfig();
//        redisConnectionFactory.setPoolConfig();
        return redisConnectionFactory;
    }

    /**
     * Redis template redis template.
     *
     * @param cf the cf
     * @return the redis template
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(cf);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }

    /**
     * Cache manager cache manager.
     *
     * @param redisTemplate the redis template
     * @return the cache manager
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

        cacheManager.setDefaultExpiration(TimeUnit.MINUTES.toSeconds(30)); // 设置失效时间
        return cacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object o, Method method, Object... objects) {
                // This will generate a unique key of the class name, the method name,
                // and all method parameters appended.
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                log.debug("KEY:" + sb.toString());
                return sb.toString();
            }
        };
    }
}
