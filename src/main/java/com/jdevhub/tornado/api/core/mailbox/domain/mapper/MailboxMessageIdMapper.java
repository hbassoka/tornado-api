package com.jdevhub.tornado.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.core.mailbox.domain.dto.MailboxMessageIdDto;
import com.jdevhub.tornado.api.core.mailbox.domain.model.MailboxMessageId;

@Mapper(componentModel = "spring")
public interface MailboxMessageIdMapper {

	MailboxMessageIdDto toDto( MailboxMessageId entity);
	 
	MailboxMessageId toEntity( MailboxMessageIdDto dto);
}
