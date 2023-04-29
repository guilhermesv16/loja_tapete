-- Cadastrando Estados
insert into estado ("nome", "sigla") values ('Acre','AC');
insert into estado ("nome", "sigla") values ('Alagoas','AL');
insert into estado ("nome", "sigla") values ('Amapá','AP');
insert into estado ("nome", "sigla") values ('Amazonas','AM');
insert into estado ("nome", "sigla") values ('Bahia','BA');
insert into estado ("nome", "sigla") values ('Ceará','CE');
insert into estado ("nome", "sigla") values ('Espírito Santo','ES');
insert into estado ("nome", "sigla") values ('Goiás','GO');
insert into estado ("nome", "sigla") values ('Maranhão','MA');
insert into estado ("nome", "sigla") values ('Mato Grosso','MT');
insert into estado ("nome", "sigla") values ('Mato Grosso do Sul','MS');
insert into estado ("nome", "sigla") values ('Minas Gerais','MG');
insert into estado ("nome", "sigla") values ('Pará','PA');
insert into estado ("nome", "sigla") values ('Paraíba','PB');
insert into estado ("nome", "sigla") values ('Paraná','PR');
insert into estado ("nome", "sigla") values ('Pernambuco','PE');
insert into estado ("nome", "sigla") values ('Piauí','PI');
insert into estado ("nome", "sigla") values ('Rio de Janeiro','RJ');
insert into estado ("nome", "sigla") values ('Rio Grande do Norte','RN');
insert into estado ("nome", "sigla") values ('Rio Grande do Sul','RS');
insert into estado ("nome", "sigla") values ('Rondônia','RO');
insert into estado ("nome", "sigla") values ('Roraima','RR');
insert into estado ("nome", "sigla") values ('Santa Catarina','SC');
insert into estado ("nome", "sigla") values ('São Paulo','SP');
insert into estado ("nome", "sigla") values ('Sergipe','SE');
insert into estado ("nome", "sigla") values ('Tocantins','TO');
insert into estado ("nome", "sigla") values ('Distrito Federal','DF');

