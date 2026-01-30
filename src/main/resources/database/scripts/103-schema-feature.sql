
DROP TABLE IF EXISTS mission_expertise;
DROP TABLE IF EXISTS projet_expertise;
DROP TABLE IF EXISTS missions;
DROP TABLE IF EXISTS projets;
DROP TABLE IF EXISTS expertise_competence;
DROP TABLE IF EXISTS expertises;
DROP TABLE IF EXISTS competences;
DROP TABLE IF EXISTS competence_level;
DROP TABLE IF EXISTS competence_category;


-- ================================
-- DOMAINES COMPETENCES
-- ================================

create table competence_category(
  id SERIAL PRIMARY KEY,
  name VARCHAR(64) not null unique,
  label varchar(255) NULL,
  description TEXT NULL
);

create index idx_competence_category on competence_category(name);


create table competence_level(
  id SERIAL PRIMARY KEY,
  name VARCHAR(64) not null unique,
  label varchar(255) NULL,
  description TEXT NULL
);

create index idx_competence_level on competence_level(name);


CREATE TABLE competences (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(64) not null,     -- Backend, Frontend, Data, DevOps, Architecture, Leadership
    level VARCHAR(64) not null,        -- Junior | Confirmé | Senior | Expert
    years_experience INTEGER,
    is_primary BOOLEAN DEFAULT false,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category) REFERENCES competence_category(name),
    FOREIGN KEY (level) REFERENCES competence_level(name)
);

create index idx_competences_name on competences(name);
create index idx_competences_category on competences(category);
create index idx_competences_level on competences(level);


