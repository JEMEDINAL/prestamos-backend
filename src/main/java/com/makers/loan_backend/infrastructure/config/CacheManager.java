package com.makers.loan_backend.infrastructure.config;


import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ehcache.jsr107.Eh107Configuration;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
@EnableCaching
public class CacheManager {

    @Bean
    public org.springframework.cache.CacheManager ehcacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        javax.cache.CacheManager cacheManager = provider.getCacheManager();

        var configuracionEhcache = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        Object.class, Object.class, ResourcePoolsBuilder.heap(500))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(15)))
                .build();

        var configuracionJCache = Eh107Configuration.fromEhcacheCacheConfiguration(configuracionEhcache);

        if (cacheManager.getCache("prestamos_usuario") == null) {
            cacheManager.createCache("prestamos_usuario", configuracionJCache);
        }

        if (cacheManager.getCache("usuarios_y_prestamos") == null) {
            cacheManager.createCache("usuarios_y_prestamos", configuracionJCache);
        }

        return new JCacheCacheManager(cacheManager);
    }
    
}
