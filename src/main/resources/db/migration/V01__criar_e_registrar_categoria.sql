CREATE TABLE categoria(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('SuperMercado');
INSERT INTO categoria (nome) VALUES ('Farmácia');
INSERT INTO categoria (nome) VALUES ('Outros');

