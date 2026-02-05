package com.jdevhub.tornado.api.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jdevhub.tornado.api.security.domain.dto.RoleDto;

public interface RoleService {

	List<RoleDto> findAll() ;

	Optional<RoleDto> findById(Long id);
    
	Optional<RoleDto> findByName(String name);
    
    Page<RoleDto> search(String keyword, Pageable pageable);
           
    Optional<RoleDto> createOrUpdate(RoleDto role);
    
    void delete(Long id) ;

    void addPermissionToRole(Long roleId, String permissionName);
    
    void removePermissionFromRole(Long roleId, String permissionName);
}
