package com.jdevhub.tornado.api.common.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.common.domain.dto.TitreDto;
import com.jdevhub.tornado.api.common.service.TitreService;

@RestController
@RequestMapping("/api/titres")
public class TitreController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final TitreService titreService;
	

	public TitreController(final TitreService titreService) {
		super();
		this.titreService = titreService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<TitreDto> getOne(@PathVariable Long id) {
		return titreService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public List<TitreDto> getAll() {
		return titreService.findAll();
	}
	
}
