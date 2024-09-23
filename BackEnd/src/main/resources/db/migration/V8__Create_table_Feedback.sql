CREATE TABLE feedback (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          note INT NOT NULL,
                          commentaire VARCHAR(255),
                          date_creation DATE NOT NULL,
                          demande_service_id BIGINT,
                          FOREIGN KEY (demande_service_id) REFERENCES demande_service(id)
);