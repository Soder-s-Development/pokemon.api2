CREATE TABLE poder (
id bigint not null primary key auto_increment,
danobase int not null,
nome varchar(50),
efeito varchar(256),
tipo varchar(100)
);


CREATE TABLE poder_unico (
	id bigint not null auto_increment primary key,
	id_poder bigint not null,
	id_pkm_unico bigint,
	novo_dano int
);
alter table poder_unico add constraint fk_poder_unico_pokemon
foreign key (id_pkm_unico) references pokemon_unico(id);

alter table poder_unico add constraint fk_poder_unico
foreign key (id_poder) references poder(id);

INSERT INTO poder (danobase, nome, efeito, tipo) VALUES (5, "Tackle", "30%stun", "physical");
