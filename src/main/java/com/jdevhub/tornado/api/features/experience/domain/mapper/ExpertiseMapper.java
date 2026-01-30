package com.jdevhub.tornado.api.features.experience.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.features.experience.domain.dto.ExpertiseDto;
import com.jdevhub.tornado.api.features.experience.domain.model.Expertise;

@Mapper(componentModel = "spring")
public interface ExpertiseMapper {

	Expertise toEntity(ExpertiseDto dto);
	ExpertiseDto toDto(Expertise entity);
}
