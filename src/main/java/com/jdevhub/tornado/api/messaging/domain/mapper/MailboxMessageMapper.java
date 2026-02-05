package com.jdevhub.tornado.api.messaging.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.messaging.domain.dto.MailboxMessageDto;

@Mapper(componentModel = "spring")
public interface MailboxMessageMapper {

	
	MailboxMessageDto toDto( MailboxMessageDto entity);
	 
	MailboxMessageDto toEntity( MailboxMessageDto dto);
}
