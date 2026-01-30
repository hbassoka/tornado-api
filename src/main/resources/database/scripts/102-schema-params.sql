
DROP TABLE IF EXISTS parametres;


CREATE TABLE parametres  (
    prop_key VARCHAR(255) PRIMARY KEY,        
    prop_value TEXT NOT NULL,
    description TEXT,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




INSERT INTO parametres (prop_key, prop_value, description)
VALUES 
('app.email.sender', 'noreply@myapp.com', 'Adresse d’envoi des emails'),
('security.token.expiration', '3600', 'Expiration JWT en secondes');