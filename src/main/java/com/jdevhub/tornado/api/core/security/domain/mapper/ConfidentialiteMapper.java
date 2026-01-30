package com.jdevhub.tornado.api.core.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.core.security.domain.dto.ConfidentialiteDto;
import com.jdevhub.tornado.api.core.security.domain.model.Confidentialite;

@Mapper( 
		componentModel = "spring",
		uses=UserMapper.class
		
		)
public interface ConfidentialiteMapper {

	ConfidentialiteDto toDto(Confidentialite entity);
    Confidentialite toEntity(ConfidentialiteDto dto);
}
