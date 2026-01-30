package com.jdevhub.intranet.api.core.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.security.domain.dto.GroupDto;
import com.jdevhub.intranet.api.core.security.domain.model.Group;


@Mapper(
	componentModel = "spring",
	uses=RoleMapper.class
)
public interface GroupMapper {

    GroupDto toDto(Group entity);

    Group toEntity(GroupDto dto);
    
    
}
