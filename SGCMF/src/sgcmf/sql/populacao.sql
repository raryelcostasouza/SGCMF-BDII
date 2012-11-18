-- Insercao dos Usuarios
insert into Usuario values (1,'Usuario 1','123.456.789-10', 'admin@sgcmf.com', 'Administrador','admin','admin');
insert into Usuario values (2,'Usuario 2','213.456.789-20', 'usuario2@sgcmf.com', 'Administrador','user2','user2');
insert into Usuario values (3,'Usuario 3','321.456.789-30', 'usuario3@sgcmf.com', 'Tecnico da Selecao','user3','user3');
insert into Usuario values (4,'Usuario 4','423.156.789-40', 'usuario4@sgcmf.com', 'Tecnico da Selecao','user4','user4');
insert into Usuario values (5,'Usuario 5','523.416.789-50', 'usuario5@sgcmf.com', 'Tecnico da Selecao','user5','user5');
insert into Usuario values (6,'Usuario 6','623.451.789-60', 'usuario6@sgcmf.com', 'Tecnico da Selecao','user6','user6');
insert into Usuario values (7,'Usuario 7','723.456.189-70', 'usuario7@sgcmf.com', 'Tecnico da Selecao','user7','user7');
insert into Usuario values (8,'Usuario 8','823.456.189-80', 'usuario8@sgcmf.com', 'Tecnico da Selecao','user8','user8');
insert into Usuario values (9,'Usuario 9','923.456.781-90', 'usuario9@sgcmf.com', 'Tecnico da Selecao','user9','user9');
insert into Usuario values (10,'Usuario 10','231.456.789.10', 'usuario10@sgcmf.com', 'Tecnico da Selecao','user10','user10');
insert into Usuario values (11,'Usuario 11','132.456.789-20', 'usuario11@sgcmf.com', 'Tecnico da Selecao','user11','user11');
insert into Usuario values (12,'Usuario 12','134.256.789-30', 'usuario12@sgcmf.com', 'Tecnico da Selecao','user12','user12');
insert into Usuario values (13,'Usuario 13','671.416.789-40', 'usuario13@sgcmf.com', 'Tecnico da Selecao','user13','user13');
insert into Usuario values (14,'Usuario 14','387.451.789-50', 'usuario14@sgcmf.com', 'Tecnico da Selecao','user14','user14');
insert into Usuario values (15,'Usuario 15','413.456.189-60', 'usuario15@sgcmf.com', 'Tecnico da Selecao','user15','user15');
insert into Usuario values (16,'Usuario 16','911.456.189-70', 'usuario16@sgcmf.com', 'Tecnico da Selecao','user16','user16');
insert into Usuario values (17,'Usuario 17','785.456.781-80', 'usuario17@sgcmf.com', 'Tecnico da Selecao','user17','user17');
insert into Usuario values (18,'Usuario 18','785.456.781-90', 'usuario18@sgcmf.com', 'Membro Comite','user18','user18');
insert into Usuario values (19,'Usuario 19','785.456.781-10', 'usuario19@sgcmf.com', 'Membro Comite','user19','user19');
insert into Usuario values (20,'Usuario 20','785.456.781-20', 'usuario20@sgcmf.com', 'Entusiasta','user20','user20');

-- Insercao das Selecoes
insert into Selecao  values (1,'Franca','img/selecoes32/grupoa/France.png',3);
insert into Selecao  values (2,'Mexico','img/selecoes32/grupoa/Mexico.png',4);
insert into Selecao  values (3,'Africa do Sul','img/selecoes32/grupoa/South-Africa.png',5);
insert into Selecao  values (4,'Uruguai','img/selecoes32/grupoa/Uruguay.png',6);

insert into Selecao  values (5,'Argentina','img/selecoes32/grupob/Argentina.png',7);
insert into Selecao  values (6,'Grecia','img/selecoes32/grupob/Greece.png',8);
insert into Selecao  values (7,'Nigeria','img/selecoes32/grupob/Nigeria.png',9);
insert into Selecao  values (8,'Coreia do sul','img/selecoes32/grupob/South-Korea.png',10);

insert into Selecao  values (9,'Algeria','img/selecoes32/grupoc/Algeria.png',11);
insert into Selecao  values (10,'Inglaterra','img/selecoes32/grupoc/England.png',12);
insert into Selecao  values (11,'Eslovenia','img/selecoes32/grupoc/Slovenia.png',13);
insert into Selecao  values (12,'Estados Unidos','img/selecoes32/grupoc/United-States.png',14);

insert into Selecao  values (13,'Australia','img/selecoes32/grupod/Australia.png',15);
insert into Selecao  values (14,'Alemanha','img/selecoes32/grupod/Germany.png',16);
insert into Selecao  values (15,'Gana','img/selecoes32/grupod/Ghana.png',17);
insert into Selecao  values (16,'Servia','img/selecoes32/grupod/Serbia.png',17);

