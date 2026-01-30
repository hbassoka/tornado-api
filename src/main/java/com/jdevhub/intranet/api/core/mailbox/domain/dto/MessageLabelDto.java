package com.jdevhub.intranet.api.core.mailbox.domain.dto;

import com.jdevhub.intranet.api.core.mailbox.domain.model.Label;

public record MessageLabelDto(Long id, Label label, MessageDto message) {

}
