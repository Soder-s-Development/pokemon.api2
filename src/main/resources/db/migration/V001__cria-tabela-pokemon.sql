create table pokemon(
	id bigint not null auto_increment,
    nome varchar(50) not null,
    nivel int not null,
    Atk int not null,
    Def int not null,
    SpA int not null,
    SpD int not null,
    Spe int not null,
    HP int not null,
    
    primary key (id)
);