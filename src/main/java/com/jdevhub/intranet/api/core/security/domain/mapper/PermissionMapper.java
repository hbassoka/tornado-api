package com.jdevhub.intranet.api.core.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.security.domain.dto.PermissionDto;
import com.jdevhub.intranet.api.core.security.domain.model.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto toDto(Permission entity) ;      

    Permission toEntity(PermissionDto dto) ;
}
