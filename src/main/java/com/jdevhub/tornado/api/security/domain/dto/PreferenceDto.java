package com.jdevhub.tornado.api.security.domain.dto;

public record PreferenceDto(
		Long id, UserDto user, String language, String theme, boolean emailNotifications,
		boolean pushNotifications
) {}