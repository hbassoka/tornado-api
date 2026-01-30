package com.jdevhub.tornado.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.core.mailbox.domain.dto.MailboxMessageDto;

@Mapper(componentModel = "spring")
public interface MailboxMessageMapper {

	
	MailboxMessageDto toDto( MailboxMessageDto entity);
	 
	MailboxMessageDto toEntity( MailboxMessageDto dto);
}
