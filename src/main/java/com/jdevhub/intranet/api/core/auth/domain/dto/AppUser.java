package com.jdevhub.intranet.api.core.auth.domain.dto;

public record AppUser(Long id, String username,String [] authorities, String []  roles,String []  permissions) {

}
