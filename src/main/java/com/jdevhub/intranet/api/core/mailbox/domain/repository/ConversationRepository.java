package com.jdevhub.intranet.api.core.mailbox.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.intranet.api.core.mailbox.domain.model.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {}