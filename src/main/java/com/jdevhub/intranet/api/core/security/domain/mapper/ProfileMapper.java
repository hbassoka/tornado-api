package com.jdevhub.intranet.api.core.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.security.domain.dto.ProfileDto;
import com.jdevhub.intranet.api.core.security.domain.model.Profile;

@Mapper( 
		componentModel = "spring",
		uses=UserMapper.class
		
		)
public interface ProfileMapper {

	 ProfileDto toDto(Profile entiy);
	 Profile toEntity(ProfileDto dto);

}
