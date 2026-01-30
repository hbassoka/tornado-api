package com.jdevhub.tornado.api.core.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.core.security.domain.dto.RoleDto;
import com.jdevhub.tornado.api.core.security.domain.model.Role;

@Mapper(
	componentModel = "spring",
    uses = PermissionMapper.class
)
public interface RoleMapper  {

  RoleDto toDto(Role entity);
  Role toEntity(RoleDto dto) ;
    
}
