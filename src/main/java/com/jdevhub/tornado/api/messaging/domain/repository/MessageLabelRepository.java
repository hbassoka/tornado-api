package com.jdevhub.tornado.api.messaging.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.messaging.domain.model.MessageLabel;

@Repository
public interface MessageLabelRepository extends JpaRepository<MessageLabel, Long> {
List<MessageLabel> findByMessageId(Long messageId);
}