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
	foreign key (idUsuarioTecnico) references Usuario(id)
);

Create Table Jogo
(
	id smallserial primary key,
	tipo text check(tipo in('Primeira Fase', 'Oitavas de Final', 'Quartas de final', 'Semi-final', 'Terceiro Lugar', 'Final')) not null,
	cidade text not null,
	nomeEstadio text not null,
	idSelecaoI smallint,
	idSelecaoII smallint,
	prorrogacao boolean default false not null,
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

Create Table RodadaDePenaltis
(
	id smallserial primary key,
	idJogo smallint not null,
	nRodada smallint not null,
	idJogadorCobrancaI smallint not null,
	resultadoCobrancaI boolean not null,
	idJogadorCobrancaII smallint not null,
	resultadoCobrancaII boolean not null,
	unique (idJogo, nRodada),
	foreign key(idJogadorCobrancaI) references Jogador(id),
	foreign key(idJogadorCobrancaII) references Jogador(id)
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