CREATE TABLE poder (
id bigint not null primary key auto_increment,
danobase int not null,
nome varchar(50),
efeito varchar(100),
descricao varchar(256),
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

INSERT INTO poder (danobase, nome, efeito, descricao, tipo) VALUES (5, "Tackle", "normal", "A maior parte dos pokemons conhece esse golpe que pode ser muito efetivo.","physical");

INSERT INTO poder (danobase, nome, efeito, descricao, tipo) VALUES (7, "Vine Whip", "grass", "Ataca o oponene com um par de shicotes de sipó.","physical");

INSERT INTO poder (danobase, nome, efeito, descricao, tipo) VALUES (10, "Ember", "fire", "Ataca o oponene com uma pequena chama que pode deixar o oponente queimando.","power");

INSERT INTO poder (danobase, nome, efeito, descricao, tipo) VALUES (5, "Bug Bite", "bug", "Uma mordida cortante de inseto.","physical");

INSERT INTO poder (danobase, nome, efeito, descricao, tipo) VALUES (8, "Wing Attack", "flying", "Voa em direção ao oponente e ataca com as asas.","physical");


