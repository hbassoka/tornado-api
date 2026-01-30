package com.jdevhub.intranet.api.core.auth.web;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.jdevhub.intranet.api.core.security.web.RoleController;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

	
	@Autowired
	RoleController roleController;
	
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
