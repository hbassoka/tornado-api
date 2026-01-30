package com.jdevhub.intranet.api.core.mailbox.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.intranet.api.core.mailbox.domain.model.MailboxMessage;
import com.jdevhub.intranet.api.core.mailbox.domain.model.MailboxMessageId;

@Repository
public interface MailboxMessageRepository extends JpaRepository<MailboxMessage, MailboxMessageId> {
List<MailboxMessage> findByMailboxId(Long mailboxId);
}
