package com.jdevhub.intranet.api.config;

import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jdevhub.intranet.api.core.auth.service.AuthService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;


@Configuration
public class SecurityConfig {

	protected static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Value("${app.security.jwt.secret-key}")
	private String secretKey;
	

	@Value("${app.security.jwt.expiration}")
	private long jwtExpiration;
	
	@Value("${app.security.jwt.refresh-token.expiration}")
	private long refreshTokenExpiration;
	
	@Value("${app.frontend-success-url}")
	private String frontendSuccessUrl;
		
	@Autowired
	private JwtAuthFilter  jwtAuthFilter;
	
	@Autowired
	private AuthService  authService;
	
	@Bean	
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
		
        CorsConfiguration config = new CorsConfiguration();
        
       config.setAllowedOrigins(List.of("http://localhost:4200","http://127.0.0.1:4200","https://www.jdevhub.com","https://pprod.jdevhub.com")); // frontend Angular        
      // config.addAllowedOrigin("http://localhost:4200"); // ton front Angular
       //config.addAllowedOriginPattern("*");
       config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
       config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
       config.setAllowCredentials(true); // obligatoire pour cookies OAuth2
       //config.setExposedHeaders(List.of("Authorization"));
        
        
       // config.addAllowedOrigin("http://localhost:4200"); // ton front Angular
        //config.addAllowedHeader("*");
        //config.addAllowedMethod("*");
        //config.setAllowCredentials(true); // si tu utilises des cookies / JWT en header
       
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
	
	
	@Bean
    public JwtEncoder jwtEncoder() {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");        
        return new NimbusJwtEncoder(new ImmutableSecret<>(key));
    }
	
    public SecurityConfig() {
		super();
		
		
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
          	
   
    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, AuthService authService) throws Exception {
		http
		
		    .cors(Customizer.withDefaults())
		    // Désactivation CSRF car API REST
		    .csrf(csrf -> csrf.disable())		
		    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
		    		   
		     // Ajout du filtre JWT avant UsernamePasswordAuthenticationFilter
		     .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		     
		     // Autorisations		        
		    .authorizeHttpRequests(auth -> auth
		    	.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		    	.requestMatchers(		    			    
		    			    "/api/oauth2/**",       // callback OAuth2			                    
		                    "/api/v3/api-docs/**",
		                    "/api/swagger-ui/**", 
		                    "/api/swagger-ui.html",
		                    "/api/health",
		                    "/api/ws/**",		                   
		                    "/api/actuator",
		    			    "/api/auth/facebook",
		    			    "/api/auth/google",	
		    			    "/api/auth/login",	
		    			    "/api/auth/logout",
		    			    "/api/auth/refreshToken",	
		                    "/api/auth/**",       		                   
		                    "/api/domaines",
		                    "/api/titres",    
		                    "/api/sujets",
		                    "/api/parametres",
		                    "/api/contact"
		                ).permitAll()		    	
				.anyRequest().authenticated());			

		return http.build();
	}
    
    
    
}
