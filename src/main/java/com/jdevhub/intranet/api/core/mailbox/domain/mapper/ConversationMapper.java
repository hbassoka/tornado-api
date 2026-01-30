package com.jdevhub.intranet.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.mailbox.domain.dto.ConversationDto;
import com.jdevhub.intranet.api.core.mailbox.domain.model.Conversation;

@Mapper(componentModel = "spring")
public interface ConversationMapper {

	ConversationDto toDto( Conversation entity);
	 
	Conversation toEntity( ConversationDto dto);
}
