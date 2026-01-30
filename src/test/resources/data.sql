-- Rôles
INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- Permissions
INSERT INTO permissions (name) VALUES 
('USER_READ'), 
('USER_CREATE'), 
('USER_DELETE');

-- Associer permissions aux rôles
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r, permissions p
WHERE (r.name = 'ROLE_ADMIN');


-- Utilisateur admin (password = 'admin' hashé en BCrypt à faire côté Java)

INSERT INTO users (username, email, password) 
VALUES ('admin', 'admin@jdevhub.com', 'admin1234'); -- BCrypt hash

INSERT INTO users (username, email, password) 
VALUES ('demo', 'demo@jdevhub.com', 'demo1234'); -- BCrypt hash


-- Associer l'utilisateur à un rôle
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.username = 'admin' AND r.name = 'ROLE_ADMIN';

-- Associer l'utilisateur à un rôle
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.username = 'demo' AND r.name = 'ROLE_USER';



INSERT INTO civilites(libelle_court,libelle)
VALUES
('Dr', 'Docteur'),
('Mr', 'Monsieur'),
('Mme', 'Madame'),
('Mle', 'Mademoiselle')
;



INSERT INTO subjects(name)
values
('Demande d''information'),
('Demande de devis'),
('Demande de support'),
('Autres');


