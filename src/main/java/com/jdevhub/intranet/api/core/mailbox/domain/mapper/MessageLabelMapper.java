package com.jdevhub.intranet.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.mailbox.domain.dto.MessageLabelDto;
import com.jdevhub.intranet.api.core.mailbox.domain.model.MessageLabel;

@Mapper(componentModel = "spring")
public interface MessageLabelMapper {

	MessageLabelDto toDto( MessageLabel entity);
	 
	MessageLabel toEntity( MessageLabelDto dto);
}
