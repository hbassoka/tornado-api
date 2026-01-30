package com.jdevhub.intranet.api.core.security.service.impl;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdevhub.intranet.api.config.TokenHasher;
import com.jdevhub.intranet.api.core.auth.domain.dto.AppUser;
import com.jdevhub.intranet.api.core.security.domain.dto.RefreshTokenDto;
import com.jdevhub.intranet.api.core.security.domain.mapper.RefreshTokenMapper;
import com.jdevhub.intranet.api.core.security.domain.model.RefreshToken;
import com.jdevhub.intranet.api.core.security.domain.model.User;
import com.jdevhub.intranet.api.core.security.domain.repository.RefreshTokenRepository;
import com.jdevhub.intranet.api.core.security.domain.repository.UserRepository;
import com.jdevhub.intranet.api.core.security.service.RefreshTokenService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;

	private final RefreshTokenMapper refreshTokenMapper;
   
	private final UserRepository userRepository;

	private final TokenHasher tokenHasher;

	

	public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, RefreshTokenMapper refreshTokenMapper,
			UserRepository userRepository, TokenHasher tokenHasher) {
		super();
		this.refreshTokenRepository = refreshTokenRepository;
		this.refreshTokenMapper = refreshTokenMapper;
		this.userRepository = userRepository;
		this.tokenHasher = tokenHasher;
	}

	@Override
	public Optional<RefreshTokenDto> create(AppUser appUser, String refreshToken, long expirationMs) {

		// RefreshTokenDto(Long id, String tokenHash, UserDto user, Instant expiryDate,
		// boolean revoked)

		User user=userRepository.findByUsername(appUser.username()).orElseThrow(()->new SecurityException("User not found"));
		
		RefreshToken rt = new RefreshToken(null, tokenHasher.hash(refreshToken), user,Instant.now().plusMillis(expirationMs), false);

		RefreshToken saved = refreshTokenRepository.save(rt);

		return Optional.of(refreshTokenMapper.toDto(saved));
	}

	@Override
	public Optional<RefreshTokenDto> verifyAndRotate(String refreshToken) {

		  String hash = tokenHasher.hash(refreshToken);

		  RefreshToken stored = refreshTokenRepository.findForUpdate(hash)
		    .orElseThrow(() -> new SecurityException("Refresh token inconnu"));

		  if (stored.isRevoked()) {
		    throw new SecurityException("Refresh token déjà utilisé");
		  }

		  if (stored.getExpiryDate().isBefore(Instant.now())) {
		    stored.setRevoked(true);
		    return Optional.empty();
		  }

		  stored.setRevoked(true);
		  refreshTokenRepository.save(stored);

		  return Optional.of(refreshTokenMapper.toDto(stored));
		}

	@Override
	public void revokeAll(String usermane) {
		
		User user=userRepository.findByUsername(usermane).orElseThrow(()->new SecurityException("User not found"));

		refreshTokenRepository.deleteAllByUser(user);
		
	}

	@Override
	public void revokeAll(Long userId) {
		
		User user=userRepository.findById(userId).orElseThrow(()->new SecurityException("User not found"));

		refreshTokenRepository.deleteAllByUser(user);
		
	}
	
}
