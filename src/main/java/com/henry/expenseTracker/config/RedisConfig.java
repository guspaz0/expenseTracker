package com.henry.expenseTracker.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.henry.expenseTracker.util.constants.CacheConstants;

import java.util.Map;

@Configuration
@EnableCaching
@EnableScheduling
@Slf4j
public class RedisConfig {

    @Value(value= "${cache.redis.address}")
    private String serverAddress;

    @Value(value= "${cache.redis.password}")
    private String serverPassword;

    /*
     * Con esta configuracion cargamos el cliente de redis al contenedor de spring
     */
    @Bean
    public RedissonClient redissonClient(){
        var config = new Config();
        config.useSingleServer()
                .setAddress(serverAddress)
                .setPassword(serverPassword);
        return Redisson.create(config);
    }

    /*
     * Con esta configuracion podemos habilitar las anotaciones de spring cache @Cacheable
     */
    @Bean
    @Autowired
    public CacheManager cacheManager(RedissonClient redissonClient){
        var configs = Map.of(
                CacheConstants.EXPENSE_CACHE_NAME, new CacheConfig(),
                CacheConstants.SUPPLIER_CACHE_NAME, new CacheConfig(),
                CacheConstants.CATEGORY_CACHE_NAME, new CacheConfig(),
                CacheConstants.EXPIRATIONS_CACHE_NAME, new CacheConfig()
        );
        return new RedissonSpringCacheManager(redissonClient, configs);
    }
}
