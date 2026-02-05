package com.jdevhub.tornado.api.common.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.common.domain.dto.ParametreDto;
import com.jdevhub.tornado.api.common.domain.model.Parametre;

@Mapper(componentModel = "spring")
public interface ParametreMapper {

	ParametreDto toDto(Parametre entity);
	Parametre toEntity(ParametreDto dto);
}
