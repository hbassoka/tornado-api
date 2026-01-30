package com.jdevhub.tornado.api.features.experience.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.features.experience.domain.dto.MissionDto;
import com.jdevhub.tornado.api.features.experience.domain.model.Mission;

@Mapper(componentModel = "spring")
public interface MissionMapper {

	Mission toEntity(MissionDto dto);
	
	MissionDto toDto(Mission entity);
}
