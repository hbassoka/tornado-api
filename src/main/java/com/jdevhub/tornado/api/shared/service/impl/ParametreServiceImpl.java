package com.jdevhub.tornado.api.shared.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdevhub.tornado.api.shared.domain.dto.ParametreDto;
import com.jdevhub.tornado.api.shared.domain.mapper.ParametreMapper;
import com.jdevhub.tornado.api.shared.domain.model.Parametre;
import com.jdevhub.tornado.api.shared.domain.repository.ParametreRepository;
import com.jdevhub.tornado.api.shared.service.CustomPropertyService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ParametreServiceImpl implements CustomPropertyService {

	private final ParametreRepository customPropertyRepository;
	private final ParametreMapper customPropertyMapper;

	public ParametreServiceImpl(final ParametreRepository customPropertyRepository,
			final ParametreMapper customPropertyMapper) {
		super();
		this.customPropertyRepository = customPropertyRepository;
		this.customPropertyMapper = customPropertyMapper;
	}

	public String get(String key) {
		return customPropertyRepository.findByNom(key).map(Parametre::getNom).orElse(null);
	}

	public <T> T getAs(String key, Class<T> targetType) {
		String raw = get(key);
		if (raw == null)
			return null;

		if (targetType == Integer.class)
			return targetType.cast(Integer.parseInt(raw));
		if (targetType == Long.class)
			return targetType.cast(Long.parseLong(raw));
		if (targetType == Boolean.class)
			return targetType.cast(Boolean.parseBoolean(raw));
		if (targetType == Double.class)
			return targetType.cast(Double.parseDouble(raw));

		return targetType.cast(raw);
	}

	public void update(String key, String value) {
		Parametre prop = new Parametre();
		prop.setNom(key);
		prop.setValeur(value);

		customPropertyRepository.save(prop);
	}

	@Override
	public Optional<ParametreDto> findByKey(String key) {

		return customPropertyRepository.findByNom(key).map(customPropertyMapper::toDto);
	}

	@Override
	public List<ParametreDto> findAll() {

		return customPropertyRepository.findAll().stream().map(p -> customPropertyMapper.toDto(p)).toList();
	}

	@Override
	public void update(ParametreDto dto) {
		
		String key=dto.nom();
		if(key!=null && customPropertyRepository.findByNom(key).isPresent()) {
			
			Parametre prop=customPropertyRepository.findByNom(key).get();
			prop.setValeur(dto.valeur());
			customPropertyRepository.save(prop);
		}

	}

}
