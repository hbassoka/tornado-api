package com.jdevhub.tornado.api.common.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.common.domain.dto.SujetDto;
import com.jdevhub.tornado.api.common.domain.model.Sujet;

@Mapper(componentModel = "spring")
public interface SujetMapper {

    SujetDto toDto(Sujet entity);

    Sujet toEntity(SujetDto dto);
}

