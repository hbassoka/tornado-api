package com.jdevhub.tornado.api.messaging.domain.dto;

import com.jdevhub.tornado.api.security.domain.dto.UserDto;

public record MessageRecipientDto(Long id, MessageDto messageDto, UserDto recipient, String recipientType) {

}
