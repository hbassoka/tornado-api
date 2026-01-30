package com.jdevhub.intranet.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.mailbox.domain.dto.AttachmentDto;
import com.jdevhub.intranet.api.core.mailbox.domain.model.Attachment;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {

	 AttachmentDto toDto( Attachment entity);
	 
	 Attachment toEntity( AttachmentDto dto);
}