-- Insercao dos Jogos
insert into Jogo  values (1,'Primeira Fase', 'Rio de Janeiro', 'Maracana', 1,2,false,'2012-08-10 10:20:00 +02');
insert into Jogo  values (2,'Oitavas de Final', 'Sao Paulo', 'Morumbi', 3,4,false,'2012-08-11 11:20:00 +02');
insert into Jogo  values (3,'Oitavas de Final', 'Belo Horizonte', 'Mineirao', 5,6,false,'2012-08-12 12:20:00 +01');
insert into Jogo  values (4,'Quartas de final', 'Rio de Janeiro', 'Maracana', 7,8,false,'2012-08-13 13:20:00 +01');
insert into Jogo  values (5,'Quartas de final', 'Sao Paulo', 'Morumbi', 9,10,false,'2012-08-14 10:20:00 +02');
insert into Jogo  values (6,'Semi-final', 'Belo Horizonte', 'Mineirao', 11,12,false,'2012-08-15 11:20:00 -03');
insert into Jogo  values (7,'Terceiro Lugar', 'Rio de Janeiro', 'Maracana', 13,14,false,'2012-08-16 12:20:00 -02');
insert into Jogo  values (8,'Final', 'Sao Paulo', 'Morumbi', 5,2,false,'2012-08-17 13:20:00 +02');
insert into Jogo  values (9,'Primeira Fase', 'Belo Horizonte', 'Mineirao', 3,5,false,'2012-08-18 11:20:00 +02');
insert into Jogo  values (10,'Primeira Fase', 'Rio de Janeiro', 'Maracana', 1,6,false,'2012-08-19 12:20:00 +02');

-- Insercao dos Jogadores
insert into Jogador  values (1,'Jogador 1', 1.93, 1, '1990-10-10', 'Goleiro', true, 1);
insert into Jogador  values (2,'Jogador 2', 1.93, 2, '1990-10-10', 'Zagueiro', true, 1);
insert into Jogador  values (3,'Jogador 3', 1.93, 3, '1990-10-10', 'Zagueiro', true, 1);
insert into Jogador  values (4,'Jogador 4', 1.93, 4, '1990-10-10', 'Zagueiro', true, 1);
insert into Jogador  values (5,'Jogador 5', 1.93, 5, '1990-10-10', 'Lateral Esquerdo', true, 1);
insert into Jogador  values (6,'Jogador 6', 1.93, 6, '1990-10-10', 'Lateral Direito', true, 1);
insert into Jogador  values (7,'Jogador 7', 1.93, 7, '1990-10-10', 'Atacante', true, 1);
insert into Jogador  values (8,'Jogador 8', 1.93, 15, '1990-10-10', 'Volante', false, 1);
insert into Jogador  values (9,'Jogador 9', 1.93, 16, '1990-10-10', 'Volante', false, 1);
insert into Jogador  values (10,'Jogador 10', 1.93, 17, '1990-10-10', 'Zagueiro', false, 1);
insert into Jogador  values (25,'Jogador 11', 1.93, 23, '1990-10-10', 'Zagueiro', false, 1);
insert into Jogador  values (11,'Jogador 11', 1.93, 1, '1990-10-10', 'Goleiro', true, 2);
insert into Jogador  values (12,'Jogador 12', 1.93, 2, '1990-10-10', 'Zagueiro', true, 2);
insert into Jogador  values (13,'Jogador 13', 1.93, 3, '1990-10-10', 'Zagueiro', true, 2);
insert into Jogador  values (14,'Jogador 14', 1.93, 4, '1990-10-10', 'Lateral Esquerdo', true, 2);
insert into Jogador  values (15,'Jogador 15', 1.93, 5, '1990-10-10', 'Lateral Direito', true, 2);
insert into Jogador  values (16,'Jogador 16', 1.93, 6, '1990-10-10', 'Volante', true, 2);
insert into Jogador  values (17,'Jogador 17', 1.93, 7, '1990-10-10', 'Atacante', true, 2);
insert into Jogador  values (18,'Jogador 18', 1.93, 11, '1990-10-10', 'Atacante', false, 2);
insert into Jogador  values (19,'Jogador 19', 1.93, 18, '1990-10-10', 'Zagueiro', false, 2);
insert into Jogador  values (20,'Jogador 20', 1.93, 21, '1990-10-10', 'Goleiro', false, 2);
insert into Jogador  values (21,'Jogador 21', 1.93, 19, '1990-10-10', 'Goleiro', false, 2);
insert into Jogador  values (22,'Jogador 22', 1.93, 13, '1990-10-10', 'Atacante', false, 2);
insert into Jogador  values (23,'Jogador 23', 1.93, 14, '1990-10-10', 'Zagueiro', false, 2);
insert into Jogador  values (24,'Jogador 24', 1.93, 15, '1990-10-10', 'Lateral Direito', false, 2);

