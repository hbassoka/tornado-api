package com.jdevhub.tornado.api.core.mailbox.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.core.mailbox.domain.model.Mailbox;


@Repository
public interface MailboxRepository extends JpaRepository<Mailbox, Long> {

	
	boolean existsByUserUsername(String username);
			    	 
	List<Mailbox> findByUserId(Long userId);
	
	List<Mailbox> findByUserUsername(String username);
	
 
   
    @Query("""
    	       SELECT mb FROM Mailbox mb
    	       WHERE LOWER(mb.user.username) LIKE LOWER(CONCAT('%', :keyword, '%'))   
    	       """)
     Page<Mailbox> search(@Param("keyword") String keyword, Pageable pageable);
    
}
