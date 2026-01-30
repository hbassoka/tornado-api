package com.jdevhub.tornado.api.core.security.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.core.security.domain.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

	
	boolean existsByUserUsername(String email);
		
    Optional<Profile> findById(Long Id);
	
	Optional<Profile> findByUserId(Long userId);
	
	Optional<Profile> findByUserUsername(String username);
	       	
	@Query("""
       SELECT p FROM Profile p
       WHERE  LOWER(p.telephone) LIKE LOWER(CONCAT('%', :keyword, '%'))     
        OR LOWER(p.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))   
        OR LOWER(p.prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))       
       """)
    Page<Profile> search(@Param("keyword") String keyword, Pageable pageable);
}
