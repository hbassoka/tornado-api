package com.jdevhub.tornado.api.shared.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.shared.domain.dto.SujetDto;
import com.jdevhub.tornado.api.shared.service.SujetService;

@RestController
@RequestMapping("/api/sujets")
public class SujetController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private final SujetService sujetService;
		
	

	public SujetController(final SujetService sujetService) {
		super();
		this.sujetService = sujetService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<SujetDto> getOne(@PathVariable Long id) {
		return sujetService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public List<SujetDto> getAll() {
		return sujetService.findAll();
	}
}
