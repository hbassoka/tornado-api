package com.jdevhub.tornado.api.core.security.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public  record AuditableDto (String createdBy, 
		   LocalDateTime createdAt, 
		   String updatedBy, 
		   LocalDateTime updatedAt)  implements Serializable{

	
}
