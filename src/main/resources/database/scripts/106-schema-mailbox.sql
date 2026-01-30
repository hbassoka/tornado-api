

DROP TABLE IF EXISTS message_label;
DROP TABLE IF EXISTS label;
DROP TABLE IF EXISTS attachment;
DROP TABLE IF EXISTS mailbox_message;
DROP TABLE IF EXISTS mailbox_folder;
DROP TABLE IF EXISTS mailbox;
DROP TABLE IF EXISTS message_recipient;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS conversation;





-- CONVERSATIONS

CREATE TABLE conversation (
    id              BIGSERIAL PRIMARY KEY,
    subject_main    VARCHAR(500),
    created_at      TIMESTAMP NOT NULL DEFAULT NOW()
);


-- MESSAGES

CREATE TABLE message (
    id                      BIGSERIAL PRIMARY KEY,
    conversation_id         BIGINT REFERENCES conversation(id) ON DELETE CASCADE,
    sender_id               BIGINT REFERENCES users(id) ON DELETE SET NULL,
    reply_to_message_id     BIGINT REFERENCES message(id),
    body                    TEXT NOT NULL,
    sent_at                 TIMESTAMP DEFAULT NOW(),
    priority                VARCHAR(20) DEFAULT 'normal',  -- low / normal / high
    is_draft                BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_message_conversation ON message(conversation_id);

-- ============================
-- DESTINATAIRES (TO / CC / BCC)
-- ============================
CREATE TABLE message_recipient (
    id              BIGSERIAL PRIMARY KEY,
    message_id      BIGINT NOT NULL REFERENCES message(id) ON DELETE CASCADE,
    recipient_id    BIGINT NOT NULL REFERENCES users(id),
    recipient_type  VARCHAR(10) NOT NULL CHECK (recipient_type IN ('TO','CC','BCC'))
);

CREATE INDEX idx_message_recipient_msg ON message_recipient(message_id);
CREATE INDEX idx_message_recipient_user ON message_recipient(recipient_id);

-- ============================
-- MAILBOX / FOLDERS
-- ============================

CREATE TABLE mailbox (
    id          BIGSERIAL PRIMARY KEY,    
    user_id     BIGINT NOT NULL REFERENCES users(id) ON DELETE cascade,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    
);

CREATE UNIQUE INDEX uq_mailbox_user_name ON mailbox(user_id);

CREATE TABLE mailbox_folder (
 id          BIGSERIAL PRIMARY KEY,
 mailbox_id  BIGINT NOT NULL REFERENCES mailbox(id) ON DELETE CASCADE,
 name        VARCHAR(100) NOT NULL,
 label        VARCHAR(100) NOT NULL,
 created_by  VARCHAR(64) DEFAULT 'SYSTEM',
 created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 updated_by  VARCHAR(64) DEFAULT 'SYSTEM',
 updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX uq_mailbox_folder_mailbox ON mailbox_folder(mailbox_id, name);


-- ============================
-- MESSAGES DANS LES MAILBOXES
-- ============================
CREATE TABLE mailbox_message (
    mailbox_id      BIGINT NOT NULL REFERENCES mailbox(id) ON DELETE CASCADE,
    message_id      BIGINT NOT NULL REFERENCES message(id) ON DELETE CASCADE,
    is_read         BOOLEAN NOT NULL DEFAULT FALSE,
    is_flagged      BOOLEAN NOT NULL DEFAULT FALSE,
    received_at     TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (mailbox_id, message_id)
);

CREATE INDEX idx_mailbox_msg_read ON mailbox_message(mailbox_id, is_read);

-- ============================
-- ATTACHMENTS
-- ============================
CREATE TABLE attachment (
    id          BIGSERIAL PRIMARY KEY,
    message_id  BIGINT NOT NULL REFERENCES message(id) ON DELETE CASCADE,
    file_name   VARCHAR(255) NOT NULL,
    file_path   TEXT NOT NULL,
    mime_type   VARCHAR(100),
    size_bytes  BIGINT
);


-- ============================
-- LABELS / TAGS (type Gmail)
-- ============================
CREATE TABLE label (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT REFERENCES users(id) ON DELETE CASCADE,
    name        VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX uq_label_user_name ON label(user_id, name);

CREATE TABLE message_label (
    id                      BIGSERIAL PRIMARY KEY,
    label_id                BIGINT NOT NULL REFERENCES label(id) ON DELETE CASCADE,
    message_id      BIGINT NOT NULL REFERENCES message(id) -- association logique
);

CREATE INDEX idx_message_label_label ON message_label(label_id);
-- ================================
-- INIT
-- ================================



INSERT INTO mailbox (user_id)
select id from users;

INSERT INTO mailbox_folder (mailbox_id, name,label)
SELECT id, folder_name,foler_label
FROM mailbox
CROSS JOIN (
  VALUES
    ('Inbox','boîte de reception'),
    ('Sent','boite d\'' envoie'),
    ('Trash','Corbeille'),
    ('Archive','Archive')
) AS folders(folder_name,foler_label);
