package com.jdevhub.intranet.api.core.security.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jdevhub.intranet.api.core.security.domain.model.Confidentialite;

@Repository
public interface ConfidentialiteRepository extends JpaRepository<Confidentialite, Long>{

	boolean existsByUserUsername(String email);
	
    Optional<Confidentialite> findById(Long Id);
	
	Optional<Confidentialite> findByUserId(Long userId);
	
	Optional<Confidentialite> findByUserUsername(String username);
	
	@Query("""
       SELECT c FROM Confidentialite c
       WHERE LOWER(c.user.username) LIKE LOWER(CONCAT('%', :keyword, '%'))         
       """)
    Page<Confidentialite> search(@Param("keyword") String keyword, Pageable pageable);
}
