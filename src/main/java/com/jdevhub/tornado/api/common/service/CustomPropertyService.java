package com.jdevhub.tornado.api.common.service;

import java.util.List;
import java.util.Optional;

import com.jdevhub.tornado.api.common.domain.dto.ParametreDto;

public interface CustomPropertyService {


	String get(String key);
	
	<T> T getAs(String key, Class<T> targetType) ;
	
	Optional<ParametreDto> findByKey(String key);
	
	List<ParametreDto> findAll();
	
	void update (ParametreDto dto);
	
	void update(String key, String value) ;
}
