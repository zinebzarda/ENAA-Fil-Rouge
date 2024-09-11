CREATE TABLE contact (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         message VARCHAR(255),
                         client_id BIGINT,
                         FOREIGN KEY (client_id) REFERENCES client(id)
);