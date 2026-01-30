package com.jdevhub.intranet.api.core.mailbox.domain.dto;

import com.jdevhub.intranet.api.core.security.domain.dto.UserDto;

public record LabelDto(Long id, UserDto userDto, String name) {

}
