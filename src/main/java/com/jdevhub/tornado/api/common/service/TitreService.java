package com.jdevhub.tornado.api.common.service;

import java.util.List;
import java.util.Optional;

import com.jdevhub.tornado.api.common.domain.dto.TitreDto;

public interface TitreService {

	Optional<TitreDto> findById(Long id);
	
	Optional<TitreDto> findByCode(String code);
	
	List<TitreDto> findAll();

}
