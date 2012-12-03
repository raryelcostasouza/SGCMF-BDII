Create Table Usuario
(
	id smallserial primary key,
	nome text not null,
	cpf char(14) not null unique,
	email text not null unique,
	perfil text check(perfil in ('Administrador', 'Tecnico da Selecao', 'Membro Comite', 'Entusiasta')) not null,
	login text not null unique,
	senha text not null
);

Create Table Selecao
(
	id smallserial primary key,
	pais text not null unique,
	caminhoImgBandeira text not null,
	idUsuarioTecnico smallint not null,
        grupo char(1) check (grupo in ('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')) not null,
	foreign key (idUsuarioTecnico) references Usuario(id)
);

Create Table Jogo
(
	id smallserial primary key,
	cidade text not null,
	nomeEstadio text not null,
	idSelecaoI smallint,
	idSelecaoII smallint,
	dataHora timestamp with time zone not null,
	unique(dataHora, idSelecaoI, idSelecaoII),
	foreign key(idSelecaoI) references Selecao(id),
	foreign key(idSelecaoII) references Selecao(id)
);

Create Table Jogador
(
	id smallserial primary key,
	nome text not null,
	altura numeric(3,2) not null,
	nCamisa smallint check (nCamisa > 0 and nCamisa < 24) not null,
	dataNasc date not null,
	posicao text check (posicao in ('Goleiro','Lateral Esquerdo', 'Lateral Direito', 'Atacante', 'Volante', 'Zagueiro')) not null,
	titular boolean default false not null,
	idSelecao smallint not null,
	unique(idSelecao,nCamisa),
	foreign key(idSelecao) references Selecao(id)
);


Create Table Ocorrencia
(
	id smallserial primary key,
	instanteTempo time not null,
	idJogo smallint not null,
	unique (instanteTempo, idJogo),
	foreign key(idJogo) references Jogo(id)
);

Create Table Gol
(
	idOc smallint primary key,
	idJogadorAutor smallint not null,
	idJogadorAssistencia smallint,
	tipo text check(tipo in('Contra', 'A Favor')) not null,
	modo text check(modo in('Falta', 'Penalti', 'Comum')) not null,
	foreign key(idOc) references Ocorrencia(id),
	foreign key(idJogadorAutor) references Jogador(id),
	foreign key(idJogadorAssistencia) references Jogador(id)
);

Create Table Cartao
(
        id smallserial primary key,
	idOc smallint not null,
	idJogador smallint not null,
        cartaoVermDerivado smallint,
	cor text check(cor in('Vermelho', 'Amarelo')) not null,
        unique(idOc, cor),
	foreign key (cartaoVermDerivado) references Cartao(id),
        foreign key (idOc) references Ocorrencia(id),
	foreign key(idJogador) references Jogador(id)
);

Create Table Falta
(
	idOc smallint primary key,
	idCartao smallint,
	idJogador smallint not null,
	tipo text check(tipo in('Penalti', 'Falta Comum')) not null,
	foreign key(idOc) references Ocorrencia(id),
	foreign key(idJogador) references Jogador(id),
	foreign key (idCartao) references Cartao(id)
);

Create Table Substituicao 
(
	idOc smallint primary key,
	idJogadorEntrou smallint not null,
	idJogadorSaiu smallint not null,
	motivo text check(motivo in ('Estrategica', 'Contusao')) not null,
	foreign key (idOc) references Ocorrencia (id), 
	foreign key(idJogadorEntrou) references Jogador(id),
	foreign key(idJogadorSaiu) references Jogador(id)
);

--Indices e justificativas
--Observação 1: o PostgreSQL gera automaticamente índices para todas as primary keys e unique keys das tabelas.
create index index_nome_jogador on jogador(nome);
--Justificativa do indice
--1) A tabela Jogador é uma tabela bastante consultada pelo nome do jogador, para o lançamento de qualquer ocorrência de jogo (gol, falta, cartão, substituição).
--2) A tabela Jogador terá um número relativamente grande de registros se comparado com outras tabelas do sistema (736 no total, 23 jogadores para cada uma das 32 seleções).
-- A tabela Seleção, por exemplo, terá apenas 32 registros, e a tabela jogo, 48 registros, tornando injustificável a criação de um índice secundário para qualquer outro campo dessas tabelas.

create index index_idJogo_ocorrencia on Ocorrencia(idJogo);
--Justificativa do indice
--Em um cenário de utilização real do sistema, a tabela de ocorrências conteria milhares de entradas, 
--muito mais que qualquer outra tabela do sistema. As consultas realizadas nessa tabela,
--utilizadas na geração dos relatórios e para a montagem da tabela do campeonato (querys complexas), utilizam sempre o idJogo como chave de pesquisa, 
--justificando assim a criação do índice para esse campo.


