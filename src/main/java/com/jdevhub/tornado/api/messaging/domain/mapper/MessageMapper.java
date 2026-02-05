package com.jdevhub.tornado.api.messaging.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.messaging.domain.dto.MessageDto;
import com.jdevhub.tornado.api.messaging.domain.model.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

	MessageDto toDto( Message entity);
	 
	Message toEntity( MessageDto dto);
}
