package com.jdevhub.intranet.api.features.experience.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Reference {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
