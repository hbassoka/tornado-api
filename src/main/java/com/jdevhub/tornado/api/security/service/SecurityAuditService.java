package com.jdevhub.tornado.api.security.service;

public interface SecurityAuditService {

	void log(String username, String event, String ip);
}
