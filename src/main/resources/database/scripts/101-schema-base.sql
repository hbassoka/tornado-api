-- ================================
--  DROP
-- ================================



DROP TABLE IF EXISTS confidentialites;
DROP TABLE IF EXISTS preferences;
DROP TABLE IF EXISTS profils;
DROP TABLE IF EXISTS adresses;

DROP TABLE IF EXISTS used_tokens;
DROP TABLE IF EXISTS security_audit;
DROP TABLE IF EXISTS refresh_tokens;

DROP TABLE IF EXISTS role_permissions;
DROP TABLE IF EXISTS group_permissions;
DROP TABLE IF EXISTS group_roles;
DROP TABLE IF EXISTS user_permissions;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user_groups;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS permissions;

DROP TABLE IF EXISTS motifs;
DROP TABLE IF EXISTS titres;


CREATE TABLE titres (
    id SERIAL PRIMARY KEY,
    code VARCHAR(8) UNIQUE NOT NULL,
    label VARCHAR(100) UNIQUE NOT null,
    active BOOLEAN DEFAULT true,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE motifs (
    id SERIAL PRIMARY KEY,
    code VARCHAR(30) NOT NULL UNIQUE,   -- "SUPPORT", "ACCES", "RH", …
    label VARCHAR(200) NOT NULL,        -- "Demande de support"
    description TEXT,
    active BOOLEAN DEFAULT true,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



-- ================================
-- DOMAINE SECURITE
-- ================================


CREATE TABLE permissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    label VARCHAR(255) NOT NULL
       
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    label VARCHAR(255) NOT NULL    
    
);

CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    label VARCHAR(255) NOT NULL,
    deletable BOOLEAN NOT NULL DEFAULT true
    
);



CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    provider VARCHAR(255),
    provider_id VARCHAR(255),    
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    password_raw VARCHAR(255) NOT NULL,
    secret VARCHAR(255), -- à sécuriser/chiffrer
    enabled BOOLEAN NOT NULL DEFAULT true,
    deletable BOOLEAN NOT NULL DEFAULT true,
    two_factor_enabled BOOLEAN NOT NULL DEFAULT false,
    account_non_expired BOOLEAN NOT NULL DEFAULT true,
    account_non_locked BOOLEAN NOT NULL DEFAULT true,
    credentials_non_expired BOOLEAN NOT NULL DEFAULT true,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);

--   User authorities
CREATE TABLE user_groups (
    user_id BIGINT NOT NULL REFERENCES users(id),
    group_id BIGINT NOT NULL REFERENCES groups(id),
    PRIMARY KEY (user_id, group_id)
);



-- Group authorities

CREATE TABLE group_roles (
    group_id BIGINT NOT NULL REFERENCES groups(id),
    role_id BIGINT NOT NULL REFERENCES roles(id),
    PRIMARY KEY (group_id, role_id)
);



-- Role authorities

CREATE TABLE role_permissions (
    role_id BIGINT NOT NULL REFERENCES roles(id),
    permission_id BIGINT NOT NULL REFERENCES permissions(id),
    PRIMARY KEY (role_id, permission_id)
);

--  
-- ================================
-- PROFIL
-- ================================

create table refresh_tokens(
   id BIGSERIAL PRIMARY KEY,
   token_hash VARCHAR(255) not null,
   user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
   expiry_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   revoked BOOLEAN NOT NULL DEFAULT true
);

