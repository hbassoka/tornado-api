package com.jdevhub.tornado.api.security.domain.dto;

public record ConfidentialiteDto(
		Long id, UserDto user, String profileVisibility, boolean showEmail, boolean showTelephone,
		boolean showBirthdate, boolean dataProcessingConsent, boolean marketingConsent, boolean thirdPartySharing
) {}