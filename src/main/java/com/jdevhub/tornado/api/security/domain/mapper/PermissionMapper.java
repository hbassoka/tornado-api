package com.jdevhub.tornado.api.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.security.domain.dto.PermissionDto;
import com.jdevhub.tornado.api.security.domain.model.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto toDto(Permission entity) ;      

    Permission toEntity(PermissionDto dto) ;
}
