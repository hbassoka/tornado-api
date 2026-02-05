package com.jdevhub.tornado.api.common.domain.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.common.domain.model.Parametre;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long>{

	Optional<Parametre> findByNom(String nom);
}
