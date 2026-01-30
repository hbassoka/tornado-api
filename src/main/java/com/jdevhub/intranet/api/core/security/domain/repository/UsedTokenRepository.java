package com.jdevhub.intranet.api.core.security.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.intranet.api.core.security.domain.model.UsedToken;

@Repository
public interface UsedTokenRepository extends JpaRepository<UsedToken,String> {

}
