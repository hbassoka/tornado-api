package com.jdevhub.tornado.api.security.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public  record AuditableDto (String createdBy, 
		   LocalDateTime createdAt, 
		   String updatedBy, 
		   LocalDateTime updatedAt)  implements Serializable{

	
}
