package com.jdevhub.tornado.api.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.security.domain.dto.RoleDto;
import com.jdevhub.tornado.api.security.domain.model.Role;

@Mapper(
	componentModel = "spring",
    uses = PermissionMapper.class
)
public interface RoleMapper  {

  RoleDto toDto(Role entity);
  Role toEntity(RoleDto dto) ;
    
}
