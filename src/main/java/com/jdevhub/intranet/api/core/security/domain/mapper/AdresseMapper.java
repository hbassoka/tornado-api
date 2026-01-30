package com.jdevhub.intranet.api.core.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.security.domain.dto.AdresseDto;
import com.jdevhub.intranet.api.core.security.domain.model.Adresse;

@Mapper(componentModel = "spring")
public interface AdresseMapper {

	Adresse toEntity(AdresseDto dto);
	
	AdresseDto toDto(Adresse entity);
	
}
