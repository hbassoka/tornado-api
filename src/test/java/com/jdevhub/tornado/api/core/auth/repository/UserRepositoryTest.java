package com.jdevhub.tornado.api.core.auth.repository;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.jdevhub.tornado.api.core.security.domain.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

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
