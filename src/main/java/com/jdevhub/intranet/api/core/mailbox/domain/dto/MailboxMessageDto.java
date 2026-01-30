package com.jdevhub.intranet.api.core.mailbox.domain.dto;

import java.time.Instant;

import com.jdevhub.intranet.api.core.mailbox.domain.model.MailboxMessageId;

public record MailboxMessageDto(MailboxMessageId id, MailboxDto mailbox, MessageDto message, boolean read, boolean flagged,
		Instant receivedAt) {

}
