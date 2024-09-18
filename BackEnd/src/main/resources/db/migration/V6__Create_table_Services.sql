CREATE TABLE services (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          titre VARCHAR(255),
                          description TEXT,
                          prix FLOAT,
                          prestataire_id BIGINT,
                          FOREIGN KEY (prestataire_id) REFERENCES prestataire(id)
);