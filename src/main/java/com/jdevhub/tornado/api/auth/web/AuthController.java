package com.jdevhub.tornado.api.auth.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.auth.domain.dto.AuthResponse;
import com.jdevhub.tornado.api.auth.domain.dto.FacebookUser;
import com.jdevhub.tornado.api.auth.domain.dto.GoogleUser;
import com.jdevhub.tornado.api.auth.domain.dto.LoginRequest;
import com.jdevhub.tornado.api.auth.domain.dto.RefreshTokenRequest;
import com.jdevhub.tornado.api.auth.domain.dto.RegisterRequest;
import com.jdevhub.tornado.api.auth.service.AuthService;
import com.jdevhub.tornado.api.security.domain.dto.UserDto;
import com.jdevhub.tornado.api.security.domain.dto.UserInformationDto;
import com.jdevhub.tornado.api.security.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final AuthService authService;

	private final UserService userService;
	
	public AuthController(final AuthService authService,final UserService userService) {

		this.authService = authService;
		this.userService = userService;
	}

	//
	/**
	 * Login : génère Access + Refresh Token
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginWithCredentials(@RequestBody LoginRequest request) {

		AuthResponse response = authService.loginWithCredentials(request);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/google")
	public ResponseEntity<?> loginWithGoogle(@RequestBody Map<String, String> request) throws Exception {

		GoogleUser googleUser = authService.loginWithGoogle(request);

		if (googleUser == null) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("error", " loginWithGoogle failed do to an invalid ID token"));
		}

		AuthResponse authResponse=null;
		
		UserDto userDto=null;
		
		if(this.userService.findByUsername(googleUser.email()).isPresent()){
			
			userDto=this.userService.findByUsername(googleUser.email()).orElseThrow();
			
		}else {
			
			userDto=this.userService.createOrUpdateGoogleUser(googleUser).orElseThrow(()->new RuntimeException("Unable to create or update Google user"));
		}
		
		// User exist
        LoginRequest loginRequest=new LoginRequest(userDto.username(), userDto.password());
        
        var res=this.loginWithCredentials(loginRequest);
		
		var authResp=res.getBody();
		
		authResponse=new AuthResponse(userDto.username(),userDto.password(),authResp.user());
		
		return ResponseEntity.ok(authResponse);
				
	}

	
	@PostMapping("/facebook")
	public ResponseEntity<?> loginWithFacebook(@RequestBody Map<String, String> request) {

		FacebookUser facebookUser = authService.loginWithFacebook(request);

		if (facebookUser == null) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("error", " loginWithFacebook failed do to an invalid ID token"));
		}
		      		
		UserDto userDto=null;
		
		 if(this.userService.findByUsername(facebookUser.email()).isPresent()) {
			 
			 userDto=this.userService.findByUsername(facebookUser.email()).orElseThrow(()->new RuntimeException("User not found"));
		 }else {
			 
			 userDto=this.userService.createOrUpdateFacebookUser(facebookUser).orElseThrow(()->new RuntimeException("Unable to create or update Google user"));
		 }
		 
		 LoginRequest loginRequest=new LoginRequest(userDto.username(),userDto.password());
			
		  var res=this.loginWithCredentials(loginRequest);
			
		  var authResp=res.getBody();
			
		  AuthResponse authResponse =new AuthResponse(authResp.accessToken(),authResp.refreshToken(),authResp.user());
			       
        return ResponseEntity.ok(authResponse);
	}


	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken2(@RequestBody RefreshTokenRequest request) {

        if (request.refreshToken() == null || request.refreshToken().isBlank()) {
			
			return ResponseEntity.badRequest().body("Refresh token is required");
		}
				
		
		AuthResponse response = authService.refreshToken(request);

		if (response == null) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("error", "Refresh token invalide ou expiré"));
		}

		return ResponseEntity.ok(response);
		
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

		
		if(request==null || request.username()==null || request.email()==null ) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("error", " No connection info"));
			
		}
		
		if(this.userService.existsByUsername(request.username())) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("info", " User already have an account"));
			
		}
		
		UserInformationDto userInformationDto=new UserInformationDto(request.noms(),request.prenoms(),request.username(),request.email(),request.password(),true,true);
				
		UserDto userDto=this.userService.register(userInformationDto).orElseThrow(()->new RuntimeException("Unable to create or update Google user"));
		
		if (userDto == null) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("error", " Account creation faied failed do to an invalid ID token"));
		}

		LoginRequest loginRequest=new LoginRequest(userDto.username(),userDto.password());
		
			
		
		return this.loginWithCredentials(loginRequest);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpServletResponse response) {

	    ResponseCookie cookie = ResponseCookie.from("refresh_token", "")
	        .httpOnly(true)
	        .secure(true)
	        .path("/")
	        .maxAge(0)
	        .build();

	    response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
	    return ResponseEntity.noContent().build();
	}
	
}
