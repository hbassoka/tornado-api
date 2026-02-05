package com.jdevhub.tornado.api.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdevhub.tornado.api.security.domain.mapper.UserMapperImpl;
import com.jdevhub.tornado.api.security.domain.dto.UserDto;
import com.jdevhub.tornado.api.security.domain.model.User;
import com.jdevhub.tornado.api.security.domain.repository.GroupRepository;
import com.jdevhub.tornado.api.security.domain.repository.UserRepository;
import com.jdevhub.tornado.api.security.service.UserService;
import com.jdevhub.tornado.api.security.service.impl.UserServiceImpl;


@SpringBootTest
public class UserServiceTest {

	private final UserRepository userRepository = mock(UserRepository.class);
	
	private final GroupRepository groupRepository= mock(GroupRepository.class);
	
	
    private final UserService service = new UserServiceImpl(userRepository,groupRepository,new UserMapperImpl());
    
    
    @Test
    void testGetAllUsers() {
    	
    	
        when(userRepository.findAll()).thenReturn(List.of(new User()));
        List<UserDto> users = service.findAll();
        
        assertFalse(users.isEmpty());
    }
    
}
