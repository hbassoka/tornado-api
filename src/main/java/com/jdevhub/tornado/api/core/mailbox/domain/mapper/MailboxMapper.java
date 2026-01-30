package com.jdevhub.tornado.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.core.mailbox.domain.dto.MailboxDto;
import com.jdevhub.tornado.api.core.mailbox.domain.model.Mailbox;
import com.jdevhub.tornado.api.core.security.domain.mapper.UserMapper;

@Mapper( 
		componentModel = "spring",
		uses=UserMapper.class		
		)
public interface MailboxMapper {

	MailboxDto toDto( Mailbox entity);
	 
	Mailbox toEntity( MailboxDto dto);
}
