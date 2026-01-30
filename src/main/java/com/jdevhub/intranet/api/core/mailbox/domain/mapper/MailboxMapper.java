package com.jdevhub.intranet.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.mailbox.domain.dto.MailboxDto;
import com.jdevhub.intranet.api.core.mailbox.domain.model.Mailbox;
import com.jdevhub.intranet.api.core.security.domain.mapper.UserMapper;

@Mapper( 
		componentModel = "spring",
		uses=UserMapper.class		
		)
public interface MailboxMapper {

	MailboxDto toDto( Mailbox entity);
	 
	Mailbox toEntity( MailboxDto dto);
}
