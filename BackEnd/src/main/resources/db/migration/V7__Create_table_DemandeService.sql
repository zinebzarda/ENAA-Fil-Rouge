CREATE TABLE demande_service (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 date_demmande DATE NOT NULL,  -- Change from DATE to DATETIME
                                 statut VARCHAR(50) NOT NULL,
                                 client_id BIGINT,
                                 service_id BIGINT,
                                 FOREIGN KEY (client_id) REFERENCES client(id),
                                 FOREIGN KEY (service_id) REFERENCES services(id)
);