package com.jdevhub.tornado.api.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdevhub.tornado.api.security.domain.dto.PermissionDto;
import com.jdevhub.tornado.api.security.domain.mapper.PermissionMapper;
import com.jdevhub.tornado.api.security.domain.repository.PermissionRepository;
import com.jdevhub.tornado.api.security.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	private final PermissionRepository permissionRepository;
	private final PermissionMapper  permissionMapper;


	public PermissionServiceImpl(final PermissionRepository permissionRepository,final PermissionMapper  permissionMapper) {
		super();
		this.permissionRepository = permissionRepository;
		this.permissionMapper = permissionMapper;
		
	}

	@Override
	public List<PermissionDto> findAll() {
		
		return this.permissionRepository.findAll().stream().map(permissionMapper::toDto).toList();
				 
	}

	@Override
	public Optional<PermissionDto> findById(Long id) {
		
		return permissionRepository.findById(id).map(permissionMapper::toDto);
	}

	@Override
	public Optional<PermissionDto> findByName(String name) {
		
		return permissionRepository.findByName(name).map(permissionMapper::toDto);
	}

	@Override
	public Page<PermissionDto> search(String keyword, Pageable pageable) {
		
		return permissionRepository.search(keyword, pageable).map(permissionMapper::toDto);
	}
	
	
}
	
	
