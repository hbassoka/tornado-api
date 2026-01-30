package com.jdevhub.intranet.api.core.security.domain.dto;

import java.util.Set;

public record UserDto(Long id, String provider, String providerId, String username,String email,
		String password, String rawPassword, String secret, boolean enabled,boolean deletable, boolean twoFactorEnabled,
		boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, Set<GroupDto> groups
		) {
		
}
