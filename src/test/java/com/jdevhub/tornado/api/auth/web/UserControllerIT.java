package com.jdevhub.tornado.api.auth.web;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.jdevhub.tornado.api.security.config.AuthPersistenceTestConfig;
import com.jdevhub.tornado.api.security.config.AuthServiceTestConfig;
import com.jdevhub.tornado.api.security.web.UserController;

@ContextConfiguration(classes= {AuthPersistenceTestConfig.class,AuthServiceTestConfig.class})
@DataJpaTest
public class UserControllerIT {
	
	@Autowired
	UserController  userController;

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
