package com.jdevhub.tornado.api.core.mailbox.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.core.mailbox.domain.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
List<Message> findByConversationIdOrderBySentAtAsc(Long conversationId);
}