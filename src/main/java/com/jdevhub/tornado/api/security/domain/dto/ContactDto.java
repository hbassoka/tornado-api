package com.jdevhub.tornado.api.security.domain.dto;

import java.time.LocalDateTime;

import com.jdevhub.tornado.api.common.domain.dto.TitreDto;

public record ContactDto(Long id, TitreDto titre, String nom, String prenom, String email, String telephone,
		LocalDateTime createdAt, LocalDateTime updatedAt) {

	
	
}
