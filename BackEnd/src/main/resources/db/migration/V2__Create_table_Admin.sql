CREATE TABLE admin (
                       id BIGINT PRIMARY KEY,
                       FOREIGN KEY (id) REFERENCES personne(id)
);
