package com.jdevhub.tornado.api.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.security.domain.dto.RefreshTokenDto;
import com.jdevhub.tornado.api.security.domain.model.RefreshToken;

@Mapper( 
		componentModel = "spring",
		uses=UserMapper.class
		
		)
public interface RefreshTokenMapper {

	RefreshToken toEntity(RefreshTokenDto dto);
	
	RefreshTokenDto toDto(RefreshToken entity);
}
