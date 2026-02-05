package com.jdevhub.tornado.api.messaging.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.messaging.domain.dto.LabelDto;
import com.jdevhub.tornado.api.messaging.domain.model.Label;

@Mapper(componentModel = "spring")
public interface LabelMapper {

	LabelDto toDto( Label entity);
	 
	Label toEntity( LabelDto dto);
}

