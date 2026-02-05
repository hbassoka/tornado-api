package com.jdevhub.tornado.api.security.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.security.domain.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	Optional<Permission> findByName(String name);
	
	 @Query("""
       SELECT p FROM Permission p
       WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))         
       """)
   Page<Permission> search(@Param("keyword") String keyword, Pageable pageable);
}
