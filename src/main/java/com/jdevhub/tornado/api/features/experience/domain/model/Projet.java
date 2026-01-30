package com.jdevhub.tornado.api.features.experience.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "projets")
public class Projet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
