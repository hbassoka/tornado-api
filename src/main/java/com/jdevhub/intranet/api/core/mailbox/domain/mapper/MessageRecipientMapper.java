package com.jdevhub.intranet.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.mailbox.domain.dto.MessageRecipientDto;
import com.jdevhub.intranet.api.core.mailbox.domain.model.MessageRecipient;

@Mapper(componentModel = "spring")
public interface MessageRecipientMapper {

	MessageRecipientDto toDto( MessageRecipient entity);
	 
	MessageRecipient toEntity( MessageRecipientDto dto);
}
