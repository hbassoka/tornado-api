package com.jdevhub.tornado.api.core.mailbox.domain.dto;

import com.jdevhub.tornado.api.core.security.domain.dto.UserDto;

public record LabelDto(Long id, UserDto userDto, String name) {

}
