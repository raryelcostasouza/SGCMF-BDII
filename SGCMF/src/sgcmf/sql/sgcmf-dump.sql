--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.0
-- Dumped by pg_dump version 9.2.0
-- Started on 2012-10-30 20:19:18

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 184 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 184
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 25838)
-- Name: cartao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cartao (
    idoc smallint NOT NULL,
    idjogador smallint NOT NULL,
    cor text NOT NULL,
    CONSTRAINT cartao_cor_check CHECK ((cor = ANY (ARRAY['Vermelho'::text, 'Amarelo'::text])))
);


ALTER TABLE public.cartao OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 25857)
-- Name: falta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE falta (
    idoc smallint NOT NULL,
    idcartao smallint,
    idjogador smallint NOT NULL,
    tipo text NOT NULL,
    CONSTRAINT falta_tipo_check CHECK ((tipo = ANY (ARRAY['Penalti'::text, 'Falta Comum'::text])))
);


ALTER TABLE public.falta OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 25813)
-- Name: gol; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gol (
    idoc smallint NOT NULL,
    idjogadorautor smallint NOT NULL,
    idjogadorassistencia smallint,
    tipo text NOT NULL,
    modo text NOT NULL,
    CONSTRAINT gol_modo_check CHECK ((modo = ANY (ARRAY['Falta'::text, 'Penalti'::text, 'Comum'::text]))),
    CONSTRAINT gol_tipo_check CHECK ((tipo = ANY (ARRAY['Contra'::text, 'A Favor'::text])))
);


ALTER TABLE public.gol OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 25759)
-- Name: jogador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE jogador (
    id smallint NOT NULL,
    nome text NOT NULL,
    altura numeric(3,2) NOT NULL,
    ncamisa smallint NOT NULL,
    datanasc date NOT NULL,
    posicao text NOT NULL,
    titular boolean DEFAULT false NOT NULL,
    idselecao smallint NOT NULL,
    CONSTRAINT jogador_ncamisa_check CHECK (((ncamisa > 0) AND (ncamisa < 24))),
    CONSTRAINT jogador_posicao_check CHECK ((posicao = ANY (ARRAY['Goleiro'::text, 'Lateral Esquerdo'::text, 'Lateral Direito'::text, 'Atacante'::text, 'Volante'::text, 'Zagueiro'::text])))
);


ALTER TABLE public.jogador OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 25757)
-- Name: jogador_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE jogador_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.jogador_id_seq OWNER TO postgres;

--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 174
-- Name: jogador_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE jogador_id_seq OWNED BY jogador.id;


--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 174
-- Name: jogador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('jogador_id_seq', 1, false);


--
-- TOC entry 173 (class 1259 OID 25734)
-- Name: jogo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE jogo (
    id smallint NOT NULL,
    tipo text NOT NULL,
    cidade text NOT NULL,
    nomeestadio text NOT NULL,
    idselecaoi smallint,
    idselecaoii smallint,
    prorrogacao boolean DEFAULT false NOT NULL,
    datahora timestamp with time zone NOT NULL,
    CONSTRAINT jogo_tipo_check CHECK ((tipo = ANY (ARRAY['Primeira Fase'::text, 'Oitavas de Final'::text, 'Quartas de final'::text, 'Semi-final'::text, 'Terceiro Lugar'::text, 'Final'::text])))
);


ALTER TABLE public.jogo OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 25732)
-- Name: jogo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE jogo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.jogo_id_seq OWNER TO postgres;

--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 172
-- Name: jogo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE jogo_id_seq OWNED BY jogo.id;


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 172
-- Name: jogo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('jogo_id_seq', 1, false);


--
-- TOC entry 179 (class 1259 OID 25800)
-- Name: ocorrencia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ocorrencia (
    id smallint NOT NULL,
    instantetempo time without time zone NOT NULL,
    idjogo smallint NOT NULL
);


