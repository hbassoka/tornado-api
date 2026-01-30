package com.jdevhub.tornado.api.core.mailbox.web;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.core.mailbox.domain.dto.MailboxDto;
import com.jdevhub.tornado.api.core.mailbox.service.MailboxService;

@RestController
@RequestMapping("/api/mailboxes")
public class MailboxController {

	private final MailboxService mailboxService;


	public MailboxController(final MailboxService mailboxService) {
	this.mailboxService = mailboxService;
	}
	
	@PostMapping("/{userId}")
    public ResponseEntity<MailboxDto> createUserMailbox(@PathVariable Long userId, @RequestParam String name) {
		MailboxDto m = mailboxService.createMailbox(userId, name);
        return ResponseEntity.created(URI.create("/api/mailboxes/" + m.id())).body(m);
    }

	@GetMapping
    public ResponseEntity<List<MailboxDto>> getMailboxes() {
        return ResponseEntity.ok(mailboxService.getAllMailboxes());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<MailboxDto>> getMailboxeByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(mailboxService.getMailboxeByUser(userId));
    }
    
    @GetMapping("/pages")
	public Page<MailboxDto> findUsers(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "id") String sortBy,
	        @RequestParam(defaultValue = "asc") String direction,
	        @RequestParam(defaultValue = "") String search
	) {
	    Pageable pageable = PageRequest.of(
	        page,
	        size,
	        Sort.by(direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy)
	    );

	    return mailboxService.search(search, pageable);
	}

}
