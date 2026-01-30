package com.jdevhub.tornado.api.core.security.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.core.security.domain.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	
	Optional<Role> findByName(String name);
	
	@Query("""
       SELECT r FROM Role r
       WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) 
       """)
    Page<Role> search(@Param("keyword") String keyword, Pageable pageable);
}
