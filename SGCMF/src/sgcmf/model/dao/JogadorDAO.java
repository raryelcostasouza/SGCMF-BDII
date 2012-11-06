package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Jogador;
import sgcmf.model.hibernate.Selecao;

public class JogadorDAO extends GeneralDAO
{
    public ArrayList<Jogador> queryJogadorByNome(String nome)
    {
        String hql;

        hql = "from Jogador j "
                + "where lower(j.nome) like lower('%" + nome + "%')";

        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }

    public ArrayList<Jogador> queryJogadorByPosicao(String posicao)
    {
        String hql;

        hql = "from Jogador j "
                + "where lower(j.posicao) like lower('%" + posicao + "%')";
        return (ArrayList<Jogador>) sessao.createQuery(hql).list();
    }

    public ArrayList queryQuantidadeJogadorTitularesSelecao(Short idSelecao)
    {
        String hql;
        hql = "select count(id) from Jogador j where j.selecao.id = " + idSelecao + "and j.titular = true";

        return (ArrayList) sessao.createQuery(hql).list();
    }

    public ArrayList queryQuantidadeGoleirosSelecao(Short idSelecao)
    {
        String hql;
        hql = "select count(id) from Jogador j where j.selecao.id = " + idSelecao + "and j.posicao = 'Goleiro'";
        return (ArrayList) sessao.createQuery(hql).list();
    }
}
