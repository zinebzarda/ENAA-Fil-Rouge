CREATE TABLE client (
                        id BIGINT PRIMARY KEY,
                        adresse VARCHAR(255),
                        FOREIGN KEY (id) REFERENCES personne(id)
);