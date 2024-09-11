CREATE TABLE media (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       media_url VARCHAR(255),
                       media_id VARCHAR(255),
                       type VARCHAR(50),
                       service_id BIGINT,
                       FOREIGN KEY (service_id) REFERENCES services(id)
);