-- Insercao de Ocorrencias
insert into Ocorrencia values (1,'10:00',1);
insert into Ocorrencia values (2,'10:10',1);
insert into Ocorrencia values (3,'10:20',1);
insert into Ocorrencia values (4,'10:30',1);
insert into Ocorrencia values (5,'10:40',1);
insert into Ocorrencia values (6,'10:50',1);
insert into Ocorrencia values (7,'11:00',1);
insert into Ocorrencia values (8,'11:10',1);
insert into Ocorrencia values (9,'11:20',1);
insert into Ocorrencia values (10,'11:30',1);
insert into Ocorrencia values (11,'11:40',1);
insert into Ocorrencia values (12,'11:50',1);
insert into Ocorrencia values (13,'12:10',1);
insert into Ocorrencia values (14,'12:20',1);
insert into Ocorrencia values (15,'12:30',1);
insert into Ocorrencia values (16,'12:40',1);
insert into Ocorrencia values (17,'12:50',1);
insert into Ocorrencia values (18,'13:10',1);
insert into Ocorrencia values (19,'13:20',1);
insert into Ocorrencia values (20,'13:30',1);
insert into Ocorrencia values (21,'13:40',1);
insert into Ocorrencia values (22,'13:50',1);
insert into Ocorrencia values (23,'14:00',1);
insert into Ocorrencia values (24,'14:20',1);
insert into Ocorrencia values (25,'14:30',1);
insert into Ocorrencia values (26,'14:40',1);
insert into Ocorrencia values (27,'14:50',1);
insert into Ocorrencia values (28,'15:10',1);
insert into Ocorrencia values (29,'15:20',1);
insert into Ocorrencia values (30,'15:30',1);
insert into Ocorrencia values (31,'15:40',1);
insert into Ocorrencia values (32,'15:50',1);
insert into Ocorrencia values (33,'16:10',1);
insert into Ocorrencia values (34,'16:20',1);
insert into Ocorrencia values (35,'16:30',1);
insert into Ocorrencia values (36,'16:40',1);
insert into Ocorrencia values (37,'16:50',10);
insert into Ocorrencia values (38,'17:10',10);
insert into Ocorrencia values (39,'17:20',10);
insert into Ocorrencia values (40,'17:30',8);
insert into Ocorrencia values (41,'17:30',1);
insert into Ocorrencia values (42,'17:40',1);
insert into Ocorrencia values (43,'17:50',1);
insert into Ocorrencia values (44,'18:10',1);
insert into Ocorrencia values (45,'18:20',1);
insert into Ocorrencia values (46,'18:30',1);
insert into Ocorrencia values (47,'18:40',1);
insert into Ocorrencia values (48,'18:50',1);
insert into Ocorrencia values (49,'19:10',1);
insert into Ocorrencia values (50,'19:20',1);

-- Insercao dos Gols
insert into Gol values (1,4,null, 'A Favor', 'Comum');
insert into Gol values (2,5,null, 'A Favor', 'Comum');
insert into Gol values (3,6,null, 'A Favor', 'Comum');
insert into Gol values (4,7,null, 'A Favor', 'Comum');
insert into Gol values (5,7,null, 'A Favor', 'Comum');
insert into Gol values (6,14,null, 'A Favor', 'Comum');
insert into Gol values (7,15,null, 'A Favor', 'Comum');
insert into Gol values (8,16,null, 'A Favor', 'Comum');
insert into Gol values (9,17,null, 'A Favor', 'Comum');
insert into Gol values (10,17,null, 'A Favor', 'Comum');

-- Insercao dos cartoes
insert into Cartao values (11,1,'Amarelo');
insert into Cartao values (12,2,'Amarelo');
insert into Cartao values (13,3,'Amarelo');
insert into Cartao values (14,4,'Amarelo');
insert into Cartao values (15,5,'Amarelo');
insert into Cartao values (16,6,'Amarelo');
insert into Cartao values (17,7,'Amarelo');
insert into Cartao values (18,15,'Amarelo');
insert into Cartao values (19,16,'Amarelo');
insert into Cartao values (20,17,'Amarelo');

-- Insercao das faltas
insert into Falta values (21, null, 4, 'Falta Comum');
insert into Falta values (22, null, 5, 'Falta Comum');
insert into Falta values (23, 13, 6, 'Falta Comum');
insert into Falta values (24, null, 7, 'Falta Comum');
insert into Falta values (25, null, 3, 'Falta Comum');
insert into Falta values (26, 15, 14, 'Falta Comum');
insert into Falta values (27, null, 15, 'Falta Comum');
insert into Falta values (28, null, 16, 'Falta Comum');
insert into Falta values (29, 17, 17, 'Falta Comum');
insert into Falta values (30, 20, 13, 'Falta Comum');

-- Insercao das substituicoes
insert into Substituicao values (31, 8, 1, 'Estrategica');
insert into Substituicao values (32, 9, 2, 'Estrategica');
insert into Substituicao values (33, 10, 3, 'Contusao');
insert into Substituicao values (34, 18, 11, 'Estrategica');
insert into Substituicao values (35, 19, 12, 'Estrategica');
insert into Substituicao values (36, 20, 13, 'Contusao');
insert into Substituicao values (37, 1, 6, 'Estrategica');
insert into Substituicao values (38, 9, 7, 'Estrategica');
insert into Substituicao values (39, 10, 5, 'Estrategica');
insert into Substituicao values (40, 24, 13, 'Contusao');