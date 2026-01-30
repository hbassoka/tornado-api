package com.jdevhub.intranet.api.core.mailbox.domain.dto;

public record AttachmentDto(Long id, MessageDto message, String fileName, String filePath, String mimeType, Long sizeBytes) {

}
