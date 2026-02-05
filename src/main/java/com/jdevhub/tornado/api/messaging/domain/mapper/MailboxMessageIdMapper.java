package com.jdevhub.tornado.api.messaging.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.messaging.domain.dto.MailboxMessageIdDto;
import com.jdevhub.tornado.api.messaging.domain.model.MailboxMessageId;

@Mapper(componentModel = "spring")
public interface MailboxMessageIdMapper {

	MailboxMessageIdDto toDto( MailboxMessageId entity);
	 
	MailboxMessageId toEntity( MailboxMessageIdDto dto);
}
