package com.jdevhub.intranet.api.core.mailbox.domain.dto;

import com.jdevhub.intranet.api.core.security.domain.dto.UserDto;

public record MessageRecipientDto(Long id, MessageDto messageDto, UserDto recipient, String recipientType) {

}
