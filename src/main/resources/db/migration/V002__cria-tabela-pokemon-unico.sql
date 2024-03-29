create table pokemon_unico(
	id bigint not null auto_increment,
	id_pokemon bigint not null,
	personagem_id bigint not null,
    apelido varchar(50) not null,
    nome_pokemon varchar(50) not null,
    tipo varchar(100) not null,
   	nivel int not null,
   	novo_atk int,
   	novo_def int,
   	novo_spa int,
   	novo_spd int,
   	novo_spe int,
   	novo_hp int,
   	
   	dias_de_vida int not null,
   	dias_vivido int,
   	conquistas varchar(512),
   	crias varchar(256),
   	genero varchar(10) not null,
	vivo  bool not null,
	hp_atual int,
	stamina int,
	stamina_atual int,
	evoluvao_estado smallint,
	
	
    primary key (id)
);