ALTER TABLE public.ocorrencia OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 25798)
-- Name: ocorrencia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ocorrencia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ocorrencia_id_seq OWNER TO postgres;

--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 178
-- Name: ocorrencia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ocorrencia_id_seq OWNED BY ocorrencia.id;


--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 178
-- Name: ocorrencia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ocorrencia_id_seq', 1, false);


--
-- TOC entry 177 (class 1259 OID 25780)
-- Name: rodadadepenaltis; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE rodadadepenaltis (
    id smallint NOT NULL,
    idjogo smallint NOT NULL,
    nrodada smallint NOT NULL,
    idjogadorcobrancai smallint NOT NULL,
    resultadocobrancai boolean NOT NULL,
    idjogadorcobrancaii smallint NOT NULL,
    resultadocobrancaii boolean NOT NULL
);


ALTER TABLE public.rodadadepenaltis OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 25778)
-- Name: rodadadepenaltis_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE rodadadepenaltis_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rodadadepenaltis_id_seq OWNER TO postgres;

--
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 176
-- Name: rodadadepenaltis_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE rodadadepenaltis_id_seq OWNED BY rodadadepenaltis.id;


--
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 176
-- Name: rodadadepenaltis_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rodadadepenaltis_id_seq', 1, false);


--
-- TOC entry 171 (class 1259 OID 25716)
-- Name: selecao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE selecao (
    id smallint NOT NULL,
    pais text NOT NULL,
    caminhoimgbandeira text NOT NULL,
    idusuariotecnico smallint NOT NULL
);


ALTER TABLE public.selecao OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 25714)
-- Name: selecao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE selecao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.selecao_id_seq OWNER TO postgres;

--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 170
-- Name: selecao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE selecao_id_seq OWNED BY selecao.id;


--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 170
-- Name: selecao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('selecao_id_seq', 1, false);


--
-- TOC entry 183 (class 1259 OID 25881)
-- Name: substituicao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE substituicao (
    idoc smallint NOT NULL,
    idjogadorentrou smallint NOT NULL,
    idjogadorsaiu smallint NOT NULL,
    motivo text NOT NULL,
    CONSTRAINT substituicao_motivo_check CHECK ((motivo = ANY (ARRAY['Estrategica'::text, 'Contusao'::text])))
);


ALTER TABLE public.substituicao OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 25698)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    id smallint NOT NULL,
    nome text NOT NULL,
    cpf character(14) NOT NULL,
    email text NOT NULL,
    perfil text NOT NULL,
    login text NOT NULL,
    senha text NOT NULL,
    CONSTRAINT usuario_perfil_check CHECK ((perfil = ANY (ARRAY['Administrador'::text, 'Tecnico da Selecao'::text, 'Membro Comite'::text, 'Entusiasta'::text])))
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 25696)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 168
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;


--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 168
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_seq', 1, false);


--
-- TOC entry 1976 (class 2604 OID 25762)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jogador ALTER COLUMN id SET DEFAULT nextval('jogador_id_seq'::regclass);


--
-- TOC entry 1973 (class 2604 OID 25737)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jogo ALTER COLUMN id SET DEFAULT nextval('jogo_id_seq'::regclass);


--
-- TOC entry 1981 (class 2604 OID 25803)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ocorrencia ALTER COLUMN id SET DEFAULT nextval('ocorrencia_id_seq'::regclass);


--
-- TOC entry 1980 (class 2604 OID 25783)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rodadadepenaltis ALTER COLUMN id SET DEFAULT nextval('rodadadepenaltis_id_seq'::regclass);


--
-- TOC entry 1972 (class 2604 OID 25719)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY selecao ALTER COLUMN id SET DEFAULT nextval('selecao_id_seq'::regclass);


--
-- TOC entry 1970 (class 2604 OID 25701)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq'::regclass);


