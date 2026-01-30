package com.jdevhub.tornado.api.core.security.domain.dto;

import java.time.Instant;

public record RefreshTokenDto(Long id, String tokenHash, UserDto user, Instant expiryDate, boolean revoked) {

}
