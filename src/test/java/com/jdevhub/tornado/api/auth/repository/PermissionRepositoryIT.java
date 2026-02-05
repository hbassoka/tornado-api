package com.jdevhub.tornado.api.auth.repository;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.jdevhub.tornado.api.security.config.AuthPersistenceTestConfig;
import com.jdevhub.tornado.api.security.domain.repository.PermissionRepository;

@ContextConfiguration(classes= {AuthPersistenceTestConfig.class})
@DataJpaTest
public class PermissionRepositoryIT {
	
	
	@Autowired
	PermissionRepository permissionRepository;
	
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
