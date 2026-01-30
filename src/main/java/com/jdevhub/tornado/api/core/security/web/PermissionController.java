package com.jdevhub.tornado.api.core.security.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.core.security.domain.dto.PermissionDto;
import com.jdevhub.tornado.api.core.security.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final PermissionService permissionService;

	public PermissionController(PermissionService permissionService) {
		super();
		this.permissionService = permissionService;
	}

	@GetMapping("/pages")
	public Page<PermissionDto> findRoles(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String direction, @RequestParam(defaultValue = "") String search) {
		Pageable pageable = PageRequest.of(page, size,
				Sort.by(direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

		return this.permissionService.search(search, pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PermissionDto> findById(@PathVariable Long id) {

		return permissionService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{name}")
	public ResponseEntity<PermissionDto> getOne(@PathVariable String name) {
		return permissionService.findByName(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public List<PermissionDto> getAll() {
		return permissionService.findAll();
	}
}
