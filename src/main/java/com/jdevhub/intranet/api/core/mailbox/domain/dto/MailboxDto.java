package com.jdevhub.intranet.api.core.mailbox.domain.dto;

import java.time.Instant;
import java.util.Set;

import com.jdevhub.intranet.api.core.security.domain.dto.UserDto;

public record MailboxDto(Long id, UserDto user, Instant createdAt, Instant updatedAt, Set<MailboxMessageDto> messages) {

}
