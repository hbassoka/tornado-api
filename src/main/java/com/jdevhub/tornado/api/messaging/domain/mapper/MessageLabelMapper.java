package com.jdevhub.tornado.api.messaging.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.messaging.domain.dto.MessageLabelDto;
import com.jdevhub.tornado.api.messaging.domain.model.MessageLabel;

@Mapper(componentModel = "spring")
public interface MessageLabelMapper {

	MessageLabelDto toDto( MessageLabel entity);
	 
	MessageLabel toEntity( MessageLabelDto dto);
}
