package com.jdevhub.tornado.api.core.security.domain.dto;

public record PreferenceDto(
		Long id, UserDto user, String language, String theme, boolean emailNotifications,
		boolean pushNotifications
) {}