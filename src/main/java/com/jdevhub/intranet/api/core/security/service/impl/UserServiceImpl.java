package com.jdevhub.intranet.api.core.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdevhub.intranet.api.core.auth.domain.dto.FacebookUser;
import com.jdevhub.intranet.api.core.auth.domain.dto.GoogleUser;
import com.jdevhub.intranet.api.core.security.domain.dto.UserDto;
import com.jdevhub.intranet.api.core.security.domain.dto.UserInformationDto;
import com.jdevhub.intranet.api.core.security.domain.mapper.UserMapper;
import com.jdevhub.intranet.api.core.security.domain.model.Confidentialite;
import com.jdevhub.intranet.api.core.security.domain.model.Group;
import com.jdevhub.intranet.api.core.security.domain.model.Preference;
import com.jdevhub.intranet.api.core.security.domain.model.Profile;
import com.jdevhub.intranet.api.core.security.domain.model.User;
import com.jdevhub.intranet.api.core.security.domain.repository.GroupRepository;
import com.jdevhub.intranet.api.core.security.domain.repository.UserRepository;
import com.jdevhub.intranet.api.core.security.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final String DEFAULT_GROUP_NAME = "GROUP_USER";

	private final UserRepository userRepository;

	private final GroupRepository groupRepository;

	private final UserMapper userMapper;

	public UserServiceImpl(final UserRepository userRepository, final GroupRepository groupRepository,
			final UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.groupRepository = groupRepository;
		this.userMapper = userMapper;

	}

	public List<UserDto> findAll() {
		return this.userRepository.findAll().stream().map(u -> userMapper.toDto(u)).toList();
	}

	
	
	public Optional<UserDto> findById(Long id) {
		return userRepository.findById(id).map(userMapper::toDto);
	}
	
	
	@Override
	public Optional<UserDto> save(UserDto user) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<UserDto> update(Long id, UserDto user) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	

	@Override
	public Optional<UserDto> findByUsername(String username) {

		return userRepository.findByUsername(username).map(userMapper::toDto);

	}

	
	@Override
	public boolean existsByUsername(String username) {

		return userRepository.existsByUsername(username);
	}
	
	@Override
	public Optional<UserDto> getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {

			String username = authentication.getName();

			return userRepository.findByUsername(username).map(userMapper::toDto);
		}
		return null;
	}

	
	

	@Override
	public Optional<UserDto> createOrUpdate(UserDto userDto) {

		User newUser = userMapper.toEntity(userDto);

		this.userRepository.findByUsername(userDto.username()).ifPresentOrElse(user -> {

			// USER EXIST

		}, () -> {

			// USER NOT FOUND

		});

		User savedUser = this.userRepository.save(newUser);
		
		return Optional.of(userMapper.toDto(savedUser));
	}

	@Override
	public Optional<UserDto> register(UserInformationDto userInformationDto) {
		
		
		boolean userExists = userRepository.existsByUsername(userInformationDto.username());

		if (userExists) {

			return userRepository.findByUsername(userInformationDto.email()).map(userMapper::toDto);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<UserDto> createOrUpdateGoogleUser(GoogleUser googleUser) {

		boolean userExists = userRepository.existsByUsername(googleUser.email());

		if (userExists) {

			return userRepository.findByUsername(googleUser.email()).map(userMapper::toDto);
		}

		// create new USer
		User newUser = new User();

		newUser.setProvider("google");
		newUser.setProviderId(googleUser.sub());		
		newUser.setUsername(googleUser.email());
		newUser.setEmail(googleUser.email());
		// GROUP
		Group group = groupRepository.findByName(DEFAULT_GROUP_NAME).get();
		newUser.getGroups().add(group);

		// Profil
		Profile profil = new Profile();		
		profil.setNom(googleUser.name());
		profil.setUser(newUser);
		
		
		// Preference
		Preference preference = new Preference();

		// Confidentialite
		Confidentialite confidentialite = new Confidentialite();
		preference.setUser(newUser);
		
		
		
		confidentialite.setUser(newUser);
		
		
		
		
		return    Optional.of(userMapper.toDto(newUser));
	
	}

	@Override
	public Optional<UserDto> createOrUpdateFacebookUser(FacebookUser facebookUser) {

		boolean userExists = userRepository.existsByUsername(facebookUser.email());

		if (userExists) {

			return userRepository.findByUsername(facebookUser.email()).map(userMapper::toDto);
		}

		// create new USer
		User newUser = new User();

		newUser.setProvider("facebook");
		newUser.setProviderId(facebookUser.id());		
		newUser.setUsername(facebookUser.email());
		newUser.setEmail(facebookUser.email());

		// GROUP
		Group group = groupRepository.findByName(DEFAULT_GROUP_NAME).get();
		newUser.getGroups().add(group);

		// Profil
		Profile profil = new Profile();
		profil.setUser(newUser);
		profil.setNom(facebookUser.name());
		
		// Preference
		Preference preference = new Preference();
		preference.setUser(newUser);
		
		// Confidentialite
		Confidentialite confidentialite = new Confidentialite();
		confidentialite.setUser(newUser);
		
		
		
		
	    return  Optional.of(userMapper.toDto(newUser));

	}

	
	@Override
	public void addGroupToUser(Long userId, String groupName) {
		User user = userRepository.findById(userId).orElseThrow();
		Group group = groupRepository.findByName(groupName).orElseThrow();
		user.getGroups().add(group);
		userRepository.save(user);
	}

	@Override
	public void removeGroupFromUser(Long userId, String groupName) {

		User user = userRepository.findById(userId).orElseThrow();
		Group group = groupRepository.findByName(groupName).orElseThrow();
		user.getGroups().remove(group);
		userRepository.save(user);
	}

	@Override
	public Page<UserDto> search(String keyword, Pageable pageable) {

		return userRepository.search(keyword, pageable).map(userMapper::toDto);
	}

	
	
}
