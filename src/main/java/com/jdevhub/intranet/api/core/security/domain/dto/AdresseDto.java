package com.jdevhub.intranet.api.core.security.domain.dto;

public record AdresseDto(Long id, String ligne1, String ligne2, String ligne3, String codePostal,
		String ville, String region, String pays) {

}
