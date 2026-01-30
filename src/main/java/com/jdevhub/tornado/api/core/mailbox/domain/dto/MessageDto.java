package com.jdevhub.tornado.api.core.mailbox.domain.dto;

import java.time.Instant;
import java.util.Set;


public record MessageDto(
	    Long id,
	    Long conversationId,
	    Long senderId,
	    Long replyToMessageId,
	    String body,
	    String priority,
	    boolean draft,
	    Instant sentAt,
	    Set<AttachmentDto> attachments,
	    Set<MessageLabelDto> labels
	) {}