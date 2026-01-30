package com.jdevhub.tornado.api.core.mailbox.domain.dto;

import java.time.Instant;

public record ConversationDto(Long id, String subjectMain, Instant createdAt) {

}
