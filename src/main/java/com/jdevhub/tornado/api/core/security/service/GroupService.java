package com.jdevhub.tornado.api.core.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jdevhub.tornado.api.core.security.domain.dto.GroupDto;

public interface GroupService {

	
	List<GroupDto> findAll();
	
	Optional<GroupDto> findById(Long id);
	
	Optional<GroupDto> findByName(String name);
	
	Page<GroupDto> search(String keyword, Pageable pageable);
	
	void addRoleToGroup(Long groupId, String roleName) ;
	
	void removeRoleFromGroup(Long groupId, String roleName);
	
	
}
