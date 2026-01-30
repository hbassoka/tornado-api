package com.jdevhub.tornado.api.core.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.core.security.domain.dto.UsedTokenDto;
import com.jdevhub.tornado.api.core.security.domain.model.UsedToken;

@Mapper(componentModel = "spring")
public interface UsedTokenMapper {

	UsedToken toEntity(UsedTokenDto dto);
	UsedTokenDto toDto(UsedToken entity);
	
    
}
