package com.jdevhub.tornado.api.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.security.domain.dto.GroupDto;
import com.jdevhub.tornado.api.security.domain.model.Group;


@Mapper(
	componentModel = "spring",
	uses=RoleMapper.class
)
public interface GroupMapper {

    GroupDto toDto(Group entity);

    Group toEntity(GroupDto dto);
    
    
}
