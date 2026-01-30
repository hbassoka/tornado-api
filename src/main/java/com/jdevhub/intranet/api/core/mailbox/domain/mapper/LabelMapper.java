package com.jdevhub.intranet.api.core.mailbox.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.mailbox.domain.dto.LabelDto;
import com.jdevhub.intranet.api.core.mailbox.domain.model.Label;

@Mapper(componentModel = "spring")
public interface LabelMapper {

	LabelDto toDto( Label entity);
	 
	Label toEntity( LabelDto dto);
}

