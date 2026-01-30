package com.jdevhub.intranet.api.core.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jdevhub.intranet.api.core.auth.domain.dto.FacebookUser;
import com.jdevhub.intranet.api.core.auth.domain.dto.GoogleUser;
import com.jdevhub.intranet.api.core.security.domain.dto.UserDto;
import com.jdevhub.intranet.api.core.security.domain.dto.UserInformationDto;

public interface UserService {
	
	
	Optional<UserDto> findById(Long id);
		
	Optional<UserDto>  save(UserDto user);
	
	Optional<UserDto>  update(Long id, UserDto user);
	
	void delete(Long id) ;
	
    boolean existsByUsername(String username);
     	
    
	Optional<UserDto> findByUsername(String username);
    
	Optional<UserDto> getCurrentUser();    
	    

	List<UserDto> findAll() ;
	 
    Page<UserDto> search(String keyword, Pageable pageable);
   
    Optional<UserDto> register(UserInformationDto userInformationDto);

    Optional<UserDto> createOrUpdate(UserDto userDto);
    
    Optional<UserDto> createOrUpdateGoogleUser(GoogleUser googleUser);
    
    Optional<UserDto> createOrUpdateFacebookUser(FacebookUser facebookUser);
    
    

    void addGroupToUser(Long userId, String groupName);
    
    void removeGroupFromUser(Long userId, String groupName);

}