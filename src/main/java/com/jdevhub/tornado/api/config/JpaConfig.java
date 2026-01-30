package com.jdevhub.tornado.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {		
    "com.jdevhub.intranet.api.core.security.domain.repository",
    "com.jdevhub.intranet.api.core.auth.domain.repository",
    "com.jdevhub.intranet.api.core.mailbox.domain.repository",
    "com.jdevhub.intranet.api.features.notification.domain.repository",
    "com.jdevhub.intranet.api.features.experience.domain.repository",
    "com.jdevhub.intranet.api.features.formation.domain.repository",   
    "com.jdevhub.intranet.api.shared.domain.repository"
})
@EntityScan(basePackages = {
    "com.jdevhub.intranet.api.core.security.domain.model",
    "com.jdevhub.intranet.api.core.auth.domain.model",
    "com.jdevhub.intranet.api.core.mailbox.domain.model",
    "com.jdevhub.intranet.api.features.notification.domain.model",
    "com.jdevhub.intranet.api.features.experience.domain.model",
    "com.jdevhub.intranet.api.features.formation.domain.model",    
    "com.jdevhub.intranet.api.shared.domain.model"
})
public class JpaConfig {
}
