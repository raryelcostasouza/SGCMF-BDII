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
}
