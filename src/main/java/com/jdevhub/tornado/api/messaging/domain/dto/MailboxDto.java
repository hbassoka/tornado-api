package com.jdevhub.tornado.api.messaging.domain.dto;

import java.time.Instant;
import java.util.Set;

import com.jdevhub.tornado.api.security.domain.dto.UserDto;

public record MailboxDto(Long id, UserDto user, Instant createdAt, Instant updatedAt, Set<MailboxMessageDto> messages) {

}
