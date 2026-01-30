package com.jdevhub.tornado.api.core.security.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


@SpringBootConfiguration
@EnableAutoConfiguration
@EntityScan("com.jdevhub.portal.core.security.domain")
@EnableJpaRepositories("com.jdevhub.portal.core.security.repository")
@ActiveProfiles("test")
@TestPropertySource(value= {"classpath:application-test.properties"})
public class AuthServiceTestConfig {

}
