package com.jdevhub.tornado.api.core.mailbox.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jdevhub.tornado.api.core.mailbox.domain.dto.MailboxDto;
import com.jdevhub.tornado.api.core.mailbox.domain.mapper.MailboxMapper;
import com.jdevhub.tornado.api.core.mailbox.domain.model.Mailbox;
import com.jdevhub.tornado.api.core.mailbox.domain.repository.MailboxRepository;
import com.jdevhub.tornado.api.core.mailbox.service.MailboxService;
import com.jdevhub.tornado.api.core.security.domain.model.User;
import com.jdevhub.tornado.api.core.security.domain.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class MailboxServiceImpl implements MailboxService {

	
	
	private final MailboxRepository mailboxRepository;
	private final UserRepository userRepository;

	private final MailboxMapper mailboxMapper;

	public MailboxServiceImpl(final MailboxRepository mailboxRepository,final MailboxMapper mailboxMapper, final  UserRepository userRepository) {
	this.mailboxRepository = mailboxRepository;
	this.mailboxMapper = mailboxMapper;
	this.userRepository = userRepository;
	}
	@Override
	public MailboxDto createMailbox(Long userId, String name) {
		
		User user=userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
		
		Mailbox mailbox=new Mailbox();
		mailbox.setUser(user);
		

		return mailboxMapper.toDto(mailboxRepository.save(mailbox));
	}

	@Override
	public List<MailboxDto> getMailboxeByUser(Long userId) {
		
		return mailboxRepository.findByUserId(userId).stream().map(u->mailboxMapper.toDto(u)).toList();
	}
	@Override
	public List<MailboxDto> getAllMailboxes() {
		
		return mailboxRepository.findAll().stream().map(u->mailboxMapper.toDto(u)).toList();
	}

	@Override
	public Page<MailboxDto> search(String keyword, Pageable pageable) {

		return mailboxRepository.search(keyword, pageable).map(mailboxMapper::toDto);
	}

	
}
