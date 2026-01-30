package com.jdevhub.tornado.api.core.mailbox.domain.dto;

import com.jdevhub.tornado.api.core.security.domain.dto.UserDto;

public record MessageRecipientDto(Long id, MessageDto messageDto, UserDto recipient, String recipientType) {

}