create table used_tokens(
jti varchar(255) not null primary key,
expiry TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table security_audit(
id bigserial not null primary key,
username  varchar(255) not null,
event varchar(255) not null,
ip varchar(32) not null,
timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);



CREATE TABLE adresses (
    id BIGSERIAL PRIMARY KEY,  
    ligne1 VARCHAR(255)  ,
    ligne2 VARCHAR(255),
    ligne3 VARCHAR(255),
    code_postal VARCHAR(20)  ,
    ville VARCHAR(100)  ,
    region VARCHAR(100),
    pays VARCHAR(100)  ,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE profils (
    id SERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    titre_id BIGINT REFERENCES titres(id) ON DELETE SET NULL,
    adresse_id BIGINT null UNIQUE  REFERENCES adresses(id) ON DELETE SET NULL, 
    nom VARCHAR(255),
    prenom VARCHAR(255),    
    photo_url VARCHAR(255),    
    telephone VARCHAR(255) null UNIQUE,   
    date_naissance DATE,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_profils_telephone ON profils(telephone);

CREATE TABLE preferences (
    id SERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    language VARCHAR(10) DEFAULT 'fr',
    theme VARCHAR(20) DEFAULT 'light',
    email_notifications BOOLEAN DEFAULT TRUE,
    push_notifications BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE confidentialites (
    id SERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    profile_visibility VARCHAR(20) NOT NULL DEFAULT 'public' CHECK (profile_visibility IN ('public','friends','private')),
    show_email BOOLEAN NOT null  DEFAULT FALSE,
    show_birthdate BOOLEAN NOT null  DEFAULT FALSE,
    show_telephone BOOLEAN NOT null  DEFAULT TRUE,
    data_processing_consent     BOOLEAN NOT null DEFAULT TRUE,
    marketing_consent           BOOLEAN NOT null DEFAULT TRUE,
    third_party_sharing         BOOLEAN NOT null DEFAULT TRUE,
    consent_date                TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



INSERT INTO titres (code, label) VALUES
('M',  'Monsieur'),
('Mme','Madame'),
('Mlle','Mademoiselle'),
('Dr', 'Docteur'),
('Pr', 'Professeur'),
('Me', 'Maître'),
('Fr', 'Frère'),
('Soe', 'Sœur')
;



INSERT INTO motifs (code, label) VALUES
('ACCES', 'Demande d’accès à une application'),
('SUPPORT', 'Demande de support technique'),
('PROJET', 'Soumettre un projet'),
('MISSION', 'Proposition de missions'),
('INFO', 'Demande d’information'),
('ACCOUNT', 'Demande de suppression de compte'),
('DATA', 'Demande des données personnelles'),
('BUG', 'Signalement d’anomalie');

-- ========================================
-- 1. Permissions
-- ========================================
INSERT INTO permissions (name, label) values
  ('PERM_CIVILITE_READ', 'Accès consultation utilisateur'),
  ('PERM_CIVILITE_WRITE', 'Accès modification utilisateur'),
  ('PERM_CIVILITE_DELETE', 'Accès suppression utilisateur'),
  
  ('PERM_PERMISSION_READ', 'Accès consultation permissions'),
  ('PERM_PERMISSION_WRITE', 'Accès modification permissions'),
  ('PERM_PERMISSION_DELETE', 'Accès suppression permissions'),
  
  ('PERM_ROLE_READ', 'Accès consultation role'),
  ('PERM_ROLE_WRITE', 'Accès modification role'),
  ('PERM_ROLE_DELETE', 'Accès à la suppression d''un role'),
  
  ('PERM_GROUP_READ', 'Accès consultation group'),
  ('PERM_GROUP_WRITE', 'Accès modification group'),
  ('PERM_GROUP_DELETE', 'Accès suppression group'),  
  
  ('PERM_USER_READ', 'Accès consultation utilisateur'),
  ('PERM_USER_WRITE', 'Accès modification utilisateur'),
  ('PERM_USER_DELETE', 'Accès suppression utilisateur'),
    
    
  ('PERM_PROFILE_READ', 'Accès consultation profil'),
  ('PERM_PROFILE_WRITE', 'Accès modification profil'),
  ('PERM_PROFILE_DELETE', 'Accès suppression profil'),  
  
  ('PERM_PREFERENCE_READ', 'Accès consultation préferences'),
  ('PERM_PREFERENCE_WRITE', 'Accès modification préferences'),
  ('PERM_PREFERENCE_DELETE', 'Accès suppression préferences'),
  
  ('PERM_CONFIDENTIALITE_READ', 'Accès consultation confidentialités'),
  ('PERM_CONFIDENTIALITE_WRITE', 'Accès modification confidentialités'),
  ('PERM_CONFIDENTIALITE_DELETE', 'Accès suppression confidentialités'),
  
  ('PERM_CONTACT_READ', 'Accès consultation contact'),
  ('PERM_CONTACT_WRITE', 'Accès modification contact'),
  ('PERM_CONTACT_DELETE', 'Accès suppression contact'),  
  
  ('PERM_MESSAGE_READ', 'Accès consultation message'),
  ('PERM_MESSAGE_WRITE', 'Accès modification message'),
  ('PERM_MESSAGE_DELETE', 'Accès suppression message'), 
  
  
  ('PERM_CDOMAINE_EXPERTISE_READ', 'Accès consultation message'),
  ('PERM_CDOMAINE_EXPERTISE_WRITE', 'Accès modification message'),
  ('PERM_CDOMAINE_EXPERTISE_DELETE', 'Accès suppression message'), 
  
  ('PERM_DOMAINE_EXPERTISE_READ', 'Accès consultation message'),
  ('PERM_DOMAINE_EXPERTISE_WRITE', 'Accès modification message'),
  ('PERM_DOMAINE_EXPERTISE_DELETE', 'Accès suppression message'), 
  
  ('PERM_OMPTENCE_READ', 'Accès consultation message'),
  ('PERM_COMPTENCE_WRITE', 'Accès modification message'),
  ('PERM_COMPTENCE_DELETE', 'Accès suppression message'), 
  
  ('PERM_EXPERTISE_READ', 'Accès consultation message'),
  ('PERM_EXPERTISE_WRITE', 'Accès modification message'),
  ('PERM_EXPERTISE_DELETE', 'Accès suppression message'), 
  
  ('PERM_PROJET_READ', 'Accès consultation message'),
  ('PERM_PROJET_WRITE', 'Accès modification message'),
  ('PERM_PROJET_DELETE', 'Accès suppression message'), 
  
  ('PERM_MISSION_READ', 'Accès consultation message'),
  ('PERM_MISSION_WRITE', 'Accès modification message'),
  ('PERM_MISSION_DELETE', 'Accès suppression message')
  ;

   


-- ========================================
-- 2. Roles
-- ========================================
INSERT INTO roles (name, label) values
  ('ROLE_USER', 'Gerer les utilisateur'),
  ('ROLE_ADMIN', 'Accès à l''administrateur'),
  ('ROLE_MANAGER', 'Accès à la gestion'),
  ('ROLE_CIVILITE', 'Gerer les civilités'), 
  ('ROLE_GROUP', 'Gerer les groupes'),
  ('ROLE_PERMISSION', 'Gerer les permissions'),
  ('ROLE_PROFILE', 'Gerer les profil'), 
  ('ROLE_PREFERENCE', 'Gerer les préférences'),
  ('ROLE_CONFIDENTIALITE', 'Gerer  la confidentialité'),  
  
  ('ROLE_CONTACT', 'Gerer les contacts'),
  ('ROLE_MESSAGE', 'Gerer les messages'),
  ('ROLE_OBJET', 'Gerer les objets messages'),
  
  ('ROLE_DOMAINE_COMPTENCE', 'Gerer les domaines de  compétences'), 
  ('ROLE_DOMAINE_EXPERTISE', 'Gérer les domaines expertises'),   
  ('ROLE_COMPTENCE', 'Gérer les compétences'), 
  ('ROLE_EXPERTISE', 'Gérer les expertises'),  
  ('ROLE_PROJET', 'Gérer les projets'),
  ('ROLE_MISSION', 'Gérer les missions')
  ;

-- ========================================
-- 3. Groups
-- ========================================
INSERT INTO groups (name, label,deletable) VALUES
  ('GROUP_USER','Utilisateurs',false), 
  ('GROUP_MANAGER','Gestionnaires',false), 
  ('GROUP_ADMIN','Administrateurs',false);
 
  
-- ========================================
-- 4. Users
-- ========================================
INSERT INTO users (username,email, password_hash, password_raw,deletable ) VALUES
  ('demo','demo@jdevhub.com', '$2y$10$PCU54QelVG4GnvDeYdKIIOvz.ZoJF1eI/2mW2MiKd2c9DJvDHMSFu','demo1234',true),
  ('manager','manager@jdevhub.com', '$2y$10$yt9WMN66PUJrEzig9S8lPuavEm/zkR4ozfv33yxaLqPnE2J1eWctK','demo1234',false),
  ('admin','admin@jdevhub.com', '$2y$10$yt9WMN66PUJrEzig9S8lPuavEm/zkR4ozfv33yxaLqPnE2J1eWctK','admin1234',false);
-- ========================================
-- 5. Role_Permissions
-- ========================================
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON p.name LIKE CONCAT('%', REPLACE(r.name, 'ROLE_', ''), '%');

-- ROLE_ADMIN -> toutes les permissions
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
CROSS JOIN permissions p
WHERE r.name = 'ROLE_ADMIN';

-- ========================================
-- 6. Group_Roles
-- ========================================
-- ADMINISTRATEURS -> tous les rôles
INSERT INTO group_roles (group_id, role_id)
SELECT g.id, r.id
FROM groups g
CROSS JOIN roles r
WHERE g.name = 'GROUP_ADMIN';


-- UTILISATEURS -> ROLE_USER
INSERT INTO group_roles (group_id, role_id)
SELECT g.id, r.id
FROM groups g
JOIN roles r ON r.name = 'ROLE_USER'
WHERE g.name = 'GROUP_USER';


-- ========================================
-- 7. User_Groups
-- ========================================
-- admin -> ADMINISTRATEURS
INSERT INTO user_groups (user_id, group_id)
SELECT u.id, g.id
FROM users u
JOIN groups g ON g.name = 'GROUP_ADMIN'
WHERE u.username IN ('admin');

INSERT INTO user_groups (user_id, group_id)
SELECT u.id, g.id
FROM users u
JOIN groups g ON g.name = 'GROUP_MANAGER'
WHERE u.username IN ('manager');


-- demo -> UTILISATEURS
INSERT INTO user_groups (user_id, group_id)
SELECT u.id, g.id
FROM users u
JOIN groups g ON g.name = 'GROUP_USER'
WHERE u.username NOT IN ('admin','manager');



--  profils

insert into profils (user_id)
select id from users;


insert into preferences (user_id)
select id from users;

insert into confidentialites (user_id)
select id from users;

