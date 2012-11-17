create user javaAppSGCMF password 'java';

grant select, insert, delete, update on table Usuario to javaAppSGCMF;
grant select, insert, delete, update on table Selecao to javaAppSGCMF;
grant select, insert, delete, update on table Jogo to javaAppSGCMF;
grant select, insert, delete, update on table Jogador to javaAppSGCMF;
grant select, insert, delete, update on table RodadaDePenaltis to javaAppSGCMF;
grant select, insert, delete, update on table Ocorrencia to javaAppSGCMF;
grant select, insert, delete, update on table Gol to javaAppSGCMF;
grant select, insert, delete, update on table Cartao to javaAppSGCMF;
grant select, insert, delete, update on table Falta to javaAppSGCMF;
grant select, insert, delete, update on table Substituicao to javaAppSGCMF;

grant select, update on sequence ocorrencia_id_seq to javaAppSGCMF;
grant select, update on sequence jogador_id_seq to javaAppSGCMF;
grant select, update on sequence jogo_id_seq to javaAppSGCMF;
grant select, update on sequence rodadadepenaltis_id_seq to javaAppSGCMF;
grant select, update on sequence selecao_id_seq to javaAppSGCMF;
grant select, update on sequence usuario_id_seq to javaAppSGCMF;
grant select, update on sequence cartao_id_seq to javaAppSGCMF;
