package com.jdevhub.tornado.api.core.mailbox.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.core.mailbox.domain.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
List<Attachment> findByMessageId(Long messageId);
}