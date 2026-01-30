package com.jdevhub.tornado.api.core.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdevhub.tornado.api.core.security.domain.dto.RoleDto;
import com.jdevhub.tornado.api.core.security.domain.mapper.RoleMapper;
import com.jdevhub.tornado.api.core.security.domain.model.Permission;
import com.jdevhub.tornado.api.core.security.domain.model.Role;
import com.jdevhub.tornado.api.core.security.domain.repository.PermissionRepository;
import com.jdevhub.tornado.api.core.security.domain.repository.RoleRepository;
import com.jdevhub.tornado.api.core.security.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private final RoleRepository roleRepository;	
	private final RoleMapper  roleMapper;
    private final PermissionRepository permissionRepository;
	
	public RoleServiceImpl(final RoleRepository roleRepository, final RoleMapper  roleMapper,final PermissionRepository permissionRepository) {
		super();
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
		this.permissionRepository = permissionRepository;
	}

	

	@Override
	public List<RoleDto> findAll() {
	    return this.roleRepository.findAll().stream().map(r -> roleMapper.toDto(r)).toList();
	                
	}

	@Override
	public Optional<RoleDto> findById(Long id) {
		return this.roleRepository.findById(id).map(roleMapper::toDto);
	}

	@Override
	public Optional<RoleDto> findByName(String name) {

		return this.roleRepository.findByName(name).map(roleMapper::toDto);
	}

	
	@Override
	public Optional<RoleDto> createOrUpdate(RoleDto role) {

	    Role newRole =roleMapper.toEntity(role);
	    Role saved=this.roleRepository.save(newRole);

	    return Optional.of(roleMapper.toDto(saved));
	}
	
	
	@Override
	public void delete(Long id) {
		this.roleRepository.deleteById(id);		
	}
	
	@Override
	public void addPermissionToRole(Long roleId, String permissionName) {		
		Role role = roleRepository.findById(roleId).orElseThrow();
		Permission permission = permissionRepository.findByName(permissionName).orElseThrow();
		role.getPermissions().add(permission);	
		roleRepository.save(role);
		
	}

	@Override
	public void removePermissionFromRole(Long roleId, String permissionName) {
		Role role = roleRepository.findById(roleId).orElseThrow();
		Permission permission = permissionRepository.findByName(permissionName).orElseThrow();
		role.getPermissions().remove(permission);
		roleRepository.save(role);
		
	}



	@Override
	public Page<RoleDto> search(String keyword, Pageable pageable) {
		
		return roleRepository.search(keyword, pageable).map(roleMapper::toDto);
	}

	
	
	
}
