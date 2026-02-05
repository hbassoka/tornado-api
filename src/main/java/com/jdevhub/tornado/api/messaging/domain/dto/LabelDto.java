package com.jdevhub.tornado.api.messaging.domain.dto;

import com.jdevhub.tornado.api.security.domain.dto.UserDto;

public record LabelDto(Long id, UserDto userDto, String name) {

}
