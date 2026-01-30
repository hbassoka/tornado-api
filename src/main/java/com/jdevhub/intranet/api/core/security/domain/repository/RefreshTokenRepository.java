package com.jdevhub.intranet.api.core.security.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jdevhub.intranet.api.core.security.domain.model.RefreshToken;
import com.jdevhub.intranet.api.core.security.domain.model.User;

import jakarta.persistence.LockModeType;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("""
	  select rt from RefreshToken rt
	  where rt.tokenHash = :tokenHash
	""")
	Optional<RefreshToken> findForUpdate(@Param("tokenHash") String hash);


    void deleteAllByUser(User user);
}
