package com.jdevhub.tornado.api.security.domain.dto;

import java.time.LocalDate;

import com.jdevhub.tornado.api.common.domain.dto.TitreDto;

public record ProfileDto(Long id, UserDto user, TitreDto titre, AdresseDto adresse, String nom, String prenom,
		String telephone, String photoUrl, LocalDate birthdate
) {}