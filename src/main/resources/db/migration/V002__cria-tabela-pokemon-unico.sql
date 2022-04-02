create table pokemon_unico(
	id bigint not null auto_increment,
	id_pokemon bigint not null,
    apelido varchar(50) not null,
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
   	crias string varchar(256),
	vivo  bool not null
    primary key (id)
);

alter table pokemon_unico add constraint fk_id_pokemon_unico
foreign key (id_pokemon) references pokemon (id);