package com.jdevhub.intranet.api.core.auth.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jdevhub.intranet.api.config.JwtUtil;
import com.jdevhub.intranet.api.core.auth.domain.dto.AppUser;
import com.jdevhub.intranet.api.core.auth.domain.dto.AuthResponse;
import com.jdevhub.intranet.api.core.auth.domain.dto.FacebookUser;
import com.jdevhub.intranet.api.core.auth.domain.dto.GoogleUser;
import com.jdevhub.intranet.api.core.auth.domain.dto.LoginRequest;
import com.jdevhub.intranet.api.core.auth.domain.dto.RefreshTokenRequest;
import com.jdevhub.intranet.api.core.auth.service.AuthService;
import com.jdevhub.intranet.api.core.security.domain.mapper.UserMapper;
import com.jdevhub.intranet.api.core.security.domain.model.User;
import com.jdevhub.intranet.api.core.security.domain.repository.UserRepository;
import com.jdevhub.intranet.api.core.security.service.RefreshTokenService;

import io.micrometer.common.util.StringUtils;

@Service
public class AuthServiceImpl extends DefaultOAuth2UserService implements AuthService {

	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	private static final String GOOGLE_TOKEN_INFO_URL ="https://oauth2.googleapis.com/tokeninfo?id_token=";
	
	private static final String GOOGLE_TOKEN_KEY="idToken";
			
	private static final String FACEBOOK_TOKEN_INFO_URL ="https://graph.facebook.com/me?fields=id,name,email&accessToken=";
	
	private static final String FACEBOOK_TOKEN_KEY="accessToken";
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private RestTemplate restTemplate ;

	public AuthServiceImpl() {
		super();

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	
	
	@Override
	public AuthResponse loginWithCredentials(LoginRequest request) {
		
		if(request==null || StringUtils.isBlank(request.username()) || StringUtils.isBlank(request.username())) {
			
			throw new BadCredentialsException("Invalid username or password");
		}
		
		Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
		
		if(!authentication.isAuthenticated()){
			
			throw new BadCredentialsException("Invalid username or password");
		}
			        
		 User user=(User)authentication.getPrincipal();
		 
		 AppUser appUser=UserMapper.toAppUser(user);		 
		 		 
		 String accessToken = jwtUtil.generateAccessToken(appUser);
         String refreshToken = jwtUtil.generateRefreshToken(appUser);
                 
         AuthResponse authResponse=new AuthResponse(accessToken,refreshToken,appUser);
         
        return authResponse;
		
	}
	
	@Override
	public GoogleUser loginWithGoogle(Map<String, String> request) {
		
		   String accessToken = request.get(GOOGLE_TOKEN_KEY);
        
		return this.verifyGoogleToken(accessToken);
		
	}

	@Override
	public FacebookUser loginWithFacebook(Map<String, String> request) {
		
		String accessToken = request.get(FACEBOOK_TOKEN_KEY);
		
		return this.verifyFacebookToken(accessToken);		
		
	}
	
	
	private FacebookUser verifyFacebookToken(String accessToken) {
	
		var url = FACEBOOK_TOKEN_INFO_URL + accessToken;

        try {
            return restTemplate.getForObject(url, FacebookUser.class);
            
        } catch (Exception e) {
        	throw new RuntimeException("AuthService : Invalid Facebook token"+e.getMessage());
        }
    }

	private GoogleUser verifyGoogleToken(String accessToken) {

		var url = GOOGLE_TOKEN_INFO_URL + accessToken;

		try {
			return restTemplate.getForObject(url, GoogleUser.class);

		} catch (Exception e) {
			
			throw new RuntimeException("AuthService : Invalid Google token"+e.getMessage());
		}
	}

	
	public AuthResponse refreshToken(RefreshTokenRequest request) {

	    if (request == null || request.refreshToken() == null) {
	        throw new SecurityException("Refresh token manquant");
	    }

	    String oldRefreshToken = request.refreshToken();

	    if (!jwtUtil.validateToken(oldRefreshToken) ||
	        !jwtUtil.isRefreshToken(oldRefreshToken)) {
	        throw new SecurityException("Refresh token invalide");
	    }

	    String username = jwtUtil.extractUsername(oldRefreshToken);
	    User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new SecurityException("Utilisateur introuvable"));

	    //  rotation
	    refreshTokenService.verifyAndRotate(oldRefreshToken);

	    AppUser appUser=UserMapper.toAppUser(user);	
		 
		String newAccessToken = jwtUtil.generateAccessToken(appUser);
        String newRefreshToken = jwtUtil.generateRefreshToken(appUser);                
        
	    refreshTokenService.create(appUser, newRefreshToken, jwtUtil.getRefreshTokenExpiration());	    
	    
	    return new AuthResponse(newAccessToken,newRefreshToken,appUser);
	}

	@Override
	public void logout(Long userId) {
		
		refreshTokenService.revokeAll(userId);
	}
	
	@Override
	public void logout(String username) {
		
		refreshTokenService.revokeAll(username);
	}
	
}
