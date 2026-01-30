package com.jdevhub.tornado.api.core.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jdevhub.tornado.api.core.security.domain.dto.PermissionDto;

public interface PermissionService {

	List<PermissionDto> findAll() ;

	Optional<PermissionDto> findById(Long id);
    
	Optional<PermissionDto> findByName(String name);
    
    Page<PermissionDto> search(String keyword, Pageable pageable);
}
