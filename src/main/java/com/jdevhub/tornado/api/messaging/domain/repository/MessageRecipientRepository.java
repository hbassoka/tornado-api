package com.jdevhub.tornado.api.messaging.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.messaging.domain.model.MessageRecipient;

@Repository
public interface MessageRecipientRepository extends JpaRepository<MessageRecipient, Long> {
List<MessageRecipient> findByRecipientId(Long userId);
}
