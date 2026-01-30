package com.jdevhub.tornado.api.shared.service;

import java.util.List;
import java.util.Optional;

import com.jdevhub.tornado.api.shared.domain.dto.SujetDto;

	

public interface SujetService {

	List<SujetDto> findAll();

	Optional<SujetDto> findById(Long id);
	
	Optional<SujetDto> findByCode(String code);
}
