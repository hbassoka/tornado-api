package com.jdevhub.intranet.api.core.security.domain.mapper;

import org.mapstruct.Mapper;

import com.jdevhub.intranet.api.core.security.domain.dto.PreferenceDto;
import com.jdevhub.intranet.api.core.security.domain.model.Preference;

@Mapper(
	componentModel = "spring",
    uses = UserMapper.class

)
public interface PreferenceMapper {

	PreferenceDto toDto(Preference entity);

	Preference toEntity(PreferenceDto dto);
}
