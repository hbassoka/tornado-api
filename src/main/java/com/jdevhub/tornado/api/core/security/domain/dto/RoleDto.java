package com.jdevhub.tornado.api.core.security.domain.dto;

import java.util.Set;

public record RoleDto(Long id,String name,String label ,Set<PermissionDto> permissions) {

}
