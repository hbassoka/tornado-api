package com.jdevhub.tornado.api.auth.domain.dto;

public record AuthResponse(String accessToken, String refreshToken, AppUser user) {
	
	
}
