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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.core.security.domain.dto.ProfileDto;
import com.jdevhub.tornado.api.core.security.service.ProfileService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ProfileService profileService;

	public ProfileController(final ProfileService profileService) {
		super();
		this.profileService = profileService;

	}

	@GetMapping("/me")
	public ResponseEntity<ProfileDto> getProfiles(Authentication authentication) {
		return profileService.getByUsername(authentication.getName()).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{username}")
	public ResponseEntity<ProfileDto> getByUsername(String username) {
		return profileService.getByUsername(username).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProfileDto> getyId(@PathVariable Long id) {

		return profileService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public List<ProfileDto> getAll() {
		return profileService.findAll();
	}

	@GetMapping("/pages")
	public Page<ProfileDto> findUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String direction, @RequestParam(defaultValue = "") String search) {
		Pageable pageable = PageRequest.of(page, size,
				Sort.by(direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

		return profileService.search(search, pageable);
	}
	
	@PutMapping
	public ResponseEntity<?> createOrUpdate(@RequestBody ProfileDto request) {

	    try {
	    	ProfileDto response = profileService.createOrUpdate(request).orElseThrow();

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

}
