package com.jdevhub.tornado.api.security.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.security.domain.dto.RoleDto;
import com.jdevhub.tornado.api.security.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	private final RoleService roleService;

	
	
	
	public RoleController(final RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@GetMapping
    public List<RoleDto> getAll() {
        return roleService.findAll();
    }

	@GetMapping("/pages")
	public Page<RoleDto> findRoles(
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

	    return roleService.search(search, pageable);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getOne(@PathVariable Long id) {
        return roleService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{name}")
    public ResponseEntity<RoleDto> getOne(@PathVariable String name) {
        return roleService.findByName(name)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RoleDto role) {
    	    	
		if (!roleService.findById(id).isPresent())
			return ResponseEntity.notFound().build();		
		roleService.createOrUpdate(role);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		roleService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{id}/add-permission")
	public ResponseEntity<Void> addRPermission(@PathVariable Long id, @RequestParam String permissionName) {
		roleService.addPermissionToRole(id, permissionName);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/remove-permission")
	public ResponseEntity<Void> removePermission(@PathVariable Long id, @RequestParam String permissionName) {
		roleService.removePermissionFromRole(id, permissionName);
		return ResponseEntity.ok().build();
	}
}
