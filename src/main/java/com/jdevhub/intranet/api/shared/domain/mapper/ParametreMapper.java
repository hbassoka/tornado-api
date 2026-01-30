package com.jdevhub.intranet.api.shared.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.shared.domain.dto.ParametreDto;
import com.jdevhub.intranet.api.shared.domain.model.Parametre;

@Mapper(componentModel = "spring")
public interface ParametreMapper {

	ParametreDto toDto(Parametre entity);
	Parametre toEntity(ParametreDto dto);
}