CREATE TABLE expertises (
    id BIGSERIAL PRIMARY KEY,
    titre VARCHAR(150) NOT NULL,
    description TEXT,
    categorie VARCHAR(50),              -- Dev, Archi, DevOps, Lead…
    niveau_responsabilite VARCHAR(30),   -- Execution | Conception | Pilotage | Décision
    date_debut DATE,
    date_fin DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE expertise_competence (
    expertise_id BIGINT NOT NULL,
    competence_id BIGINT NOT NULL,
    PRIMARY KEY (expertise_id, competence_id),
    FOREIGN KEY (expertise_id) REFERENCES expertises(id),
    FOREIGN KEY (competence_id) REFERENCES competences(id)
);


CREATE TABLE projets (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(150) NOT NULL,
    description TEXT,
    date_debut DATE,
    date_fin DATE,
    client VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE missions (
    id BIGSERIAL PRIMARY KEY,
    titre VARCHAR(150) NOT NULL,
    description TEXT,
    date_debut DATE,
    date_fin DATE,
    client VARCHAR(100),
    role VARCHAR(50),                 -- Dev, Tech Lead, Architecte, DevOps...
    projet_id BIGINT,                 -- Optionnel : ratexpertisement à un projet
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (projet_id) REFERENCES projets(id)
);

CREATE TABLE mission_expertise (
    mission_id BIGINT NOT NULL,
    expertise_id BIGINT NOT NULL,
    PRIMARY KEY (mission_id, expertise_id),
    FOREIGN KEY (mission_id) REFERENCES missions(id),
    FOREIGN KEY (expertise_id) REFERENCES expertises(id)
);


CREATE TABLE projet_expertise (
    projet_id BIGINT NOT NULL,
    expertise_id BIGINT NOT NULL,
    PRIMARY KEY (projet_id, expertise_id),
    FOREIGN KEY (projet_id) REFERENCES projets(id),
    FOREIGN KEY (expertise_id) REFERENCES expertises(id)
);

-- =========================================
-- PROJETS
-- =========================================
/*
INSERT INTO projets (id, nom, client, contexte, date_debut, date_fin, statut) VALUES
(1, 'Plateforme Traitements Énergétiques', 'EDF',
 'Refonte microservices, optimisation calculs énergétiques.',
 '2019-01-01', '2023-03-31', 'TERMINE'),

(2, 'Référentiel Prix & Produits', 'GALEC - E.Leclerc',
 'Système prix, promotions, catalogue produits.',
 '2016-05-01', '2018-12-31', 'TERMINE'),

(3, 'KYC/AML Compliance Platform', 'BNP Paribas',
 'Modules risk & compliance, sécurisation des workflows.',
 '2014-01-01', '2016-04-30', 'TERMINE'),

(4, 'Système Titres Sécurisés', 'ANTS',
 'Modernisation production titres sécurisés.',
 '2011-05-01', '2013-12-31', 'TERMINE'),

(5, 'Plateforme Médicale', 'Secteur Santé',
 'Intégration modules métier santé, archivage médical.',
 '2008-02-01', '2011-04-30', 'TERMINE');

*/
-- =========================================
-- MISSIONS
-- =========================================
/*
INSERT INTO missions (id, projet_id, titre, description, date_debut, date_fin) VALUES
(1, 1, 'Tech Lead Java / Spring Boot',
 'Refonte microservices, optimisation chaînes de calcul, GitLab CI/CD, architecture modulaire.',
 '2019-01-01', '2023-03-31'),

(2, 2, 'Lead Developer Angular / Java',
 'Développement du référentiel produits, système prix & promotions.',
 '2016-05-01', '2018-12-31'),

(3, 3, 'Développeur Senior Java / Sécurité',
 'KYC/AML, sécurité applicative, droits & rôles.',
 '2014-01-01', '2016-04-30'),

(4, 4, 'Développeur Java / JEE',
 'Titres d’identité sécurisés, génération documents, traçabilité.',
 '2011-05-01', '2013-12-31'),

(5, 5, 'Développeur Full Stack Java',
 'Modules médicaux, archivage, optimisation SQL.',
 '2008-02-01', '2011-04-30');

*/
-- =========================================
-- MISSIONS -> COMPETENCES
-- =========================================
-- EDF

insert into competence_category(name) values
('Backend'),
('Frontend'),
('Maintenance'),
('Data'),
('Cloud'),
('Messaging'),
('Testing'),
('Quality'),
('DevOps'),
('Architecture'),
('Leadership');

insert into competence_level(name) values
('Junior'),
('Confirmé'),
('Senior'),
('Expert');


INSERT INTO competences (name, category, level, years_experience, is_primary) VALUES
('Java', 'Backend', 'Expert', 15, true),
('Spring Boot', 'Backend', 'Expert', 10, true),
('Spring MVC', 'Backend', 'Expert', 12, true),
('JSF', 'Backend', 'Expert', 12, true),
('STRUT', 'Backend', 'Expert', 12, true),
('Spring Security', 'Backend', 'Expert', 9, true),
('Spring Data JPA', 'Backend', 'Expert', 10, true),
('Hibernate', 'Backend', 'Expert', 12, true),
('REST API', 'Backend', 'Expert', 14, true),
('Microservices', 'Architecture', 'Expert', 9, true);

INSERT INTO competences (name, category, level, years_experience, is_primary) VALUES
('Angular', 'Frontend', 'Expert', 8, true),
('TypeScript', 'Frontend', 'Expert', 8, true),
('JavaScript ES6+', 'Frontend', 'Expert', 12, false),
('RxJS', 'Frontend', 'Senior', 7, false),
('NgRx', 'Frontend', 'Senior', 6, false),
('Angular Material', 'Frontend', 'Senior', 7, false),
('Tailwind CSS', 'Frontend', 'Senior', 5, false);

INSERT INTO competences (name, category, level, years_experience, is_primary) VALUES
('Clean Architecture', 'Architecture', 'Expert', 8, true),
('Hexagonal Architecture', 'Architecture', 'Expert', 7, true),
('DDD', 'Architecture', 'Senior', 6, false),
('Event-Driven Architecture', 'Architecture', 'Senior', 6, false),
('API Design', 'Architecture', 'Expert', 10, true),
('Scalability', 'Architecture', 'Senior', 8, false);

INSERT INTO competences (name, category, level, years_experience, is_primary) VALUES
('Tech Leadership', 'Leadership', 'Expert', 7, true),
('Code Review', 'Leadership', 'Expert', 10, true),
('Mentoring développeurs', 'Leadership', 'Expert', 8, true),
('Gestion des priorités', 'Leadership', 'Senior', 9, false),
('Interface métier / IT', 'Leadership', 'Expert', 10, false),
('Animation d’équipe agile', 'Leadership', 'Senior', 7, false);

INSERT INTO competences (name, category, level, years_experience, is_primary) VALUES
('Docker', 'DevOps', 'Expert', 7, true),
('Docker Compose', 'DevOps', 'Senior', 6, false),
('CI/CD', 'DevOps', 'Expert', 9, true),
('GitLab CI', 'DevOps', 'Expert', 8, false),
('Jenkins', 'DevOps', 'Senior', 7, false),
('AWS', 'Cloud', 'Senior', 6, false),
('Linux', 'DevOps', 'Expert', 12, false),
('Nginx', 'DevOps', 'Senior', 6, false);

INSERT INTO competences (name, category, level, years_experience, is_primary) VALUES
('PostgreSQL', 'Data', 'Expert', 9, false),
('Oracle', 'Data', 'Senior', 10, false),
('MongoDB', 'Data', 'Senior', 6, false),
('Redis', 'Data', 'Senior', 5, false),
('Kafka', 'Messaging', 'Senior', 6, false),
('RabbitMQ', 'Messaging', 'Senior', 5, false);

INSERT INTO competences (name, category, level, years_experience, is_primary) VALUES
('JUnit 5', 'Testing', 'Expert', 10, false),
('Mockito', 'Testing', 'Expert', 9, false),
('Testcontainers', 'Testing', 'Senior', 6, false),
('SonarQube', 'Quality', 'Expert', 8, false);

INSERT INTO competences (name, category, level, years_experience, is_primary) VALUES
('Oracle Database', 'Data', 'Expert', 12, true),
('PostgreSQL', 'Data', 'Expert', 9, true),
('MySQL', 'Data', 'Senior', 10, false),
('SQL Server', 'Data', 'Senior', 8, false),
('DB2', 'Data', 'Senior', 7, false),
('MariaDB', 'Data', 'Senior', 6, false);

INSERT INTO competences (name, category, level, years_experience, is_primary) VALUES
('SQL avancé', 'Data', 'Expert', 14, true),
('PL/SQL', 'Data', 'Expert', 10, false),
('T-SQL', 'Data', 'Senior', 8, false),
('Optimisation des performances SQL', 'Data', 'Expert', 12, true),
('Modélisation de données', 'Data', 'Expert', 14, true),
('Indexation & partitionnement', 'Data', 'Senior', 9, false),
('Transactions & isolation', 'Data', 'Expert', 12, false);



INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Développement backend Java Spring Boot',
 'Implémentation de services REST, logique métier, validation et gestion des erreurs',
 'Backend',
 'Execution'),

('Développement frontend Angular',
 'Création de composants Angular, intégration API REST, gestion des états',
 'Frontend',
 'Execution'),

('Correction d’anomalies et maintenance',
 'Analyse des bugs, correctifs, refactoring et amélioration continue',
 'Maintenance',
 'Execution'),

('Écriture de tests unitaires et intégration',
 'Tests JUnit, Mockito, tests d’intégration Spring',
 'Quality',
 'Execution');

INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Développement applicatif JSF',
 'Conception et développement d’interfaces web avec JSF, Facelets et Managed Beans',
 'Backend',
 'Execution'),

