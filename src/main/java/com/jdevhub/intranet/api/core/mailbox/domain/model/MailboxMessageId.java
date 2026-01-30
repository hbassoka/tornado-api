package com.jdevhub.intranet.api.core.mailbox.domain.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MailboxMessageId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "mailbox_id")
    private Long mailboxId;

    @Column(name = "message_id")
    private Long messageId;

    protected MailboxMessageId() {}

    public MailboxMessageId(Long mailboxId, Long messageId) {
        this.mailboxId = mailboxId;
        this.messageId = messageId;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MailboxMessageId)) return false;
        MailboxMessageId that = (MailboxMessageId) o;
        return Objects.equals(mailboxId, that.mailboxId)
            && Objects.equals(messageId, that.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailboxId, messageId);
    }
}
