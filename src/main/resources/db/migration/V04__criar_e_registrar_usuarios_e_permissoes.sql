CREATE TABLE usuario (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL ,
    senha VARCHAR(150) NOT NULL,
    roles VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nome, email, senha, roles) values ('Administrador', 'admin@gmail.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.', 'ADMIN');
INSERT INTO usuario (nome, email, senha, roles) values ('Maria Silva', 'maria@gmail.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq', 'USER');