package com.jdevhub.intranet.api.core.security.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserInformationDto(String noms, String prenoms, String username, @NotEmpty String email,
		String password, Boolean conditionAccepted, Boolean newsletterAccepted) {

}
