package com.jdevhub.intranet.api.core.security.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jdevhub.intranet.api.core.security.domain.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	Optional<Group> findByName(String name);

	Optional<Group> findById(Long id);

	@Query("""
			SELECT g FROM Group g
			WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :keyword, '%'))			   
			""")
	Page<Group> search(@Param("keyword") String keyword, Pageable pageable);

}
