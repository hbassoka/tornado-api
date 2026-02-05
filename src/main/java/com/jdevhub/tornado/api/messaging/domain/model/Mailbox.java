package com.jdevhub.tornado.api.messaging.domain.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.jdevhub.tornado.api.security.domain.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "mailbox",
    uniqueConstraints = @UniqueConstraint(
        name = "uq_mailbox_user",
        columnNames = "user_id"
    )
)
public class Mailbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
	@JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @OneToMany(mappedBy = "mailbox", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MailboxMessage> messages = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<MailboxMessage> getMessages() {
		return messages;
	}

	public void setMessages(Set<MailboxMessage> messages) {
		this.messages = messages;
	}

	public Mailbox() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mailbox(Long id, User user, Instant createdAt, Instant updatedAt, Set<MailboxMessage> messages) {
		super();
		this.id = id;
		this.user = user;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.messages = messages;
	}

    
}
