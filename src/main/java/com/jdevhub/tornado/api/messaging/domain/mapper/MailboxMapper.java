package com.jdevhub.tornado.api.messaging.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.messaging.domain.dto.MailboxDto;
import com.jdevhub.tornado.api.messaging.domain.model.Mailbox;
import com.jdevhub.tornado.api.security.domain.mapper.UserMapper;

@Mapper( 
		componentModel = "spring",
		uses=UserMapper.class		
		)
public interface MailboxMapper {

	MailboxDto toDto( Mailbox entity);
	 
	Mailbox toEntity( MailboxDto dto);
}
