CREATE TABLE pessoa(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN,
    logradouro VARCHAR(255),
    numero VARCHAR(255),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cep VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Luiz', true, 'Rua Pedro Aleixo', '08','Apartamento', 'CIC', '21054552', 'Curitiba', 'Parana');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Pedro', false, 'Rua Oziel Fonseca', '66','Casa', 'Rebouças', '81265228', 'Curitiba', 'Parana');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Guilherme', true, 'Rua 7 de Setembro', '102','Casa', 'Agua Verde', '87452115', 'Toledo', 'Parana');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Fernando', false, 'AV Getulio Vargas', '06','Apartamento', 'Filadelfia', '87546229', 'São Paulo', 'São Paulo');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('José Pereira', true, 'Rua Iguaçu', '56','Casa', 'Vila verde', '75425669', 'Cuiaba', 'Mato Grosso');