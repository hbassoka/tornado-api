package com.jdevhub.tornado.api.core.mailbox.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdevhub.tornado.api.core.mailbox.domain.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
List<Label> findByUserId(Long userId);
}