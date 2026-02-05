package com.jdevhub.tornado.api.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.jdevhub.tornado.api.common.domain.repository.ParametreRepository;

public class ParametreEnvPostProcessor implements EnvironmentPostProcessor {

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		ParametreRepository applicationPropertyRepository = ctx.getBean(ParametreRepository.class);

		Map<String, Object> props = new HashMap<>();

		applicationPropertyRepository.findAll().forEach(p -> props.put(p.getNom(), p.getValeur()));

		// Add properties at highest priority
		//environment.getPropertySources().addFirst(new ParametreSource("databaseProperties", props));

		ctx.close();
	}

}
