package com.jdevhub.tornado.api.security.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.security.domain.model.Preference;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long>{


	boolean existsByUserUsername(String email);
   		
    Optional<Preference> findById(Long Id);
	
	Optional<Preference> findByUserId(Long userId);
	
	Optional<Preference> findByUserUsername(String username);
	    	   
	
	
	@Query("""
       SELECT p FROM Preference p
       WHERE LOWER(p.user.username) LIKE LOWER(CONCAT('%', :keyword, '%'))  
       """)
    Page<Preference> search(@Param("keyword") String keyword, Pageable pageable);
}
