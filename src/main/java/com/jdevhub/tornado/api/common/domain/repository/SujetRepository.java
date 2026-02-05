package com.jdevhub.tornado.api.common.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.common.domain.model.Sujet;

@Repository
public interface SujetRepository extends JpaRepository<Sujet, Long>{

	Optional<Sujet> findByCode(String code);
}
