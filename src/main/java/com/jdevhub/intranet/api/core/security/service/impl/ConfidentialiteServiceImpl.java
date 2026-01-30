package com.jdevhub.intranet.api.core.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jdevhub.intranet.api.core.security.domain.dto.ConfidentialiteDto;
import com.jdevhub.intranet.api.core.security.domain.mapper.ConfidentialiteMapper;
import com.jdevhub.intranet.api.core.security.domain.model.Confidentialite;
import com.jdevhub.intranet.api.core.security.domain.repository.ConfidentialiteRepository;
import com.jdevhub.intranet.api.core.security.service.ConfidentialiteService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ConfidentialiteServiceImpl implements ConfidentialiteService{

	
	private final ConfidentialiteRepository confidentialiteRepository;
	
	private final ConfidentialiteMapper confidentialiteMapper;
	
		
	public ConfidentialiteServiceImpl(ConfidentialiteRepository confidentialiteRepository,
			ConfidentialiteMapper confidentialiteMapper) {
		super();
		this.confidentialiteRepository = confidentialiteRepository;
		this.confidentialiteMapper = confidentialiteMapper;
	}

	@Override
	public Optional<ConfidentialiteDto>  getByUsername(String username) {
		
		return confidentialiteRepository.findByUserUsername(username).map(confidentialiteMapper::toDto);
	}

	@Override
	public Optional<ConfidentialiteDto>  getById(Long id) {
		
		return confidentialiteRepository.findById(id).map(confidentialiteMapper::toDto);
	}

	@Override
	public List<ConfidentialiteDto> findAll() {
		
		return this.confidentialiteRepository.findAll().stream().map(p -> confidentialiteMapper.toDto(p)).toList();
	}
	@Override
	public Page<ConfidentialiteDto> search(String keyword, Pageable pageable) {
		
		return confidentialiteRepository.search(keyword, pageable).map(confidentialiteMapper::toDto);
	}

	@Override
	public Optional<ConfidentialiteDto> createOrUpdate(ConfidentialiteDto confidentialite) {
		
		Confidentialite newConfidentialite = confidentialiteMapper.toEntity(confidentialite);

		this.confidentialiteRepository.findByUserUsername(confidentialite.user().username()).ifPresentOrElse(p -> {

			// preference EXIST

		}, () -> {

			// preference NOT FOUND

		});

		Confidentialite savedConfidentialite = this.confidentialiteRepository.save(newConfidentialite);
		
		return Optional.of(confidentialiteMapper.toDto(savedConfidentialite));
	}

	
   
}
