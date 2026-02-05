package com.jdevhub.tornado.api.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jdevhub.tornado.api.security.domain.dto.PreferenceDto;

public interface PreferenceService {

	
	Optional<PreferenceDto> getById(Long id);
	
	Optional<PreferenceDto> getByUsername(String username);
	
	Page<PreferenceDto> search(String keyword, Pageable pageable);
	
	List<PreferenceDto> findAll() ;
	
	Optional<PreferenceDto> createOrUpdate(PreferenceDto preference);
}
