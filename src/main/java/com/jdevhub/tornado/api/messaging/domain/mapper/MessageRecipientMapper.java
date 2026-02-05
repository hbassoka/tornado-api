package com.jdevhub.tornado.api.messaging.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.messaging.domain.dto.MessageRecipientDto;
import com.jdevhub.tornado.api.messaging.domain.model.MessageRecipient;

@Mapper(componentModel = "spring")
public interface MessageRecipientMapper {

	MessageRecipientDto toDto( MessageRecipient entity);
	 
	MessageRecipient toEntity( MessageRecipientDto dto);
}
