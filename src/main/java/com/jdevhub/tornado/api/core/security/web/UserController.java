package com.jdevhub.tornado.api.core.security.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.core.security.domain.dto.UserDto;
import com.jdevhub.tornado.api.core.security.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	private final UserService userService;

	public UserController(final UserService userService) {
		this.userService = userService;
	}

	
	
	@GetMapping
	public List<UserDto> getAll() {
		return userService.findAll();
	}
	

	@GetMapping("/me")
	public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {

	    String username = authentication.getName();

	    return userService.findByUsername(username).map(user -> ResponseEntity.ok(user))
	            .orElse(ResponseEntity.notFound().build());
	}

		

	@GetMapping("/pages")
	public Page<UserDto> findUsers(
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

	    return userService.search(search, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable Long id) {
		
		return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping
	public ResponseEntity<?> createOrUpdate(@RequestBody UserDto request) {

	    try {
	    	UserDto response = userService.createOrUpdate(request).orElseThrow();

	        if (response == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(Map.of("error", "Failed to create or update message"));
	        }

	        // Si l'id est null → création
	        if (request.id() == null) {
	            return ResponseEntity.status(HttpStatus.CREATED).body(response);
	        }

	        // Sinon → mise à jour
	        return ResponseEntity.ok(response);

	    } catch (Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of(
	                        "error", "Internal error while processing message",
	                        "details", ex.getMessage()
	                ));
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{id}/add-role")
	public ResponseEntity<Void> addRole(@PathVariable Long id, @RequestParam String role) {
		//userService.addRoleToUser(id, role);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/remove-role")
	public ResponseEntity<Void> removeRole(@PathVariable Long id, @RequestParam String role) {
		//userService.removeRoleFromUser(id, role);
		return ResponseEntity.ok().build();
	}
	
}
