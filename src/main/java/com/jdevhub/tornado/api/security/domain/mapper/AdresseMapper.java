package com.jdevhub.tornado.api.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.security.domain.dto.AdresseDto;
import com.jdevhub.tornado.api.security.domain.model.Adresse;

@Mapper(componentModel = "spring")
public interface AdresseMapper {

	Adresse toEntity(AdresseDto dto);
	
	AdresseDto toDto(Adresse entity);
	
}
