package com.jdevhub.tornado.api.core.mailbox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdevhub.tornado.api.core.mailbox.domain.dto.MessageDto;
import com.jdevhub.tornado.api.core.mailbox.domain.dto.MessageRecipientDto;
import com.jdevhub.tornado.api.core.mailbox.domain.mapper.ConversationMapper;
import com.jdevhub.tornado.api.core.mailbox.domain.mapper.MessageMapper;
import com.jdevhub.tornado.api.core.mailbox.domain.mapper.MessageRecipientMapper;
import com.jdevhub.tornado.api.core.mailbox.domain.model.Message;
import com.jdevhub.tornado.api.core.mailbox.domain.model.MessageRecipient;
import com.jdevhub.tornado.api.core.mailbox.domain.repository.ConversationRepository;
import com.jdevhub.tornado.api.core.mailbox.domain.repository.MailboxMessageRepository;
import com.jdevhub.tornado.api.core.mailbox.domain.repository.MailboxRepository;
import com.jdevhub.tornado.api.core.mailbox.domain.repository.MessageRecipientRepository;
import com.jdevhub.tornado.api.core.mailbox.domain.repository.MessageRepository;
import com.jdevhub.tornado.api.core.mailbox.service.MessageService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {

	private final MessageRepository messageRepository;
	private final MessageRecipientRepository recipientRepository;
	private final ConversationRepository conversationRepository;
	private final MailboxRepository mailboxRepository;
	private final MailboxMessageRepository mailboxMessageRepository;

	@Autowired
	private ConversationMapper conversationMapper;

	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private MessageRecipientMapper messageRecipientMapper;

	public MessageServiceImpl(MessageRepository messageRepository, MessageRecipientRepository recipientRepository,
			ConversationRepository conversationRepository, MailboxRepository mailboxRepository,
			MailboxMessageRepository mailboxMessageRepository) {

		this.messageRepository = messageRepository;
		this.recipientRepository = recipientRepository;
		this.conversationRepository = conversationRepository;
		this.mailboxRepository = mailboxRepository;
		this.mailboxMessageRepository = mailboxMessageRepository;
	}

	@Override
	public Optional<MessageDto> findById(Long id) {
		return messageRepository.findById(id).map(m -> messageMapper.toDto(m));
	}

	@Override
	public List<MessageDto> findByConversation(Long conversationId) {

		return messageRepository.findByConversationIdOrderBySentAtAsc(conversationId).stream().map(messageMapper::toDto)
				.toList();
	}

	@Override
	@Transactional
	public MessageDto send(MessageDto messageDto, List<MessageRecipientDto> recipients) {
		
		Message message=messageMapper.toEntity(messageDto);
		/*
		if (message.getConversation() == null) {
			
			Conversation conv = new Conversation();
			conv.setSubjectMain(null);
			conv.setCreatedAt(java.time.Instant.now());
			message.setConversation(conv);
		}
		
		message.setSentAt(java.time.Instant.now());
		message.setDraft(false);
		
		*/
		Message savedMessage = messageRepository.save(message);
		
		// save recipients
		
		for (MessageRecipientDto r : recipients) {
			
			MessageRecipient mr =messageRecipientMapper.toEntity(r);
			mr.setMessage(savedMessage);			
			recipientRepository.save(mr);
			
			// place message in recipient's inbox mailbox
			/*
			mailboxRepository.findByUserIdAndName(r.recipient().id(), "Inbox").ifPresent(mailbox->{
				
				MailboxMessage mailboxMessage =new  MailboxMessage();
				
				mailboxMessage.setMailbox(mailbox);
				mailboxMessage.setMessage(savedMessage);
				mailboxMessage.setReceivedAt(java.time.Instant.now());
				mailboxMessage.setRead(false);
				mailboxMessage.setFlagged(false);
				
				mailboxMessageRepository.save(mailboxMessage);
			});
			*/
			 // place message in sender's Sent mailbox
			/*
             if(messageDto.sender()!=null) {
            	 
            	 mailboxRepository.findByUserIdAndName(messageDto.sender().id(), "Inbox").ifPresent(mailbox->{
     				
     				MailboxMessage mailboxMessage =new  MailboxMessage();
     				
     				mailboxMessage.setMailbox(mailbox);
     				mailboxMessage.setMessage(savedMessage);
     				mailboxMessage.setReceivedAt(java.time.Instant.now());
     				mailboxMessage.setRead(false);
     				mailboxMessage.setFlagged(false);
     				
     				mailboxMessageRepository.save(mailboxMessage);
     			});
     			
             }	
             */		
		}
		
		
		return messageMapper.toDto(savedMessage);
	}

}
