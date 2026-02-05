package com.jdevhub.tornado.api.security.domain.dto;

import java.time.Instant;

public record UsedTokenDto(String jti, Instant expiry) {

}
