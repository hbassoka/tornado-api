package com.jdevhub.tornado.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.core.mailbox.domain.dto.MessageDto;
import com.jdevhub.tornado.api.core.mailbox.domain.model.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

	MessageDto toDto( Message entity);
	 
	Message toEntity( MessageDto dto);
}
