package com.jdevhub.tornado.api.core.mailbox.web;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.core.mailbox.service.MessageService;
import com.jdevhub.tornado.api.core.security.service.UserService;
import com.jdevhub.tornado.api.shared.domain.dto.SujetDto;
import com.jdevhub.tornado.api.shared.domain.dto.TitreDto;
import com.jdevhub.tornado.api.shared.service.SujetService;
import com.jdevhub.tornado.api.shared.service.TitreService;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final UserService  userService;	
	private final MessageService messageService;
	private final TitreService titreService;
	private final SujetService sujetService;
   
	
   

	public ContactController(UserService userService, MessageService messageService, TitreService titreService,
			SujetService sujetService) {
		super();
		this.userService = userService;
		this.messageService = messageService;
		this.titreService = titreService;
		this.sujetService = sujetService;
	}



	@PostMapping
	public ResponseEntity<?> send(@RequestBody ContactRequest request) {
		
		
		logger.info("Debut envoie de message");

	    Optional<TitreDto> titreOpt =
	            titreService.findById(request.getTitreId());

	    if (titreOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("error", "Titre not found"));
	    }

	    Optional<SujetDto> sujetOpt =
	            sujetService.findById(request.getSujetId());

	    if (sujetOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("error", "Sujet not found"));
	    }

	    TitreDto titreDto = titreOpt.get();
	    SujetDto sujetDto = sujetOpt.get();
	    
	    
	    
	    
	 
	    // traitement métier ici (email, sauvegarde, etc.)

	    return ResponseEntity.ok(Map.of(
	            "message", "Message envoyé avec succès"
	    ));
	}



	
}
