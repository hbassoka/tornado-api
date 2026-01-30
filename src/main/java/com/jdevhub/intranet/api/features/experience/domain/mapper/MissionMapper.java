package com.jdevhub.intranet.api.features.experience.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.features.experience.domain.dto.MissionDto;
import com.jdevhub.intranet.api.features.experience.domain.model.Mission;

@Mapper(componentModel = "spring")
public interface MissionMapper {

	Mission toEntity(MissionDto dto);
	
	MissionDto toDto(Mission entity);
}
