create table item(

	id bigint not null auto_increment primary key,
	idProprietario bigint not null,
	idContaAssociada bigint not null,
	nome varchar(100) not null,
	descricao varchar(200),
	valor_de_compra int,
	valor_de_venda int
);
