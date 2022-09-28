create table pokemon_db.batalhas (
	id bigint not null auto_increment primary key,
	id_conta1 bigint not null,
	id_conta2 bigint,
	vencedorId bigint
	
);
