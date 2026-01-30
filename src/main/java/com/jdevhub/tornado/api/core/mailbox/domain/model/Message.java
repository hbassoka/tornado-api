package com.jdevhub.tornado.api.core.mailbox.domain.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "message",
    indexes = @Index(name = "idx_message_conversation", columnList = "conversation_id")
)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Column(name = "sender_id")
    private Long senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_to_message_id")
    private Message replyTo;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "sent_at")
    private Instant sentAt = Instant.now();

    @Column(name = "priority", length = 20)
    private String priority = "normal";

    @Column(name = "is_draft", nullable = false)
    private boolean draft;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MailboxMessage> mailboxMessages = new HashSet<>();

    protected Message() {}

    public Message(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }
}
