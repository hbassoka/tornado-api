package com.jdevhub.intranet.api.core.security.domain.dto;

import java.util.Set;

public record GroupDto(Long id, String name,String label,boolean deletable, Set<RoleDto> roles)  {}