package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Jogador;

public class JogadorDAO extends GeneralDAO
{
    public ArrayList<Jogador> listaTodos()
    {
        return (ArrayList<Jogador>) sessao.createQuery("from Jogador j order by j.ncamisa").list();
    }

    public ArrayList<Jogador> queryJogadorByNome(String nome)
    {
        String hql;

        hql = "from Jogador j "
                + "where lower(j.nome) like lower('%" + nome + "%') order by j.id";

        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }

    public ArrayList<Jogador> queryJogadorByPosicao(String posicao)
    {
        String hql;

        hql = "from Jogador j "
                + "where lower(j.posicao) like lower('%" + posicao + "%') order by j.id";
        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }

    public int queryQuantidadeJogadorTitularesSelecao(Short idSelecao)
    {
        String hql;
        int qtde;
        hql = "select count(id) from Jogador j where j.selecao.id = " + idSelecao + " and j.titular = true";
        qtde = Integer.parseInt(sessao.createQuery(hql).uniqueResult().toString());
        return qtde;
    }

    public int queryQuantidadeGoleirosSelecao(Short idSelecao)
    {
        String hql;
        int qtde;
        hql = "select count(id) from Jogador j where j.selecao.id = " + idSelecao + "and j.posicao = 'Goleiro'";
        qtde = Integer.parseInt(sessao.createQuery(hql).uniqueResult().toString());
        return qtde;
    }

    public String queryPosicaoJogador(Short idJogador)
    {
        String hql;
        hql = "select j.posicao from Jogador j where j.id = " + idJogador;
        String posicao = sessao.createQuery(hql).uniqueResult().toString();

        return posicao;
    }

    //jogadores em campo para um certo jogo
    public ArrayList<Jogador> queryJogadoresEmCampo(Short idJogo)
    {
        String hql;
        hql = auxQueryJogadoresEmCampo(idJogo);

        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }

    //outros jogadores, da mesma seleção, em campo, diferentes do jogador passado como parâmetro
    public ArrayList queryOutrosJogadoresEmCampoSelecao(Short idJogo, Short idSelecao, Short idJogador)
    {
        String hql;
        String queryJogadoresSairamNoJogo;
        String queryJogadoresEntraramJogo;
        String queryExpulsos;

        queryJogadoresSairamNoJogo = "(select s.jogadorByIdjogadorsaiu.id "
                + "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + " "
                + "and s.jogadorByIdjogadorsaiu.selecao.id = " + idSelecao + ")";

        queryJogadoresEntraramJogo = "(select s.jogadorByIdjogadorentrou.id "
                + "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + " "
                + "and s.jogadorByIdjogadorentrou.selecao.id = " + idSelecao + ")";

        queryExpulsos = "(select c.jogador.id "
                + "from Cartao c "
                + "where c.ocorrencia.jogo.id = " + idJogo + " "
                + "and c.cor = 'Vermelho' "
                + "and c.jogador.selecao.id = "+ idSelecao + ")";

        hql = "from Jogador jgdr "
                + "where (("
                //jogadores titulares, da seleção passada como parâmetro, que nao sairam de campo
                + "jgdr.selecao.id = " + idSelecao + " "
                + "and jgdr.titular = true and jgdr.id not in " + queryJogadoresSairamNoJogo + ") "
                //jogadores reservas, da seleção passada como parâmetro, que entraram em campo
                + "or ("
                + "jgdr.id in " + queryJogadoresEntraramJogo + ")) "
                //menos os jogadores expulsos
                + "and jgdr.id not in " + queryExpulsos + " "
                + "and jgdr.id != " + idJogador;

        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }

    public Short queryIdSelecaoJogador(Short idJogador)
    {
        String hql;

        hql = "select j.selecao.id "
                + "from Jogador j "
                + "where j.id = " + idJogador;

        return Short.parseShort(String.valueOf(sessao.createQuery(hql).uniqueResult()));
    }
    
    //reservas disponíveis, num dado jogo, para uma dada seleção
    public ArrayList<Jogador> queryJogadoresReservaMesmaSelecao(Short idJogo, Short idSelecao)
    {
        String hql;
        String queryJogadoresMesmaSelecaoEntraramCampo;

        //jogadores reservas, da mesma seleção passada como parâmetro, que entraram em campo
        queryJogadoresMesmaSelecaoEntraramCampo = "(select s.jogadorByIdjogadorentrou.id "
                + "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + " "
                + "and s.jogadorByIdjogadorentrou.selecao.id = " + idSelecao + ")";

        hql = "from Jogador jgdr "
                //reservas da seleção
                + "where jgdr.selecao.id = " + idSelecao + " and jgdr.titular = false and "
                //menos os reservas que entraram em campo
                + "jgdr.id not in " + queryJogadoresMesmaSelecaoEntraramCampo;

        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }
    
    public ArrayList<Jogador> queryJogadoresEmCampoByNome(Short idJogo, String nome)
    {
        String hql;

        hql = auxQueryJogadoresEmCampo(idJogo) + " "
                + "and lower(jgdr.nome) like lower('%" +nome+"%')";

        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }
    
    private String auxQuerySelecaoIJogo(Short idJogo)
    {
        return "(select jg.selecaoByIdselecaoi.id "
                + "from Jogo jg "
                + "where jg.id = " + idJogo + ")";
    }
    
    private String auxQuerySelecaoIIJogo(Short idJogo)
    {
        return "(select jg.selecaoByIdselecaoii.id "
                + "from Jogo jg "
                + "where jg.id = " + idJogo + ")";
    }
    
    private String auxQueryJogadoresSairamJogo(Short idJogo)
    {
        return "(select s.jogadorByIdjogadorsaiu.id "
                + "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + ")";
    }
    
    private String auxQueryJogadoresEntraramJogo(Short idJogo)
    {
        return "(select s.jogadorByIdjogadorentrou.id "
                + "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + ")";
    }
    
    private String auxQueryJogadoresExpulsosJogo(Short idJogo)
    {
        return "(select c.jogador.id "
                + "from Cartao c "
                + "where c.ocorrencia.jogo.id = " + idJogo + " and c.cor = 'Vermelho')";
    } 
    
    private String auxQueryJogadoresEmCampo(Short idJogo)
    {
        return "from Jogador jgdr "
                + "where (("
                //jogadores titulares, de uma das selecoes que disputam o jogo, que nao sairam de campo
                + "(jgdr.selecao.id = " + auxQuerySelecaoIJogo(idJogo) + " or jgdr.selecao.id = " + auxQuerySelecaoIIJogo(idJogo) + ") "
                + "and jgdr.titular = true and jgdr.id not in " + auxQueryJogadoresSairamJogo(idJogo) + ") "
                //jogadores reservas, de uma das selecoes que disputam o jogo, que entraram em campo
                + "or ("
                + "jgdr.id in " + auxQueryJogadoresEntraramJogo(idJogo) + ")) "
                //menos os jogadores expulsos
                + "and jgdr.id not in " + auxQueryJogadoresExpulsosJogo(idJogo);
    }
}
