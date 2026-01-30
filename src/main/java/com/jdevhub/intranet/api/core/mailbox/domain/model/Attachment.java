package com.jdevhub.intranet.api.core.mailbox.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "attachment")
@Data
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_id", nullable = false)
	private Message message;


	@Column(name = "file_name", nullable = false, length = 255)
	private String fileName;


	@Column(name = "file_path", nullable = false)
	private String filePath; // Could be S3 path or local storage path


	@Column(name = "mime_type", length = 100)
	private String mimeType;


	@Column(name = "size_bytes")
	private Long sizeBytes;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getMimeType() {
		return mimeType;
	}


	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}


	public Long getSizeBytes() {
		return sizeBytes;
	}


	public void setSizeBytes(Long sizeBytes) {
		this.sizeBytes = sizeBytes;
	}


	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Attachment(Long id, Message message, String fileName, String filePath, String mimeType, Long sizeBytes) {
		super();
		this.id = id;
		this.message = message;
		this.fileName = fileName;
		this.filePath = filePath;
		this.mimeType = mimeType;
		this.sizeBytes = sizeBytes;
	}
	
	
	
	
}
