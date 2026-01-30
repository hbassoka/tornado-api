package com.jdevhub.intranet.api.core.auth.repository;


import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.jdevhub.intranet.api.core.security.config.AuthPersistenceTestConfig;
import com.jdevhub.tornado.api.core.security.domain.repository.UserRepository;


@ContextConfiguration(classes= {AuthPersistenceTestConfig.class})
@DataJpaTest
public class UserRepositoryIT {

	@Autowired
	UserRepository userRepository;
	
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