--
-- TOC entry 2048 (class 0 OID 25838)
-- Dependencies: 181
-- Data for Name: cartao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cartao (idoc, idjogador, cor) FROM stdin;
11	1	Amarelo
12	2	Amarelo
13	3	Amarelo
14	4	Amarelo
15	5	Amarelo
16	6	Amarelo
17	7	Amarelo
18	15	Amarelo
19	16	Amarelo
20	17	Amarelo
\.


--
-- TOC entry 2049 (class 0 OID 25857)
-- Dependencies: 182
-- Data for Name: falta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY falta (idoc, idcartao, idjogador, tipo) FROM stdin;
23	13	6	Falta Comum
24	\N	7	Falta Comum
25	\N	3	Falta Comum
26	15	14	Falta Comum
27	\N	15	Falta Comum
28	\N	16	Falta Comum
29	17	17	Falta Comum
30	20	13	Falta Comum
\.


--
-- TOC entry 2047 (class 0 OID 25813)
-- Dependencies: 180
-- Data for Name: gol; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY gol (idoc, idjogadorautor, idjogadorassistencia, tipo, modo) FROM stdin;
1	4	\N	A Favor	Comum
2	5	\N	A Favor	Comum
3	6	\N	A Favor	Comum
4	7	\N	A Favor	Comum
5	7	\N	A Favor	Comum
6	14	\N	A Favor	Comum
7	15	\N	A Favor	Comum
8	16	\N	A Favor	Comum
9	17	\N	A Favor	Comum
10	17	\N	A Favor	Comum
\.


--
-- TOC entry 2044 (class 0 OID 25759)
-- Dependencies: 175
-- Data for Name: jogador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY jogador (id, nome, altura, ncamisa, datanasc, posicao, titular, idselecao) FROM stdin;
1	Jogador 1	1.93	1	1990-10-10	Goleiro	t	1
2	Jogador 2	1.93	2	1990-10-10	Zagueiro	t	1
3	Jogador 3	1.93	3	1990-10-10	Zagueiro	t	1
4	Jogador 4	1.93	4	1990-10-10	Zagueiro	t	1
5	Jogador 5	1.93	5	1990-10-10	Lateral Esquerdo	t	1
6	Jogador 6	1.93	6	1990-10-10	Lateral Direito	t	1
7	Jogador 7	1.93	7	1990-10-10	Atacante	t	1
8	Jogador 8	1.93	15	1990-10-10	Volante	f	1
9	Jogador 9	1.93	16	1990-10-10	Volante	f	1
10	Jogador 10	1.93	17	1990-10-10	Zagueiro	f	1
11	Jogador 11	1.93	1	1990-10-10	Goleiro	t	2
12	Jogador 12	1.93	2	1990-10-10	Zagueiro	t	2
13	Jogador 13	1.93	3	1990-10-10	Zagueiro	t	2
14	Jogador 14	1.93	4	1990-10-10	Lateral Esquerdo	t	2
15	Jogador 15	1.93	5	1990-10-10	Lateral Direito	t	2
16	Jogador 16	1.93	6	1990-10-10	Volante	t	2
17	Jogador 17	1.93	7	1990-10-10	Atacante	t	2
18	Jogador 18	1.93	11	1990-10-10	Atacante	f	2
19	Jogador 19	1.93	18	1990-10-10	Zagueiro	f	2
20	Jogador 20	1.93	21	1990-10-10	Goleiro	f	2
21	Jogador 21	1.93	19	1990-10-10	Goleiro	f	2
22	Jogador 22	1.93	13	1990-10-10	Atacante	f	2
23	Jogador 23	1.93	14	1990-10-10	Zagueiro	f	2
24	Jogador 24	1.93	15	1990-10-10	Lateral Direito	f	2
\.


