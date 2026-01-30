package com.jdevhub.tornado.api.shared.service;

public interface CachedCustomPropertyService {

	void reloadCache();
	String get(String key);
	<T> T getAs(String key, Class<T> targetType);
}
