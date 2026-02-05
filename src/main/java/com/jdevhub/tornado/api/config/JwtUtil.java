package com.jdevhub.tornado.api.config;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jdevhub.tornado.api.auth.domain.dto.AppUser;
import com.jdevhub.tornado.api.auth.domain.dto.FacebookUser;
import com.jdevhub.tornado.api.auth.domain.dto.GoogleUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil  {

	@Value("${app.security.jwt.secret-key}")
	private String secretKey;

	@Value("${app.security.jwt.expiration}")
	private long accessTokenExpiration;

	@Value("${app.security.jwt.refresh-token.expiration}")
	private long refreshTokenExpiration;

	@Value("${app.security.oauth2.google.client-id}")
	private String googleClientId;

	@Value("${app.security.oauth2.google.client-secret}")
	private String googleClientSecret;

	@Value("${app.security.oauth2.facebook.client-id}")
	private String facebookClientId;

	@Value("${app.security.oauth2.facebook.client-secret}")
	private String facebookClientSecret;
	
	
	public String getSecretKey() {
		return secretKey;
	}

	public long getAccessTokenExpiration() {
		return accessTokenExpiration;
	}

	public long getRefreshTokenExpiration() {
		return refreshTokenExpiration;
	}

	public String getGoogleClientId() {
		return googleClientId;
	}

	public String getGoogleClientSecret() {
		return googleClientSecret;
	}

	public String getFacebookClientId() {
		return facebookClientId;
	}

	public String getFacebookClientSecret() {
		return facebookClientSecret;
	}

	public JwtUtil() {
		super();
		
	}

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	/*
	 * ========================= ACCESS TOKEN =========================
	 */
	public String generateAccessToken(AppUser user) {
		return Jwts.builder().setSubject(user.username())
				.claim("id", user.id())
				.claim("jti", UUID.randomUUID().toString())
				.claim("username", user.username())
				.claim("authorities", user.authorities())
				.claim("roles", user.roles())
				.claim("permissions", user.permissions())
				.claim("token_type", "ACCESS")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	/*
	 * ========================= REFRESH TOKEN =========================
	 */
	public String generateRefreshToken(AppUser user) {
		return Jwts.builder().setSubject(user.username())
				.claim("id", user.id())
				.claim("jti", UUID.randomUUID().toString())
				.claim("username", user.username())				
				.claim("authorities", user.authorities())
				.claim("roles", user.roles())
				.claim("permissions", user.permissions())
				.claim("token_type", "REFRESH")
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	
    public  String generateGoogleToken(GoogleUser user) {
        return Jwts.builder()
                .setSubject(user.name())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(Keys.hmacShaKeyFor(googleClientSecret.getBytes()))
                .compact();
    }
    
    public String generateFacebookToken(FacebookUser user) {
    	return Jwts.builder()
                .setSubject(user.name())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(Keys.hmacShaKeyFor(facebookClientSecret.getBytes()))
                .compact();
    }
	/*
	 * ========================= VALIDATION =========================
	 */
	public boolean validateToken(String token) {
		try {
			parseToken(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public boolean isRefreshToken(String token) {
		return "REFRESH".equals(extractClaim(token, "token_type"));
	}

	public boolean isAccessToken(String token) {
		return "ACCESS".equals(extractClaim(token, "token_type"));
	}

	/*
	 * ========================= EXTRACTION =========================
	 */
	public String extractUsername(String token) {
		return parseToken(token).getSubject();
	}

	@SuppressWarnings("unchecked")
	public <T> T extractClaim(String token, String claimName) {
		return (T) parseToken(token).get(claimName);
	}

	private Claims parseToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

}
