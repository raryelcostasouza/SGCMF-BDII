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

    public ArrayList<Jogador> queryJogadoresEmCampo(Short idJogo)
    {
        String hql;
        String querySelecaoIJogo;
        String querySelecaoIIJogo;
        String queryJogadoresSairamNoJogo;
        String queryJogadoresEntraramJogo;

        querySelecaoIJogo = "(select jg.selecaoByIdselecaoi.id "
                + "from Jogo jg "
                + "where jg.id = " + idJogo + ")";

        querySelecaoIIJogo = "(select jg.selecaoByIdselecaoii.id "
                + "from Jogo jg "
                + "where jg.id = " + idJogo + ")";

        queryJogadoresSairamNoJogo = "(select s.jogadorByIdjogadorsaiu.id "
                + "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + ")";

        queryJogadoresEntraramJogo = "(select s.jogadorByIdjogadorentrou.id "
                + "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + ")";

        hql = "from Jogador jgdr "
                + "where ("
                //jogadores titulares, de uma das selecoes que disputam o jogo, que nao sairam de campo
                + "(jgdr.selecao.id = " + querySelecaoIJogo + " or jgdr.selecao.id = " + querySelecaoIIJogo + ") "
                + "and jgdr.titular = true and jgdr.id not in " + queryJogadoresSairamNoJogo + ") "
                //jogadores reservas, de uma das selecoes que disputam o jogo, que entraram em campo
                + "or ("
                + "jgdr.id in " + queryJogadoresEntraramJogo + ")";

        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }

    public ArrayList queryJogadoresEmCampoMesmaSelecao(Short idJogo, Short idSelecao)
    {
        String hql;
        String queryJogadoresSairamNoJogo;
        String queryJogadoresEntraramJogo;

        queryJogadoresSairamNoJogo = "(select s.jogadorByIdjogadorsaiu.id "
                + "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + " "
                + "and s.jogadorByIdjogadorsaiu.selecao.id = " + idSelecao + ")";

        queryJogadoresEntraramJogo = "(select s.jogadorByIdjogadorentrou.id "
                + "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + " "
                + "and s.jogadorByIdjogadorentrou.selecao.id = " + idSelecao + ")";

        hql = "from Jogador jgdr "
                + "where ("
                //jogadores titulares, de uma das selecoes que disputam o jogo, que nao sairam de campo
                + "jgdr.selecao.id = " + idSelecao + " "
                + "and jgdr.titular = true and jgdr.id not in " + queryJogadoresSairamNoJogo + ") "
                //jogadores reservas, de uma das selecoes que disputam o jogo, que entraram em campo
                + "or ("
                + "jgdr.id in " + queryJogadoresEntraramJogo + ")";

        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }
    
    public Short queryIdSelecaoJogador(Short idJogador)
    {
        String hql;
        
        hql = "select j.selecao.id "
                + "from Jogador j "
                + "where j.id = " +idJogador;
        
        return Short.parseShort(String.valueOf(sessao.createQuery(hql).uniqueResult()));
    }
}
