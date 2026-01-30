package com.jdevhub.tornado.api.core.auth.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.jdevhub.tornado.api.core.auth.domain.dto.AuthResponse;
import com.jdevhub.tornado.api.core.auth.domain.dto.FacebookUser;
import com.jdevhub.tornado.api.core.auth.domain.dto.GoogleUser;
import com.jdevhub.tornado.api.core.auth.domain.dto.LoginRequest;
import com.jdevhub.tornado.api.core.auth.domain.dto.RefreshTokenRequest;

public interface AuthService extends   OAuth2UserService<OAuth2UserRequest, OAuth2User> , UserDetailsService{

	
	AuthResponse loginWithCredentials(LoginRequest request);	
	
	GoogleUser   loginWithGoogle(Map<String, String> request);	
	
	FacebookUser loginWithFacebook(Map<String, String> request);
	
	AuthResponse refreshToken(RefreshTokenRequest request);
	
	
	void logout(Long userId);
	
	 void logout(String username);
	

}
 