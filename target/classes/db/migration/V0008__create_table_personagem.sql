create table personagem(

	id bigint not null auto_increment primary key,
	id_conta bigint not null,
	nome varchar(100) not null,
	pkmu_ids varchar(512),
	hold_ids varchar(10),
	nivel int,
	experiencia int
)
