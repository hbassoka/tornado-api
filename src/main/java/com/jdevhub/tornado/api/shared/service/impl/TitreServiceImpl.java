package com.jdevhub.tornado.api.shared.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdevhub.tornado.api.shared.domain.dto.TitreDto;
import com.jdevhub.tornado.api.shared.domain.mapper.TitreMapper;
import com.jdevhub.tornado.api.shared.domain.repository.TitreRepository;
import com.jdevhub.tornado.api.shared.service.TitreService;

@Service
@Transactional
public class TitreServiceImpl implements TitreService {

	private final TitreRepository titreRepository;
	private final TitreMapper titreMapper;

	public TitreServiceImpl(final TitreRepository titreRepository, final TitreMapper titreMapper) {
		this.titreRepository = titreRepository;
		this.titreMapper = titreMapper;
	}

	

	@Override
	public Optional<TitreDto> findById(Long id) {

		return this.titreRepository.findById(id)
				.map(civilite -> new TitreDto(civilite.getId(), civilite.getCode(), civilite.getLabel()));
	}

	@Override
	public Optional<TitreDto> findByCode(String code) {

		return this.titreRepository.findByCode(code).map(titreMapper::toDto);
	}

	
	@Override
	public List<TitreDto> findAll() {

		return this.titreRepository.findAll().stream()
				.map(civilite -> new TitreDto(civilite.getId(), civilite.getCode(), civilite.getLabel()))
				.collect((Collectors.toList()));
	}
}
