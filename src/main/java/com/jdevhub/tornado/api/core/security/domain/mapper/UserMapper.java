package com.jdevhub.tornado.api.core.security.domain.mapper;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.core.auth.domain.dto.AppUser;
import com.jdevhub.tornado.api.core.auth.domain.dto.RegisterRequest;
import com.jdevhub.tornado.api.core.security.domain.dto.UserDto;
import com.jdevhub.tornado.api.core.security.domain.model.Group;
import com.jdevhub.tornado.api.core.security.domain.model.Permission;
import com.jdevhub.tornado.api.core.security.domain.model.Role;
import com.jdevhub.tornado.api.core.security.domain.model.User;


@Mapper(
		componentModel = "spring",
		uses=GroupMapper.class
	)
public interface UserMapper {

   UserDto toDto(User entity);
    
   User toEntity(UserDto dto) ;
   
   UserDto toDto(RegisterRequest request);
   
   public static AppUser toAppUser(User user) {
	   //AppUser(Long id, String username, List<String> roles,List<String>permissions) 
	   
	   String[]   roleNames=UserMapper.getAllRoles(user).stream().map(r->r.getName()).toArray(String[]::new);
	   
	   String[]   permissionNames=UserMapper.getAllPermissions(user).stream().map(r->r.getName()).toArray(String[]::new);
	   
	   String[]  authorities = user.getAuthorities().stream().map(a -> a.getAuthority()).toArray(String[]::new);
	   
	   return new AppUser(user.getId(),user.getUsername(), roleNames,permissionNames,authorities);
   }
   
  
   private static Set<Role> getAllRoles(User user) {
	    Set<Role> allRoles = new HashSet<>(); // Start with the roles assigned directly to the user

	    for (Group group : user.getGroups()) {
	        allRoles.addAll(group.getRoles()); // Add roles from the groups the user belongs to
	    }
	    return allRoles;
	}
	
  
   private static Set<Permission> getAllPermissions(User user) {
			
		
	    Set<Permission> allPermissions = new HashSet<>(); // Start with the permissions assigned directly to the user

	  
	    // Include permissions from roles associated with the user
	    for (Role role : getAllRoles(user)) {
	        allPermissions.addAll(role.getPermissions()); // Add permissions granted by the roles
	    }
	    return allPermissions;
	}  
    
   
    
  
}

