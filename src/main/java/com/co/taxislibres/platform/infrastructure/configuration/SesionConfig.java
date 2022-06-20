package com.co.taxislibres.platform.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
public class SesionConfig extends AbstractHttpSessionApplicationInitializer {

	
//    @Bean
//    public static ConfigureRedisAction configureRedisAction() {
//        return ConfigureRedisAction.NO_OP;
//    }
   
    @Bean
    public FindByIndexNameSessionRepository<?> redisSessionRepository(RedisOperations<Object, Object> sessionRedisOperations) {
        return new RedisOperationsSessionRepository(sessionRedisOperations);
    }
}
