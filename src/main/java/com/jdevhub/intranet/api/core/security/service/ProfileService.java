package com.jdevhub.intranet.api.core.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jdevhub.intranet.api.core.security.domain.dto.ProfileDto;

public interface ProfileService {

	Optional<ProfileDto> getById(Long id);

	Optional<ProfileDto> getByUsername(String username);

	Page<ProfileDto> search(String keyword, Pageable pageable);

	List<ProfileDto> findAll();
	
	Optional<ProfileDto> createOrUpdate(ProfileDto profil);
}
