package com.jdevhub.tornado.api.core.security.domain.dto;

import java.time.Instant;

public record UsedTokenDto(String jti, Instant expiry) {

}
