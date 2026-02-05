package com.jdevhub.tornado.api.common.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdevhub.tornado.api.common.domain.dto.SujetDto;
import com.jdevhub.tornado.api.common.domain.mapper.SujetMapper;
import com.jdevhub.tornado.api.common.domain.repository.SujetRepository;
import com.jdevhub.tornado.api.common.service.SujetService;

@Service
@Transactional
public class SujetServiceImpl implements SujetService {
	
	
	private final SujetRepository  sujetRepository;

	private final SujetMapper sujetMapper;
	
	
	
	
	public SujetServiceImpl(final SujetRepository sujetRepository,
			final SujetMapper sujetMapper) {
		super();
		this.sujetRepository = sujetRepository;
		this.sujetMapper = sujetMapper;
	}

	@Override
	public List<SujetDto> findAll() {
		return this.sujetRepository.findAll().stream().map(m->sujetMapper.toDto(m)).toList();
	}

	@Override
	public Optional<SujetDto> findById(Long id) {
		
		return this.sujetRepository.findById(id).map(sujetMapper::toDto);
	}

	@Override
	public Optional<SujetDto> findByCode(String code) {
		
		return this.sujetRepository.findByCode(code).map(sujetMapper::toDto);
	}

	
}
