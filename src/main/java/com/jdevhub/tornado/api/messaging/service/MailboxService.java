package com.jdevhub.tornado.api.messaging.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jdevhub.tornado.api.messaging.domain.dto.MailboxDto;

public interface MailboxService {
	
	MailboxDto createMailbox(Long userId, String name);

	List<MailboxDto> getMailboxeByUser(Long userId);
	
	
	List<MailboxDto> getAllMailboxes();
	
	Page<MailboxDto> search(String keyword, Pageable pageable);
}