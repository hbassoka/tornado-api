package com.jdevhub.tornado.api.shared.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.shared.domain.model.Titre;

@Repository
public interface TitreRepository extends JpaRepository<Titre,Long> {

	
	Optional<Titre> findByCode(String code);
}
