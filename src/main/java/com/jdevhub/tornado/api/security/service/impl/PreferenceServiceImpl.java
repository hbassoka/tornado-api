package com.jdevhub.tornado.api.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jdevhub.tornado.api.security.domain.dto.PreferenceDto;
import com.jdevhub.tornado.api.security.domain.mapper.PreferenceMapper;
import com.jdevhub.tornado.api.security.domain.model.Preference;
import com.jdevhub.tornado.api.security.domain.repository.PreferenceRepository;
import com.jdevhub.tornado.api.security.service.PreferenceService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class PreferenceServiceImpl implements PreferenceService {

	private final PreferenceRepository preferenceRepository;

	private final PreferenceMapper preferenceMapper;

	public PreferenceServiceImpl(PreferenceRepository preferenceRepository, PreferenceMapper preferenceMapper) {
		super();
		this.preferenceRepository = preferenceRepository;
		this.preferenceMapper = preferenceMapper;
	}

	@Override
	public Optional<PreferenceDto> getByUsername(String username) {

		return preferenceRepository.findByUserUsername(username).map(preferenceMapper::toDto);
	}

	@Override
	public Optional<PreferenceDto>  getById(Long id) {
		
		return preferenceRepository.findById(id).map(preferenceMapper::toDto);
	}

	@Override
	public List<PreferenceDto> findAll() {
		
		return this.preferenceRepository.findAll().stream().map(p -> preferenceMapper.toDto(p)).toList();
	}
	
	@Override
	public Page<PreferenceDto> search(String keyword, Pageable pageable) {
		
		return preferenceRepository.search(keyword, pageable).map(preferenceMapper::toDto);
	}

	@Override
	public Optional<PreferenceDto> createOrUpdate(PreferenceDto preference) {
		
		Preference newPreference = preferenceMapper.toEntity(preference);

		this.preferenceRepository.findByUserUsername(preference.user().username()).ifPresentOrElse(p -> {

			// preference EXIST

		}, () -> {

			// preference NOT FOUND

		});

		Preference savedPreference = this.preferenceRepository.save(newPreference);
		
		return Optional.of(preferenceMapper.toDto(savedPreference));
	}

	

}
