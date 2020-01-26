create table endereco (
    id bigint(20) NOT NULL,
    nome varchar(255) NOT NULL,
    numero varchar(10) NOT NULL,
    complemento varchar(255),
    bairro varchar(255) NOT NULL,
    cidade varchar(255) NOT NULL,
    pais varchar(100) NOT NULL,
    cep varchar(10) NOT NULL,
    latitude float ,
    longitude float
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE endereco
    ADD PRIMARY KEY(id);

ALTER TABLE endereco
    MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;
