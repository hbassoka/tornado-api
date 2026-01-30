package com.jdevhub.intranet.api.core.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdevhub.intranet.api.core.security.domain.dto.GroupDto;
import com.jdevhub.intranet.api.core.security.domain.mapper.GroupMapper;
import com.jdevhub.intranet.api.core.security.domain.repository.GroupRepository;
import com.jdevhub.intranet.api.core.security.service.GroupService;

@Service
@Transactional
public class GroupServiceImpl implements GroupService{

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final GroupRepository  groupRepository;
	private final GroupMapper groupMapper;

	

	public GroupServiceImpl(final GroupRepository groupRepository, final GroupMapper groupMapper) {
		super();
		this.groupRepository = groupRepository;
		this.groupMapper=groupMapper;
	
	}

	@Override
	public List<GroupDto> findAll() {
		
		return this.groupRepository.findAll().stream().map(groupMapper::toDto).toList();
	}

	
	@Override
	public Optional<GroupDto> findById(Long id) {
		return this.groupRepository.findById(id).map(groupMapper::toDto);
	
	}
	@Override
	public Optional<GroupDto>  findByName(String name) {
		
		return this.groupRepository.findByName(name).map(groupMapper::toDto);
	}

	
	
	@Override
	public void addRoleToGroup(Long groupId, String roleName) {
		
				
	}
			

	@Override
	public void removeRoleFromGroup(Long groupId, String roleName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<GroupDto> search(String keyword, Pageable pageable) {
		
		return this.groupRepository.search(keyword, pageable).map(groupMapper::toDto);
	}

	
	

}
