package com.jdevhub.intranet.api.core.mailbox.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.intranet.api.core.mailbox.domain.model.MessageRecipient;

@Repository
public interface MessageRecipientRepository extends JpaRepository<MessageRecipient, Long> {
List<MessageRecipient> findByRecipientId(Long userId);
}
