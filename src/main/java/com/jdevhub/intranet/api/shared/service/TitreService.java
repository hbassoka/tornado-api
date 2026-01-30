package com.jdevhub.intranet.api.shared.service;

import java.util.List;
import java.util.Optional;

import com.jdevhub.intranet.api.shared.domain.dto.TitreDto;

public interface TitreService {

	Optional<TitreDto> findById(Long id);
	
	Optional<TitreDto> findByCode(String code);
	
	List<TitreDto> findAll();

}
