package com.jdevhub.intranet.api.core.mailbox.service;

import java.util.List;
import java.util.Optional;

import com.jdevhub.intranet.api.core.mailbox.domain.dto.MessageDto;
import com.jdevhub.intranet.api.core.mailbox.domain.dto.MessageRecipientDto;

public interface MessageService {
	
	MessageDto send(MessageDto message, List<MessageRecipientDto> recipients);

	Optional<MessageDto> findById(Long id);

	List<MessageDto> findByConversation(Long conversationId);
}