--
-- TOC entry 2043 (class 0 OID 25734)
-- Dependencies: 173
-- Data for Name: jogo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY jogo (id, tipo, cidade, nomeestadio, idselecaoi, idselecaoii, prorrogacao, datahora) FROM stdin;
1	Primeira Fase	Rio de Janeiro	Maracana	1	2	f	2012-08-10 05:20:00-03
2	Oitavas de Final	Sao Paulo	Morumbi	3	4	f	2012-08-11 06:20:00-03
3	Oitavas de Final	Belo Horizonte	Mineirao	5	6	f	2012-08-12 08:20:00-03
4	Quartas de final	Rio de Janeiro	Maracana	7	8	f	2012-08-13 09:20:00-03
5	Quartas de final	Sao Paulo	Morumbi	9	10	f	2012-08-14 05:20:00-03
6	Semi-final	Belo Horizonte	Mineirao	11	12	f	2012-08-15 11:20:00-03
7	Terceiro Lugar	Rio de Janeiro	Maracana	13	14	f	2012-08-16 11:20:00-03
8	Final	Sao Paulo	Morumbi	5	2	f	2012-08-17 08:20:00-03
9	Primeira Fase	Belo Horizonte	Mineirao	3	5	f	2012-08-18 06:20:00-03
10	Primeira Fase	Rio de Janeiro	Maracana	1	6	f	2012-08-19 07:20:00-03
\.


--
-- TOC entry 2046 (class 0 OID 25800)
-- Dependencies: 179
-- Data for Name: ocorrencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ocorrencia (id, instantetempo, idjogo) FROM stdin;
1	10:00:00	1
2	10:10:00	1
3	10:20:00	1
4	10:30:00	1
5	10:40:00	1
6	10:50:00	1
7	11:00:00	1
8	11:10:00	1
9	11:20:00	1
10	11:30:00	1
11	11:40:00	1
12	11:50:00	1
13	12:10:00	1
14	12:20:00	1
15	12:30:00	1
16	12:40:00	1
17	12:50:00	1
18	13:10:00	1
19	13:20:00	1
20	13:30:00	1
23	14:00:00	1
24	14:20:00	1
25	14:30:00	1
26	14:40:00	1
27	14:50:00	1
28	15:10:00	1
29	15:20:00	1
30	15:30:00	1
31	15:40:00	1
32	15:50:00	1
33	16:10:00	1
34	16:20:00	1
35	16:30:00	1
36	16:40:00	1
37	16:50:00	10
38	17:10:00	10
39	17:20:00	10
40	17:30:00	8
41	17:30:00	1
42	17:40:00	1
43	17:50:00	1
44	18:10:00	1
45	18:20:00	1
46	18:30:00	1
47	18:40:00	1
48	18:50:00	1
49	19:10:00	1
50	19:20:00	1
\.


--
-- TOC entry 2045 (class 0 OID 25780)
-- Dependencies: 177
-- Data for Name: rodadadepenaltis; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY rodadadepenaltis (id, idjogo, nrodada, idjogadorcobrancai, resultadocobrancai, idjogadorcobrancaii, resultadocobrancaii) FROM stdin;
\.


--
-- TOC entry 2042 (class 0 OID 25716)
-- Dependencies: 171
-- Data for Name: selecao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY selecao (id, pais, caminhoimgbandeira, idusuariotecnico) FROM stdin;
1	Espanha	\\img\\Espanha.png	3
2	Alemanha	\\img\\Alemanha.png	4
3	Inglaterra	\\img\\Inglaterra.png	5
4	Portugal	\\img\\Portugal.png	6
5	Uruguai	\\img\\Uruguai.png	7
6	Italia	\\img\\Italia.png	8
7	Argentina	\\img\\Argentina.png	9
8	Holanda	\\img\\Holanda.png	10
9	Croacia	\\img\\Croacia.png	11
10	Dinamarca	\\img\\Dinamarca.png	12
11	Grecia	\\img\\Grecia.png	13
12	Brasil	\\img\\Brasil.png	14
13	Russia	\\img\\Russia.png	15
14	Chile	\\img\\Chile.png	16
15	Franca	\\img\\Franca.png	17
\.


