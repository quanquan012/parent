package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: li.hao
 * @date: 2018/10/30 14:03
 */
//@Configuration
public class RedisConfiguration<K, V> {

    /*@Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate<K, V> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }*/

}
