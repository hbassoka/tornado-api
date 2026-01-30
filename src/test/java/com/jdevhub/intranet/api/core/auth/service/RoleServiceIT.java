package com.jdevhub.intranet.api.core.auth.service;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.jdevhub.intranet.api.core.security.config.AuthPersistenceTestConfig;
@ContextConfiguration(classes= {AuthPersistenceTestConfig.class})
@DataJpaTest
public class RoleServiceIT {

	@Before
	public void init() {
	    //MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	public void setUp() {
		
	}
	
	@AfterEach
	public void tearDown() {
		
	}
}