--
-- TOC entry 2050 (class 0 OID 25881)
-- Dependencies: 183
-- Data for Name: substituicao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY substituicao (idoc, idjogadorentrou, idjogadorsaiu, motivo) FROM stdin;
31	8	1	Estrategica
32	9	2	Estrategica
33	10	3	Contusao
34	18	11	Estrategica
35	19	12	Estrategica
36	20	13	Contusao
37	1	6	Estrategica
38	9	7	Estrategica
39	10	5	Estrategica
40	24	13	Contusao
\.


--
-- TOC entry 2041 (class 0 OID 25698)
-- Dependencies: 169
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (id, nome, cpf, email, perfil, login, senha) FROM stdin;
1	Usuario 1	123.456.789-10	admin@sgcmf.com	Administrador	admin	admin
2	Usuario 2	213.456.789-20	usuario2@sgcmf.com	Administrador	user2	user2
3	Usuario 3	321.456.789-30	usuario3@sgcmf.com	Tecnico da Selecao	user3	user3
4	Usuario 4	423.156.789-40	usuario4@sgcmf.com	Tecnico da Selecao	user4	user4
5	Usuario 5	523.416.789-50	usuario5@sgcmf.com	Tecnico da Selecao	user5	user5
6	Usuario 6	623.451.789-60	usuario6@sgcmf.com	Tecnico da Selecao	user6	user6
7	Usuario 7	723.456.189-70	usuario7@sgcmf.com	Tecnico da Selecao	user7	user7
8	Usuario 8	823.456.189-80	usuario8@sgcmf.com	Tecnico da Selecao	user8	user8
9	Usuario 9	923.456.781-90	usuario9@sgcmf.com	Tecnico da Selecao	user9	user9
10	Usuario 10	231.456.789.10	usuario10@sgcmf.com	Tecnico da Selecao	user10	user10
11	Usuario 11	132.456.789-20	usuario11@sgcmf.com	Tecnico da Selecao	user11	user11
12	Usuario 12	134.256.789-30	usuario12@sgcmf.com	Tecnico da Selecao	user12	user12
13	Usuario 13	671.416.789-40	usuario13@sgcmf.com	Tecnico da Selecao	user13	user13
14	Usuario 14	387.451.789-50	usuario14@sgcmf.com	Tecnico da Selecao	user14	user14
15	Usuario 15	413.456.189-60	usuario15@sgcmf.com	Tecnico da Selecao	user15	user15
16	Usuario 16	911.456.189-70	usuario16@sgcmf.com	Tecnico da Selecao	user16	user16
17	Usuario 17	785.456.781-80	usuario17@sgcmf.com	Tecnico da Selecao	user17	user17
18	Usuario 18	785.456.781-90	usuario18@sgcmf.com	Membro Comite	user18	user18
19	Usuario 19	785.456.781-10	usuario19@sgcmf.com	Membro Comite	user19	user19
20	Usuario 20	785.456.781-20	usuario20@sgcmf.com	Entusiasta	user20	user20
\.


--
-- TOC entry 2018 (class 2606 OID 25846)
-- Name: cartao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT cartao_pkey PRIMARY KEY (idoc);


--
-- TOC entry 2020 (class 2606 OID 25865)
-- Name: falta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY falta
    ADD CONSTRAINT falta_pkey PRIMARY KEY (idoc);


--
-- TOC entry 2016 (class 2606 OID 25822)
-- Name: gol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gol
    ADD CONSTRAINT gol_pkey PRIMARY KEY (idoc);


--
-- TOC entry 2004 (class 2606 OID 25772)
-- Name: jogador_idselecao_ncamisa_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY jogador
    ADD CONSTRAINT jogador_idselecao_ncamisa_key UNIQUE (idselecao, ncamisa);


