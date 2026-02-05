package com.jdevhub.tornado.api.messaging.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.messaging.domain.dto.MessageDto;
import com.jdevhub.tornado.api.messaging.domain.dto.MessageRecipientDto;
import com.jdevhub.tornado.api.messaging.domain.dto.SendMessageRequest;
import com.jdevhub.tornado.api.messaging.service.MessageService;
import com.jdevhub.tornado.api.security.domain.dto.UserDto;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	private final MessageService messageService;

	public MessageController(final MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping("/send")
	public ResponseEntity<MessageDto> sendMessage(@RequestBody SendMessageRequest req) {
				
		//MessageDto(Long id, ConversationDto conversation, UserDto sender, MessageDto replyTo, String body, Instant sentAt,String priority, boolean draft)
		
		//MessageDto message = new MessageDto(null,null,req.getSender(),null,req.getBody(),java.time.Instant.now(),req.getPriority() == null ? "normal" : req.getPriority(),false);

		MessageDto message = new MessageDto(null, null, null, null, null, null, false, null, null, null);
		List<MessageRecipientDto> recipients = new ArrayList<>();
		
		
		for (UserDto recipient : req.getRecipients()) {
			
			//MessageRecipientDto(Long id, MessageDto messageDto, UserDto recipient, String recipientType)
			MessageRecipientDto mr= new MessageRecipientDto(null,message,recipient,"TO");
			
			//recipients.add(MessageRecipient.builder().recipient(r).recipientType("TO").build());
			recipients.add(mr);
		}

		MessageDto sent = messageService.send(message, recipients);
		return ResponseEntity.ok(sent);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MessageDto> getMessage(@PathVariable Long id) {
		
		return messageService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/conversation/{conversationId}")
	public ResponseEntity<List<MessageDto>> getConversation(@PathVariable Long conversationId) {
		
		return ResponseEntity.ok(messageService.findByConversation(conversationId));
	}

}
