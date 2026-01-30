package com.jdevhub.intranet.api.core.security.domain.dto;

import java.time.LocalDate;

import com.jdevhub.intranet.api.shared.domain.dto.TitreDto;

public record ProfileDto(Long id, UserDto user, TitreDto titre, AdresseDto adresse, String nom, String prenom,
		String telephone, String photoUrl, LocalDate birthdate
) {}