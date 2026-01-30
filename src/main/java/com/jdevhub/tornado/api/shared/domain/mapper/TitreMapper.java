package com.jdevhub.tornado.api.shared.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.shared.domain.dto.TitreDto;
import com.jdevhub.tornado.api.shared.domain.model.Titre;

@Mapper(componentModel = "spring")
public interface TitreMapper {

   TitreDto toDto(Titre entity) ;

   Titre toEntity(TitreDto dto);
}

