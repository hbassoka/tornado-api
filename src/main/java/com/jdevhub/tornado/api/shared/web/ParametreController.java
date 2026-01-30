package com.jdevhub.tornado.api.shared.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.shared.domain.dto.ParametreDto;
import com.jdevhub.tornado.api.shared.service.CustomPropertyService;

@RestController
@RequestMapping("/api/parameters")
public class ParametreController {

	private CustomPropertyService customPropertyService;

	public ParametreController(CustomPropertyService customPropertyService) {
		super();
		this.customPropertyService = customPropertyService;
	}
	
	
	@GetMapping
	public List<ParametreDto> getAll() {
		return customPropertyService.findAll();
	}
	
	@GetMapping("/{key}")
    public ResponseEntity<String> getValue(@PathVariable String key) {
        String value = customPropertyService.get(key);
        return value != null ? ResponseEntity.ok(value) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{key}")
    public ResponseEntity<Void> update(
            @PathVariable String key,
            @RequestBody String value) {
    	customPropertyService.update(key, value);
        return ResponseEntity.ok().build();
    }
}
