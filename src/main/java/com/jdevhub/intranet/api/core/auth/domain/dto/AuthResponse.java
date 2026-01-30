package com.jdevhub.intranet.api.core.auth.domain.dto;

public record AuthResponse(String accessToken, String refreshToken, AppUser user) {
	
	
}
