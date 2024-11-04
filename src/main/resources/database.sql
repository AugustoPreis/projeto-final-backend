CREATE TABLE usuarios (
	id serial NOT NULL PRIMARY KEY,
	nome varchar(100) NOT NULL,
	email varchar(100) NOT NULL UNIQUE,
	senha varchar(20) NOT NULL
);

CREATE TABLE categorias_nota (
	id serial NOT NULL PRIMARY KEY,
	descricao varchar(50) NOT NULL
);

CREATE TABLE notas (
	id serial NOT NULL PRIMARY KEY,
	categoria_nota_id int NOT NULL REFERENCES categorias_nota,
	usuario_id int NOT NULL REFERENCES usuarios,
	titulo varchar(100) NOT NULL,
	descricao varchar(255) NULL
);

INSERT INTO usuarios VALUES
	(DEFAULT, 'Administrador', 'admin@gmail.com', 'admin123'),
	(DEFAULT, 'Funcionário', 'funcionario@gmail.com', 'funcionario123'),
	(DEFAULT, 'Gerente', 'gerente@gmail.com', 'gerente123');

INSERT INTO categorias_nota VALUES
	(DEFAULT, 'Urgente'),
	(DEFAULT, 'Não Urgente');