package com.jdevhub.intranet.api.core.security.service;

import java.util.Optional;

import com.jdevhub.intranet.api.core.auth.domain.dto.AppUser;
import com.jdevhub.intranet.api.core.security.domain.dto.RefreshTokenDto;

public interface RefreshTokenService {

	Optional<RefreshTokenDto> create(AppUser appUser, String refreshToken, long expirationMs);

	Optional<RefreshTokenDto> verifyAndRotate(String refreshToken);

	void revokeAll(Long userId);
	
	void revokeAll(String username);
}
