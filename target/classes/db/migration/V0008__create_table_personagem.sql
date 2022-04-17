create table personagem(

	id bigint not null auto_increment primary key,
	id_conta not null,
	nome varchar(100) not null,
	pkmu_ids varchar(max),
	hold_ids varchar(11)
)
alter table personagem add constraint fk_id_pokemon_unico
foreign key (id_conta) references  account(id);