package com.jdevhub.tornado.api.config;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;


@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {
	
	
	private  JwtUtil jwtUtil;
	
		   
	public JwtHandshakeInterceptor(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}
	@Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String token = null;
        if (request instanceof ServletServerHttpRequest servletRequest) {
            token = servletRequest.getServletRequest().getParameter("token");
        }
        // valider le token et éventuellement stocker l’utilisateur dans attributes
        return token != null && jwtUtil.validateToken(token);
    }
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {}
}
