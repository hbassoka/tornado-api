package com.jdevhub.tornado.api.security.domain.dto;

import java.time.Instant;

public record RefreshTokenDto(Long id, String tokenHash, UserDto user, Instant expiryDate, boolean revoked) {

}
