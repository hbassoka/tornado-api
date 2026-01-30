package com.jdevhub.intranet.api.core.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.security.domain.dto.SecurityAuditDto;
import com.jdevhub.intranet.api.core.security.domain.model.SecurityAudit;

@Mapper(componentModel = "spring")
public interface SecurityAuditMapper {

	SecurityAudit toEntity(SecurityAuditDto dto);
	SecurityAuditDto toDto(SecurityAudit entity);
}
