package com.jdevhub.tornado.api.core.auth.domain.dto;

public record AuthResponse(String accessToken, String refreshToken, AppUser user) {
	
	
}