--
-- TOC entry 2006 (class 2606 OID 25770)
-- Name: jogador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY jogador
    ADD CONSTRAINT jogador_pkey PRIMARY KEY (id);


--
-- TOC entry 2000 (class 2606 OID 25746)
-- Name: jogo_datahora_idselecaoi_idselecaoii_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY jogo
    ADD CONSTRAINT jogo_datahora_idselecaoi_idselecaoii_key UNIQUE (datahora, idselecaoi, idselecaoii);


--
-- TOC entry 2002 (class 2606 OID 25744)
-- Name: jogo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY jogo
    ADD CONSTRAINT jogo_pkey PRIMARY KEY (id);


--
-- TOC entry 2012 (class 2606 OID 25807)
-- Name: ocorrencia_instantetempo_idjogo_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ocorrencia
    ADD CONSTRAINT ocorrencia_instantetempo_idjogo_key UNIQUE (instantetempo, idjogo);


--
-- TOC entry 2014 (class 2606 OID 25805)
-- Name: ocorrencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ocorrencia
    ADD CONSTRAINT ocorrencia_pkey PRIMARY KEY (id);


--
-- TOC entry 2008 (class 2606 OID 25787)
-- Name: rodadadepenaltis_idjogo_nrodada_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rodadadepenaltis
    ADD CONSTRAINT rodadadepenaltis_idjogo_nrodada_key UNIQUE (idjogo, nrodada);


--
-- TOC entry 2010 (class 2606 OID 25785)
-- Name: rodadadepenaltis_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rodadadepenaltis
    ADD CONSTRAINT rodadadepenaltis_pkey PRIMARY KEY (id);


--
-- TOC entry 1996 (class 2606 OID 25726)
-- Name: selecao_pais_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY selecao
    ADD CONSTRAINT selecao_pais_key UNIQUE (pais);


--
-- TOC entry 1998 (class 2606 OID 25724)
-- Name: selecao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY selecao
    ADD CONSTRAINT selecao_pkey PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 25889)
-- Name: substituicao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY substituicao
    ADD CONSTRAINT substituicao_pkey PRIMARY KEY (idoc);


--
-- TOC entry 1988 (class 2606 OID 25709)
-- Name: usuario_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_cpf_key UNIQUE (cpf);


--
-- TOC entry 1990 (class 2606 OID 25711)
-- Name: usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- TOC entry 1992 (class 2606 OID 25713)
-- Name: usuario_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_login_key UNIQUE (login);


--
-- TOC entry 1994 (class 2606 OID 25707)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2033 (class 2606 OID 25852)
-- Name: cartao_idjogador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT cartao_idjogador_fkey FOREIGN KEY (idjogador) REFERENCES jogador(id);


--
-- TOC entry 2034 (class 2606 OID 25847)
-- Name: cartao_idoc_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT cartao_idoc_fkey FOREIGN KEY (idoc) REFERENCES ocorrencia(id);


--
-- TOC entry 2037 (class 2606 OID 25876)
-- Name: falta_idcartao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY falta
    ADD CONSTRAINT falta_idcartao_fkey FOREIGN KEY (idcartao) REFERENCES cartao(idoc) ON DELETE CASCADE;


--
-- TOC entry 2036 (class 2606 OID 25871)
-- Name: falta_idjogador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY falta
    ADD CONSTRAINT falta_idjogador_fkey FOREIGN KEY (idjogador) REFERENCES jogador(id);


--
-- TOC entry 2035 (class 2606 OID 25866)
-- Name: falta_idoc_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY falta
    ADD CONSTRAINT falta_idoc_fkey FOREIGN KEY (idoc) REFERENCES ocorrencia(id);


--
-- TOC entry 2032 (class 2606 OID 25833)
-- Name: gol_idjogadorassistencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gol
    ADD CONSTRAINT gol_idjogadorassistencia_fkey FOREIGN KEY (idjogadorassistencia) REFERENCES jogador(id);


