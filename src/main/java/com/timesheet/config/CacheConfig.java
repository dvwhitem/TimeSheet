package com.timesheet.config;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vitaliy on 14.05.15.
 */
@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

    final static String CACHE_POLICY = "LRU";

    @Bean(destroyMethod = "shutdown")
    public CacheManager ehCacheManager() {
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(cacheConfig("employeeList"));
        return CacheManager.create(config);
    }

    @Bean
    public org.springframework.cache.CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }


    @Bean
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(cacheManager());
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Bean
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }

    private CacheConfiguration cacheConfig(String name) {
        CacheConfiguration config = new CacheConfiguration();
        config.setName(name);
        config.setMaxElementsInMemory(16384);
        config.eternal(false);
        config.timeToIdleSeconds(300);
        config.timeToLiveSeconds(1200);
        config.overflowToDisk(false);
        config.setMemoryStoreEvictionPolicy(CACHE_POLICY);
        return config;
    }

}
