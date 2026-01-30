package com.jdevhub.tornado.api.config;

import java.util.Map;

import org.springframework.core.env.PropertySource;

public class ParametreSource extends PropertySource<Map<String, Object>>{

	public ParametreSource(String name, Map<String, Object> props) {
		super(name);
		
	}

	@Override
	public Object getProperty(String name) {
		
		return this.source.get(name);
	}

}
