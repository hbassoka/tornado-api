package com.jdevhub.tornado.api.common.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jdevhub.tornado.api.common.domain.model.Parametre;
import com.jdevhub.tornado.api.common.domain.repository.ParametreRepository;
import com.jdevhub.tornado.api.common.service.CachedCustomPropertyService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CachedParametreServiceImpl implements CachedCustomPropertyService {

	private final ParametreRepository parameterRepository;
	private volatile Map<String, String> cache = new HashMap<>();

	public CachedParametreServiceImpl(final ParametreRepository parameterRepository) {
		super();
		this.parameterRepository = parameterRepository;
	}
	
	@Scheduled(fixedDelay = 30_000)
    public void reloadCache() {
        cache = parameterRepository.findAll().stream()
                .collect(Collectors.toMap(Parametre::getNom, Parametre::getValeur));
    }

    public String get(String key) {
        return cache.get(key);
    }

    public <T> T getAs(String key, Class<T> targetType) {
        String raw = get(key);
        if (raw == null) return null;

        if (targetType == Integer.class) return targetType.cast(Integer.parseInt(raw));
        if (targetType == Long.class) return targetType.cast(Long.parseLong(raw));
        if (targetType == Boolean.class) return targetType.cast(Boolean.parseBoolean(raw));
        if (targetType == Double.class) return targetType.cast(Double.parseDouble(raw));

        return targetType.cast(raw);
    }
}
