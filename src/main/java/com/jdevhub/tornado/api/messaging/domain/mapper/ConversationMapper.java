package com.jdevhub.tornado.api.messaging.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.messaging.domain.dto.ConversationDto;
import com.jdevhub.tornado.api.messaging.domain.model.Conversation;

@Mapper(componentModel = "spring")
public interface ConversationMapper {

	ConversationDto toDto( Conversation entity);
	 
	Conversation toEntity( ConversationDto dto);
}
