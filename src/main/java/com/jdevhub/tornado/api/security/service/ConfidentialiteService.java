package com.jdevhub.tornado.api.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jdevhub.tornado.api.security.domain.dto.ConfidentialiteDto;

public interface ConfidentialiteService {
	
	
	Optional<ConfidentialiteDto> getById(Long id);

	Optional<ConfidentialiteDto> getByUsername(String username);

	Page<ConfidentialiteDto> search(String keyword, Pageable pageable);

	List<ConfidentialiteDto> findAll();
	
	Optional<ConfidentialiteDto> createOrUpdate(ConfidentialiteDto confidentialite);
	
}
