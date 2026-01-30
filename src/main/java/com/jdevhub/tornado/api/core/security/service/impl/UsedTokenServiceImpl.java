package com.jdevhub.tornado.api.core.security.service.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.jdevhub.tornado.api.core.security.domain.model.UsedToken;
import com.jdevhub.tornado.api.core.security.domain.repository.UsedTokenRepository;
import com.jdevhub.tornado.api.core.security.service.UsedTokenService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UsedTokenServiceImpl implements UsedTokenService {

	private final UsedTokenRepository usedTokenRepository;

	public UsedTokenServiceImpl(UsedTokenRepository usedTokenRepository) {
		super();
		this.usedTokenRepository = usedTokenRepository;
	}

	@Override
	public void checkAndRegister(String jti, Instant expiry) {

		if (usedTokenRepository.existsById(jti)) {
			throw new SecurityException("Replay detected");
		}

		UsedToken ut = new UsedToken();
		ut.setJti(jti);
		ut.setExpiry(expiry);

		usedTokenRepository.save(ut);
	}

}