--
-- TOC entry 2031 (class 2606 OID 25828)
-- Name: gol_idjogadorautor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gol
    ADD CONSTRAINT gol_idjogadorautor_fkey FOREIGN KEY (idjogadorautor) REFERENCES jogador(id);


--
-- TOC entry 2030 (class 2606 OID 25823)
-- Name: gol_idoc_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gol
    ADD CONSTRAINT gol_idoc_fkey FOREIGN KEY (idoc) REFERENCES ocorrencia(id);


--
-- TOC entry 2026 (class 2606 OID 25773)
-- Name: jogador_idselecao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jogador
    ADD CONSTRAINT jogador_idselecao_fkey FOREIGN KEY (idselecao) REFERENCES selecao(id);


--
-- TOC entry 2024 (class 2606 OID 25747)
-- Name: jogo_idselecaoi_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jogo
    ADD CONSTRAINT jogo_idselecaoi_fkey FOREIGN KEY (idselecaoi) REFERENCES selecao(id);


--
-- TOC entry 2025 (class 2606 OID 25752)
-- Name: jogo_idselecaoii_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jogo
    ADD CONSTRAINT jogo_idselecaoii_fkey FOREIGN KEY (idselecaoii) REFERENCES selecao(id);


--
-- TOC entry 2029 (class 2606 OID 25808)
-- Name: ocorrencia_idjogo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ocorrencia
    ADD CONSTRAINT ocorrencia_idjogo_fkey FOREIGN KEY (idjogo) REFERENCES jogo(id);


--
-- TOC entry 2027 (class 2606 OID 25788)
-- Name: rodadadepenaltis_idjogadorcobrancai_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rodadadepenaltis
    ADD CONSTRAINT rodadadepenaltis_idjogadorcobrancai_fkey FOREIGN KEY (idjogadorcobrancai) REFERENCES jogador(id);


--
-- TOC entry 2028 (class 2606 OID 25793)
-- Name: rodadadepenaltis_idjogadorcobrancaii_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rodadadepenaltis
    ADD CONSTRAINT rodadadepenaltis_idjogadorcobrancaii_fkey FOREIGN KEY (idjogadorcobrancaii) REFERENCES jogador(id);


--
-- TOC entry 2023 (class 2606 OID 25727)
-- Name: selecao_idusuariotecnico_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY selecao
    ADD CONSTRAINT selecao_idusuariotecnico_fkey FOREIGN KEY (idusuariotecnico) REFERENCES usuario(id);


--
-- TOC entry 2039 (class 2606 OID 25895)
-- Name: substituicao_idjogadorentrou_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY substituicao
    ADD CONSTRAINT substituicao_idjogadorentrou_fkey FOREIGN KEY (idjogadorentrou) REFERENCES jogador(id);


--
-- TOC entry 2040 (class 2606 OID 25900)
-- Name: substituicao_idjogadorsaiu_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY substituicao
    ADD CONSTRAINT substituicao_idjogadorsaiu_fkey FOREIGN KEY (idjogadorsaiu) REFERENCES jogador(id);


--
-- TOC entry 2038 (class 2606 OID 25890)
-- Name: substituicao_idoc_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY substituicao
    ADD CONSTRAINT substituicao_idoc_fkey FOREIGN KEY (idoc) REFERENCES ocorrencia(id);


--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 181
-- Name: cartao; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE cartao FROM PUBLIC;
REVOKE ALL ON TABLE cartao FROM postgres;
GRANT ALL ON TABLE cartao TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE cartao TO javaappsgcmf;


--
-- TOC entry 2060 (class 0 OID 0)
-- Dependencies: 182
-- Name: falta; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE falta FROM PUBLIC;
REVOKE ALL ON TABLE falta FROM postgres;
GRANT ALL ON TABLE falta TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE falta TO javaappsgcmf;


