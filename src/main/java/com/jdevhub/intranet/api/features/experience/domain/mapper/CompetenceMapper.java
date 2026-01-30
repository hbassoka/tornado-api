package com.jdevhub.intranet.api.features.experience.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.features.experience.domain.dto.CompetenceDto;
import com.jdevhub.intranet.api.features.experience.domain.model.Competence;

@Mapper(componentModel = "spring")
public interface CompetenceMapper {

	Competence toEntity(CompetenceDto dto);
	CompetenceDto toDto(Competence entity);
}
