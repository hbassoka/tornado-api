package com.jdevhub.intranet.api.core.security.domain.dto;

import java.time.Instant;

public record SecurityAuditDto(Long id, String username, String event, String ip, Instant timestamp) {

}
