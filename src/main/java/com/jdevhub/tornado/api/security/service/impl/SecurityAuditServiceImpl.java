package com.jdevhub.tornado.api.security.service.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.jdevhub.tornado.api.security.domain.model.SecurityAudit;
import com.jdevhub.tornado.api.security.domain.repository.SecurityAuditRepository;
import com.jdevhub.tornado.api.security.service.SecurityAuditService;

import jakarta.transaction.Transactional;





@Transactional
@Service
public class SecurityAuditServiceImpl implements SecurityAuditService {

	private final SecurityAuditRepository  securityAuditRepository;

	public SecurityAuditServiceImpl(SecurityAuditRepository securityAuditRepository) {
		super();
		this.securityAuditRepository = securityAuditRepository;
	}


	@Override
	public void log(String username, String event, String ip) {

        SecurityAudit audit = new SecurityAudit();
        audit.setUsername(username);
        audit.setEvent(event);
        audit.setIp(ip);
        audit.setTimestamp(Instant.now());

        securityAuditRepository.save(audit);
    }
	
}
