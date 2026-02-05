package com.jdevhub.tornado.api.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jdevhub.tornado.api.security.domain.dto.ProfileDto;
import com.jdevhub.tornado.api.security.domain.mapper.ProfileMapper;
import com.jdevhub.tornado.api.security.domain.model.Profile;
import com.jdevhub.tornado.api.security.domain.repository.ProfileRepository;
import com.jdevhub.tornado.api.security.service.ProfileService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ProfileServiceImpl implements ProfileService {

	private final ProfileRepository profileRepository;

	private final ProfileMapper profileMapper;

	public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
		super();
		this.profileRepository = profileRepository;
		this.profileMapper = profileMapper;
	}

	@Override
	public Optional<ProfileDto> getById(Long id) {
		
		return profileRepository.findById(id).map(profileMapper::toDto);
	}

	@Override
	public Optional<ProfileDto> getByUsername(String username) {
		
		return profileRepository.findByUserUsername(username).map(profileMapper::toDto);
	}

	@Override
	public Page<ProfileDto> search(String keyword, Pageable pageable) {
		
		return profileRepository.search(keyword, pageable).map(profileMapper::toDto);
	}

	@Override
	public List<ProfileDto> findAll() {
		
		return profileRepository.findAll().stream().map(p->profileMapper.toDto(p)).toList();
	}

	@Override
	public Optional<ProfileDto> createOrUpdate(ProfileDto profil) {
		
		Profile newProfil = profileMapper.toEntity(profil);

		this.profileRepository.findByUserUsername(profil.user().username()).ifPresentOrElse(p -> {

			// PROFIL EXIST

		}, () -> {

			// PROFIL NOT FOUND

		});

		Profile savedProfil = this.profileRepository.save(newProfil);
		
		return Optional.of(profileMapper.toDto(savedProfil));
	}

}
