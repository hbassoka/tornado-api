package com.jdevhub.tornado.api.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.tornado.api.security.domain.dto.PreferenceDto;
import com.jdevhub.tornado.api.security.domain.model.Preference;

@Mapper(
	componentModel = "spring",
    uses = UserMapper.class

)
public interface PreferenceMapper {

	PreferenceDto toDto(Preference entity);

	Preference toEntity(PreferenceDto dto);
}