('Maintenance et évolution applications JSF',
 'Correction d’anomalies, refactoring et modernisation progressive',
 'Maintenance',
 'Execution'),

('Développement applicatif Struts 2',
 'Implémentation d’actions, validation, navigation et intégration backend',
 'Backend',
 'Execution'),

('Migration Struts 2 / JSF vers Spring Boot',
 'Analyse de l’existant, stratégie de migration et sécurisation',
 'Architecture',
 'Conception');


INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Conception de modèles de données',
 'Modélisation relationnelle, MCD/MLD, normalisation',
 'Data',
 'Conception'),

('Développement SQL et procédures stockées',
 'Écriture de requêtes complexes, vues, fonctions et procédures',
 'Data',
 'Execution'),

('Optimisation des performances base de données',
 'Indexation, tuning SQL, analyse des plans d’exécution',
 'Data',
 'Pilotage'),

('Migration et montée de version SGBD',
 'Migration Oracle, PostgreSQL, MySQL, SQL Server',
 'Data',
 'Pilotage'),

('Gestion des transactions et intégrité',
 'Contraintes, isolation, cohérence des données',
 'Data',
 'Conception');


INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Conception technique et choix d’architecture',
 'Définition des patterns, standards techniques et bonnes pratiques',
 'Architecture',
 'Conception'),

('Revue de code',
 'Contrôle qualité, respect des standards, sécurité et performance',
 'Leadership',
 'Pilotage'),

('Encadrement et mentoring développeurs',
 'Accompagnement technique, montée en compétence de l’équipe',
 'Leadership',
 'Pilotage'),

('Estimation des charges et planification',
 'Chiffrage des tâches, découpage technique, priorisation',
 'Gestion',
 'Pilotage');


INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Définition de l’architecture applicative',
 'Architecture microservices, modulaire ou hexagonale',
 'Architecture',
 'Décision'),

('Urbanisation du SI',
 'Cartographie applicative, cohérence des flux et dépendances',
 'Architecture',
 'Décision'),

