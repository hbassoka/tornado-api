package com.jdevhub.tornado.api.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.security.domain.dto.SecurityAuditDto;
import com.jdevhub.tornado.api.security.domain.model.SecurityAudit;

@Mapper(componentModel = "spring")
public interface SecurityAuditMapper {

	SecurityAudit toEntity(SecurityAuditDto dto);
	SecurityAuditDto toDto(SecurityAudit entity);
}
