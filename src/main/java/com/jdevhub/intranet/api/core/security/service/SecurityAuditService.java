package com.jdevhub.intranet.api.core.security.service;

public interface SecurityAuditService {

	void log(String username, String event, String ip);
}
