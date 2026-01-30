package com.jdevhub.tornado.api.core.security.service;

import java.time.Instant;

public interface UsedTokenService {

	void checkAndRegister(String jti, Instant expiry);
}
