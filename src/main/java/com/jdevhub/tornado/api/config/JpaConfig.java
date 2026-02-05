package com.jdevhub.tornado.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {		
    "com.jdevhub.tornado.api.security.domain.repository",
    "com.jdevhub.tornado.api.auth.domain.repository",
    "com.jdevhub.tornado.api.messaging.domain.repository",    
    "com.jdevhub.tornado.api.common.domain.repository"
})
@EntityScan(basePackages = {
    "com.jdevhub.tornado.api.security.domain.model",
    "com.jdevhub.tornado.api.auth.domain.model",
    "com.jdevhub.tornado.api.messaging.domain.model",  
    "com.jdevhub.tornado.api.common.domain.model"
})
public class JpaConfig {
}
