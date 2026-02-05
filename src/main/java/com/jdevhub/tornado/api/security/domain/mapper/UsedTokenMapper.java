package com.jdevhub.tornado.api.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.security.domain.dto.UsedTokenDto;
import com.jdevhub.tornado.api.security.domain.model.UsedToken;

@Mapper(componentModel = "spring")
public interface UsedTokenMapper {

	UsedToken toEntity(UsedTokenDto dto);
	UsedTokenDto toDto(UsedToken entity);
	
    
}
