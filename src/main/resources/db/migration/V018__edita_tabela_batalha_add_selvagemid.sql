ALTER TABLE batalhas DROP COLUMN vencedorId;

ALTER TABLE batalhas ADD pokemon_selvagem_id bigint;

ALTER TABLE batalhas ADD vencedor_id bigint;
