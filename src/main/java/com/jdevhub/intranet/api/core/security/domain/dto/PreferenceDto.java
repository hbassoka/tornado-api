package com.jdevhub.intranet.api.core.security.domain.dto;

public record PreferenceDto(
		Long id, UserDto user, String language, String theme, boolean emailNotifications,
		boolean pushNotifications
) {}