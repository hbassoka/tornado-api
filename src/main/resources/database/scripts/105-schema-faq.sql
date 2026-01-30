
DROP TABLE IF EXISTS faqs;

CREATE TABLE faqs (
    id BIGSERIAL PRIMARY KEY,
    question TEXT NOT NULL,
    reponse TEXT NOT NULL,
    created_by VARCHAR(64) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(64) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




insert into faqs(question,reponse) values
    
    (   'Qu''est-ce que cette application web ?',
      'C’est une application full-stack avec Angular en frontend et Spring Boot en backend.'
    ),
    
    (   'Pourquoi utiliser Angular ?',
      'Angular fournit un framework robuste avec TypeScript, un système de composants, et une bonne structure pour les applications complexes.'
    ),
    
    (   'Comment Angular communique-t-il avec Spring Boot ?',
      'Via des appels HTTP REST utilisant HttpClient, typiquement en JSON.'
    ),
    
    (   'Comment l''application est-elle déployée ?',
      'Elle est buildée, testée, dockerisée puis déployée automatiquement via un pipeline CI/CD (GitHub Actions, GitLab CI, etc.).'
    ),
    
     (  'Qu''est-ce que DevSecOps ?',
      'C’est l’intégration de la sécurité dans les pratiques DevOps : scans de sécurité, analyse de dépendances, test des vulnérabilités dès la CI.'
    ),
    
     (  'Quels types de tests sont effectués ?',
      'Tests unitaires (Jest ou Karma/Jasmine pour Angular, JUnit pour Spring), tests d’intégration, tests de sécurité, linters.'
    ),
    
     (  'L''application est-elle sécurisée ?',
      'Oui, elle utilise JWT, Spring Security, gestion des rôles, et des scans de vulnérabilités automatisés.'
    ),
    
    (   'Comment sont gérées les erreurs HTTP ?',
      'Le backend renvoie des codes HTTP standards et Angular les intercepte pour les afficher de manière conviviale.'
    )
;

