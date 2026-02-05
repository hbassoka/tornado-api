package com.jdevhub.tornado.api.security.domain.dto;

import java.util.Set;

public record RoleDto(Long id,String name,String label ,Set<PermissionDto> permissions) {

}
