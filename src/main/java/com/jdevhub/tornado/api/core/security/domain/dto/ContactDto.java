package com.jdevhub.tornado.api.core.security.domain.dto;

import java.time.LocalDateTime;

import com.jdevhub.tornado.api.shared.domain.dto.TitreDto;

public record ContactDto(Long id, TitreDto titre, String nom, String prenom, String email, String telephone,
		LocalDateTime createdAt, LocalDateTime updatedAt) {

	
	
}
