CREATE TABLE prestataire (
                             id BIGINT PRIMARY KEY,
                             domaine_expertise VARCHAR(255),
                             disponibilites VARCHAR(255),
                             experience VARCHAR(255),
                             validate_status VARCHAR(50) DEFAULT 'EN_ATTENTE',
                             FOREIGN KEY (id) REFERENCES personne(id)
);