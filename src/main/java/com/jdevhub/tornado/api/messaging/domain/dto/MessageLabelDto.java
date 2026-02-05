package com.jdevhub.tornado.api.messaging.domain.dto;

import com.jdevhub.tornado.api.messaging.domain.model.Label;

public record MessageLabelDto(Long id, Label label, MessageDto message) {

}
