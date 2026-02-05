package com.jdevhub.tornado.api.messaging.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.messaging.domain.dto.AttachmentDto;
import com.jdevhub.tornado.api.messaging.domain.model.Attachment;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {

	 AttachmentDto toDto( Attachment entity);
	 
	 Attachment toEntity( AttachmentDto dto);
}
