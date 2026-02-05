package com.jdevhub.tornado.api.auth.domain.dto;

public record AppUser(Long id, String username,String [] authorities, String []  roles,String []  permissions) {

}