--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 180
-- Name: gol; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE gol FROM PUBLIC;
REVOKE ALL ON TABLE gol FROM postgres;
GRANT ALL ON TABLE gol TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE gol TO javaappsgcmf;


--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 175
-- Name: jogador; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE jogador FROM PUBLIC;
REVOKE ALL ON TABLE jogador FROM postgres;
GRANT ALL ON TABLE jogador TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE jogador TO javaappsgcmf;


--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 174
-- Name: jogador_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE jogador_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE jogador_id_seq FROM postgres;
GRANT ALL ON SEQUENCE jogador_id_seq TO postgres;
GRANT SELECT,UPDATE ON SEQUENCE jogador_id_seq TO javaappsgcmf;


--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 173
-- Name: jogo; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE jogo FROM PUBLIC;
REVOKE ALL ON TABLE jogo FROM postgres;
GRANT ALL ON TABLE jogo TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE jogo TO javaappsgcmf;


--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 172
-- Name: jogo_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE jogo_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE jogo_id_seq FROM postgres;
GRANT ALL ON SEQUENCE jogo_id_seq TO postgres;
GRANT SELECT,UPDATE ON SEQUENCE jogo_id_seq TO javaappsgcmf;


--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 179
-- Name: ocorrencia; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE ocorrencia FROM PUBLIC;
REVOKE ALL ON TABLE ocorrencia FROM postgres;
GRANT ALL ON TABLE ocorrencia TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE ocorrencia TO javaappsgcmf;


--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 178
-- Name: ocorrencia_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE ocorrencia_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE ocorrencia_id_seq FROM postgres;
GRANT ALL ON SEQUENCE ocorrencia_id_seq TO postgres;
GRANT SELECT,UPDATE ON SEQUENCE ocorrencia_id_seq TO javaappsgcmf;


--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 177
-- Name: rodadadepenaltis; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE rodadadepenaltis FROM PUBLIC;
REVOKE ALL ON TABLE rodadadepenaltis FROM postgres;
GRANT ALL ON TABLE rodadadepenaltis TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE rodadadepenaltis TO javaappsgcmf;


--
-- TOC entry 2077 (class 0 OID 0)
-- Dependencies: 176
-- Name: rodadadepenaltis_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE rodadadepenaltis_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE rodadadepenaltis_id_seq FROM postgres;
GRANT ALL ON SEQUENCE rodadadepenaltis_id_seq TO postgres;
GRANT SELECT,UPDATE ON SEQUENCE rodadadepenaltis_id_seq TO javaappsgcmf;


--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 171
-- Name: selecao; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE selecao FROM PUBLIC;
REVOKE ALL ON TABLE selecao FROM postgres;
GRANT ALL ON TABLE selecao TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE selecao TO javaappsgcmf;


--
-- TOC entry 2081 (class 0 OID 0)
-- Dependencies: 170
-- Name: selecao_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE selecao_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE selecao_id_seq FROM postgres;
GRANT ALL ON SEQUENCE selecao_id_seq TO postgres;
GRANT SELECT,UPDATE ON SEQUENCE selecao_id_seq TO javaappsgcmf;


--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 183
-- Name: substituicao; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE substituicao FROM PUBLIC;
REVOKE ALL ON TABLE substituicao FROM postgres;
GRANT ALL ON TABLE substituicao TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE substituicao TO javaappsgcmf;


--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 169
-- Name: usuario; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE usuario FROM PUBLIC;
REVOKE ALL ON TABLE usuario FROM postgres;
GRANT ALL ON TABLE usuario TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE usuario TO javaappsgcmf;


--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 168
-- Name: usuario_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE usuario_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE usuario_id_seq FROM postgres;
GRANT ALL ON SEQUENCE usuario_id_seq TO postgres;
GRANT SELECT,UPDATE ON SEQUENCE usuario_id_seq TO javaappsgcmf;


-- Completed on 2012-10-30 20:19:19

--
-- PostgreSQL database dump complete
--

