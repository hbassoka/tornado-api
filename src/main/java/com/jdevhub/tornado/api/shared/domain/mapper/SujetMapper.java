package com.jdevhub.tornado.api.shared.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.shared.domain.dto.SujetDto;
import com.jdevhub.tornado.api.shared.domain.model.Sujet;

@Mapper(componentModel = "spring")
public interface SujetMapper {

    SujetDto toDto(Sujet entity);

    Sujet toEntity(SujetDto dto);
}