-- Cadastrando municipios
-- Acre
insert into cidade ("nome", "estado_id") values ('Rio Branco', 1);
insert into cidade ("nome", "estado_id") values ('Cruzeiro do Sul', 1); 
insert into cidade ("nome", "estado_id") values ('Sena Madureira', 1);
-- Alagoas: 
insert into cidade ("nome", "estado_id") values ('Maceió', 2); 
insert into cidade ("nome", "estado_id") values ('Maragogi', 2);
insert into cidade ("nome", "estado_id") values ('Porto de Galinhas', 2);
-- Amapá: 
insert into cidade ("nome", "estado_id") values ('Macapá', 3);
insert into cidade ("nome", "estado_id") values ('Santana', 3);
insert into cidade ("nome", "estado_id") values ('Oiapoque', 3);
-- Amazonas: 
insert into cidade ("nome", "estado_id") values ('Manaus', 4); 
insert into cidade ("nome", "estado_id") values ('Parintins', 4);
insert into cidade ("nome", "estado_id") values ('Itacoatiara', 4);
-- Bahia: 
insert into cidade ("nome", "estado_id") values ('Salvador', 5);
insert into cidade ("nome", "estado_id") values ('Porto Seguro', 5);
insert into cidade ("nome", "estado_id") values ('Ilhéus', 5);
-- Ceará: 
insert into cidade ("nome", "estado_id") values ('Fortaleza', 6);
insert into cidade ("nome", "estado_id") values ('Jericoacoara', 6); 
insert into cidade ("nome", "estado_id") values ('Canoa Quebrada', 6);
-- Espírito Santo: 
insert into cidade ("nome", "estado_id") values ('Vitória', 7);
insert into cidade ("nome", "estado_id") values ('Vila Velha', 7);
insert into cidade ("nome", "estado_id") values ('Guarapari', 7);
-- Goiás: 
insert into cidade ("nome", "estado_id") values ('Goiânia', 8);
insert into cidade ("nome", "estado_id") values ('Caldas Novas', 8);
insert into cidade ("nome", "estado_id") values ('Pirenópolis', 8);
-- Maranhão: 
insert into cidade ("nome", "estado_id") values ('São Luís', 9);
insert into cidade ("nome", "estado_id") values ('Barreirinhas', 9);
insert into cidade ("nome", "estado_id") values ('Imperatriz', 9);
-- Mato Grosso: 
insert into cidade ("nome", "estado_id") values ('Cuiabá', 10);
insert into cidade ("nome", "estado_id") values ('Chapada dos Guimarães', 10);
insert into cidade ("nome", "estado_id") values ('Sinop', 10);
-- Mato Grosso do Sul: 
insert into cidade ("nome", "estado_id") values ('Campo Grande', 11);
insert into cidade ("nome", "estado_id") values ('Bonito', 11);
insert into cidade ("nome", "estado_id") values ('Corumbá', 11);
-- Minas Gerais: 
insert into cidade ("nome", "estado_id") values ('Belo Horizonte', 12);
insert into cidade ("nome", "estado_id") values ('Ouro Preto', 12);
insert into cidade ("nome", "estado_id") values ('Tiradentes', 12);
-- Pará: 
insert into cidade ("nome", "estado_id") values ('Belém', 13);
insert into cidade ("nome", "estado_id") values ('Santarém', 13);
insert into cidade ("nome", "estado_id") values ('Marabá', 13);
-- Paraíba: 
insert into cidade ("nome", "estado_id") values ('João Pessoa', 14);
insert into cidade ("nome", "estado_id") values ('Campina Grande', 14);
insert into cidade ("nome", "estado_id") values ('Cabedelo', 14);
-- Paraná: 
insert into cidade ("nome", "estado_id") values ('Curitiba', 15);
insert into cidade ("nome", "estado_id") values ('Foz do Iguaçu', 15);
insert into cidade ("nome", "estado_id") values ('Londrina', 15);
-- Pernambuco: 
insert into cidade ("nome", "estado_id") values ('Recife', 16);
insert into cidade ("nome", "estado_id") values ('Olinda', 16);
insert into cidade ("nome", "estado_id") values ('Porto de Galinhas', 16);
-- Piauí: 
insert into cidade ("nome", "estado_id") values ('Teresina', 17);
insert into cidade ("nome", "estado_id") values ('Parnaíba', 17);
insert into cidade ("nome", "estado_id") values ('Floriano', 17);
-- Rio de Janeiro: 
insert into cidade ("nome", "estado_id") values ('Rio de Janeiro', 18);
insert into cidade ("nome", "estado_id") values ('Búzios', 18);
insert into cidade ("nome", "estado_id") values ('Paraty', 18);
-- Rio Grande do Norte: 
insert into cidade ("nome", "estado_id") values ('Natal', 19);
insert into cidade ("nome", "estado_id") values ('Pipa', 19);
insert into cidade ("nome", "estado_id") values ('Mossoró', 19);
-- Rio Grande do Sul: 
insert into cidade ("nome", "estado_id") values ('Porto Alegre', 20);
insert into cidade ("nome", "estado_id") values ('Gramado', 20);
insert into cidade ("nome", "estado_id") values ('Santa Maria', 20);
-- Rondônia: 
insert into cidade ("nome", "estado_id") values ('Porto Velho', 21);
insert into cidade ("nome", "estado_id") values ('Ariquemes', 21);
insert into cidade ("nome", "estado_id") values ('Ji-Paraná', 21);
-- Roraima: 
insert into cidade ("nome", "estado_id") values ('Boa Vista', 22);
insert into cidade ("nome", "estado_id") values ('Caracaraí', 22);
insert into cidade ("nome", "estado_id") values ('Pacaraima', 22);
-- Santa Catarina: 
insert into cidade ("nome", "estado_id") values ('Florianópolis', 23);
insert into cidade ("nome", "estado_id") values ('Balneário Camboriú', 23);
insert into cidade ("nome", "estado_id") values ('Joinville', 23);
-- São Paulo: 
insert into cidade ("nome", "estado_id") values ('São Paulo', 24);
insert into cidade ("nome", "estado_id") values ('Campinas', 24);
insert into cidade ("nome", "estado_id") values ('Santos', 24);
-- Sergipe: 
insert into cidade ("nome", "estado_id") values ('Aracaju', 25);
insert into cidade ("nome", "estado_id") values ('São Cristóvão', 25);
insert into cidade ("nome", "estado_id") values ('Estância', 25);
-- Tocantins: 
insert into cidade ("nome", "estado_id") values ('Palmas', 26);
insert into cidade ("nome", "estado_id") values ('Gurupi', 26);
insert into cidade ("nome", "estado_id") values ('Araguaína', 26);
-- Distrito Federal: 
insert into cidade ("nome", "estado_id") values ('Brasília', 27);
insert into cidade ("nome", "estado_id") values ('Taguatinga', 27);
insert into cidade ("nome", "estado_id") values ('Ceilândia', 27);

-- Cadastrando Usuários
insert into usuario ("nomeusuario", "senha", "tipousuariomodel") values ('client@gmail.com','client','CLIENTE');
insert into usuario ("nomeusuario", "senha", "tipousuariomodel") values ('admin@gmail.com','admin','ADMINISTRADOR');

insert into cliente ("primeiro_nome", "sobrenome", "cpf", "rg", "usuario_id") values ('Cliente','Teste','123456789','123456',1);
insert into cliente ("primeiro_nome", "sobrenome", "cpf", "rg", "usuario_id") values ('Admin','Teste','987654321','654321',2);

insert into endereco ("endereco","complemento","cidade_id","cliente_id") values ('Q. 1106 S Al. 2 Lt. 36','Plano Diretor Sul',76,1);
insert into endereco ("endereco","complemento","cidade_id","cliente_id") values ('Q. 112 N Al. 10 Lt. 45','Plano Diretor Norte',76,2);

insert into tapete ("descricao", "preco", "estoque", "status", "material") values ('Tapete 1', 1000, 20, 'DISPONIVEL','Material 1');
insert into tapete ("descricao", "preco", "estoque", "status", "material") values ('Tapete 2', 500, 5, 'DISPONIVEL', 'Material 2');
insert into tapete ("descricao", "preco", "estoque", "status", "material") values ('Tapete 3', 200, 30, 'DISPONIVEL', 'Material ');
