package com.jdevhub.tornado.api.core.mailbox.domain.dto;

import com.jdevhub.tornado.api.core.mailbox.domain.model.Label;

public record MessageLabelDto(Long id, Label label, MessageDto message) {

}