('Conception des API et contrats',
 'Design REST, versioning, documentation OpenAPI',
 'Architecture',
 'Conception'),

('Gestion de la performance et scalabilité',
 'Analyse des goulots, stratégies de montée en charge',
 'Architecture',
 'Décision');

INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Mise en place pipelines CI/CD',
 'Automatisation des builds, tests et déploiements',
 'DevOps',
 'Pilotage'),

('Dockerisation des applications',
 'Création d’images Docker, Docker Compose',
 'DevOps',
 'Execution'),

('Déploiement cloud',
 'Déploiement sur AWS, gestion des environnements',
 'Cloud',
 'Execution'),

('Supervision et monitoring',
 'Mise en place Prometheus, Grafana, alerting',
 'DevOps',
 'Pilotage');

INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Mise en œuvre de la sécurité applicative',
 'Implémentation JWT, OAuth2, gestion des rôles et permissions',
 'Security',
 'Conception'),

('Audit de sécurité applicatif',
 'Analyse des vulnérabilités, OWASP Top 10',
 'Security',
 'Pilotage'),

('Sécurisation des données',
 'Chiffrement, protection des données sensibles, RGPD',
 'Security',
 'Décision'),

('Gestion des accès et identités',
 'RBAC, intégration Keycloak ou IAM',
 'Security',
 'Conception');

INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Sécurisation des accès aux bases de données',
 'Gestion des rôles, privilèges et accès applicatifs',
 'Security',
 'Conception'),

('Audit SQL et conformité',
 'Contrôle des accès, traçabilité, conformité RGPD',
 'Security',
 'Pilotage');

INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Animation des cérémonies Scrum',
 'Organisation et animation des Daily, Sprint Planning, Review et Rétrospective',
 'Agile',
 'Pilotage'),

('Facilitation de l’équipe Scrum',
 'Suppression des obstacles, amélioration de la collaboration et du flux',
 'Agile',
 'Pilotage'),

('Suivi des indicateurs agiles',
 'Vélocité, burndown, capacité et amélioration continue',
 'Agile',
 'Pilotage'),

('Coaching agile',
 'Accompagnement de l’équipe et des parties prenantes sur les pratiques Scrum',
 'Agile',
 'Conception');

INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Participation aux rituels Scrum',
 'Contribution active aux cérémonies et à l’amélioration continue',
 'Agile',
 'Execution'),

('Estimation collective des user stories',
 'Planning Poker, estimation en points, engagement d’équipe',
 'Agile',
 'Execution'),

('Développement en sprint',
 'Réalisation des user stories dans le respect de la Definition of Done',
 'Agile',
 'Execution'),

('Collaboration transverse',
 'Travail en équipe pluridisciplinaire (dev, test, PO)',
 'Agile',
 'Execution');

INSERT INTO expertises (titre, description, categorie, niveau_responsabilite) VALUES
('Alignement technique avec le Product Owner',
 'Traduction des besoins métier en solutions techniques',
 'Agile',
 'Conception'),

('Garantie de la qualité en sprint',
 'Respect des standards, dette technique maîtrisée',
 'Quality',
 'Pilotage');


-- Projet
INSERT INTO projets (nom, description, date_debut, date_fin, client)
VALUES ('Migration Microservices EDF', 'Migration de l’ancien SI vers microservices Spring Boot', '2024-01-01', '2024-12-31', 'EDF');

-- Mission rattachée au projet
INSERT INTO missions (titre, description, date_debut, date_fin, role, projet_id)
VALUES ('Tech Lead Microservices', 'Encadrement équipe, définition architecture microservices', '2024-01-01', '2024-12-31', 'Tech Lead', 1);

-- Tâches
/**
INSERT INTO expertises (titre, description, categorie, niveau_responsabilite)
VALUES
('Conception architecture microservices', 'Définition des services, communication, résilience', 'Architecture', 'Décision'),
('Développement backend Spring Boot', 'Implémentation des APIs REST et logique métier', 'Backend', 'Execution'),
('Développement frontend Angular', 'Intégration des composants et état global', 'Frontend', 'Execution');
*/
-- Liaison mission ↔ tâches
INSERT INTO mission_expertise (mission_id, expertise_id) VALUES
(1, 1),
(1, 2),
(1, 3);

-- Liaison projet ↔ tâches
INSERT INTO projet_expertise (projet_id, expertise_id) VALUES
(1, 1),
(1, 2),
(1, 3);
