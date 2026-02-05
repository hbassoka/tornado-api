package com.jdevhub.tornado.api.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.security.domain.dto.ProfileDto;
import com.jdevhub.tornado.api.security.domain.model.Profile;

@Mapper( 
		componentModel = "spring",
		uses=UserMapper.class
		
		)
public interface ProfileMapper {

	 ProfileDto toDto(Profile entiy);
	 Profile toEntity(ProfileDto dto);

}
