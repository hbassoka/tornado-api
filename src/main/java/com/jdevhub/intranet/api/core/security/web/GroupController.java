package com.jdevhub.intranet.api.core.security.web;

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

import com.jdevhub.intranet.api.core.security.domain.dto.GroupDto;
import com.jdevhub.intranet.api.core.security.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final GroupService groupService;
	

	public GroupController(final GroupService groupService) {
		super();
		this.groupService = groupService;
		
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<GroupDto> getById(@PathVariable Long id) {
		
		return groupService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public List<GroupDto> getAll() {
		return groupService.findAll();
	}

	@GetMapping("/pages")
	public Page<GroupDto> findUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String direction, @RequestParam(defaultValue = "") String search) {
		Pageable pageable = PageRequest.of(page, size,
				Sort.by(direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

		return groupService.search(search, pageable);
	}

}
