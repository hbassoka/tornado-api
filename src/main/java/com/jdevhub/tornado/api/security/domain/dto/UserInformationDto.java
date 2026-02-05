package com.jdevhub.tornado.api.security.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserInformationDto(String noms, String prenoms, String username, @NotEmpty String email,
		String password, Boolean conditionAccepted, Boolean newsletterAccepted) {

}
