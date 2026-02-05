package com.jdevhub.tornado.api.messaging.domain.dto;

import java.time.Instant;

public record ConversationDto(Long id, String subjectMain, Instant createdAt) {

}
