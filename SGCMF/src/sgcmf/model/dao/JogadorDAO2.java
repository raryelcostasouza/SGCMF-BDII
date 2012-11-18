package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.hibernate.Jogador;

public class JogadorDAO2
{
    private static JogadorDAO2 instance;
    
    public static JogadorDAO2 getInstance()
    {
        if (instance == null)
        {
            instance = new JogadorDAO2();
        }
        return instance;
    }
    
    public Jogador carregar(Jogador entidade, Serializable id)
    {
        SGCMFSessionManager.getCurrentSession().load(entidade, id);
        return entidade;
    }
    
    public ArrayList<Jogador> listaTodosBySelecao(Short idSelecao)
    {
        return (ArrayList<Jogador>) SGCMFSessionManager.getCurrentSession().createQuery("from Jogador j where j.selecao.id = " + idSelecao + " order by j.ncamisa").list();
    }

    public ArrayList<Jogador> queryJogadorByNome(String nome)
    {
        String hql;

        hql = "from Jogador j "
                + "where lower(j.nome) like lower('%" + nome + "%') order by j.id";

        return (ArrayList<Jogador>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Jogador> queryJogadorByPosicao(String posicao)
    {
        String hql;

        hql = "from Jogador j "
                + "where lower(j.posicao) like lower('%" + posicao + "%') order by j.id";
        return (ArrayList<Jogador>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public int queryQuantidadeJogadorTitularesSelecao(Short idSelecao)
    {
        String hql;
        int qtde;
        hql = "select count(id) from Jogador j where j.selecao.id = " + idSelecao + " and j.titular = true";
        qtde = Integer.parseInt(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult().toString());
        return qtde;
    }

    public int queryQuantidadeGoleirosSelecao(Short idSelecao)
    {
        String hql;
        int qtde;
        hql = "select count(id) from Jogador j where j.selecao.id = " + idSelecao + "and j.posicao = 'Goleiro'";
        qtde = Integer.parseInt(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult().toString());
        return qtde;
    }

    public String queryPosicaoJogador(Short idJogador)
    {
        String hql;
        hql = "select j.posicao from Jogador j where j.id = " + idJogador;
        String posicao = SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult().toString();

        return posicao;
    }

    public Short queryIdSelecaoJogador(Short idJogador)
    {
        String hql;

        hql = "select j.selecao.id "
                + "from Jogador j "
                + "where j.id = " + idJogador;

        return Short.parseShort(String.valueOf(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult()));
    }

    public ArrayList<Jogador> queryJogadoresEmCampo(Short idJogo)
    {
        String hql;
        hql = auxQueryJogadoresEmCampo(idJogo);

        return (ArrayList<Jogador>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Jogador> queryJogadoresEmCampoByNome(Short idJogo, String nome)
    {
        String hql;

        hql = auxQueryJogadoresEmCampo(idJogo) + " "
                + "and lower(jgdr.nome) like lower('%" + nome + "%')";

        return (ArrayList<Jogador>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList queryOutrosJogadoresEmCampoSelecao(Short idJogo, Short idSelecao, Short idJogador)
    {
        String hql;

        hql = auxQueryOutrosJogadoresEmCampoSelecao(idJogo, idSelecao, idJogador);

        return (ArrayList<Jogador>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList queryOutrosJogadoresEmCampoSelecaoByNome(Short idJogo, Short idSelecao, Short idJogador, String nome)
    {
        String hql;

        hql = auxQueryOutrosJogadoresEmCampoSelecao(idJogo, idSelecao, idJogador) + " "
                + "and lower(jgdr.nome) like lower('%" + nome + "%')";

        return (ArrayList<Jogador>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Jogador> queryReservasSelecao(Short idJogo, Short idSelecao)
    {
        String hql;
        hql = auxQueryJogadoresReservaSelecao(idJogo, idSelecao);

        return (ArrayList<Jogador>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Jogador> queryReservasSelecaoByNome(Short idJogo, Short idSelecao, String nome)
    {
        String hql;

        hql = auxQueryJogadoresReservaSelecao(idJogo, idSelecao) + " "
                + "and lower(jgdr.nome) like lower('%" + nome + "%')";

        return (ArrayList<Jogador>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
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

    private String auxQueryJogadoresEntraramJogoSelecao(Short idJogo, Short idSelecao)
    {
        String querySemParentesesNoFinal;
        String queryBase = auxQueryJogadoresEntraramJogo(idJogo);

        querySemParentesesNoFinal = queryBase.substring(0, queryBase.length() - 1);
        return querySemParentesesNoFinal + " "
                + "and s.jogadorByIdjogadorentrou.selecao.id = " + idSelecao + ")";
    }

    private String auxQueryJogadoresExpulsosJogo(Short idJogo)
    {
        return "(select c.jogador.id "
                + "from Cartao c "
                + "where c.ocorrencia.jogo.id = " + idJogo + " and c.cor = 'Vermelho')";
    }

    //jogadores em campo para um certo jogo
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

    //outros jogadores, da mesma seleção, em campo, diferentes do jogador passado como parâmetro
    private String auxQueryOutrosJogadoresEmCampoSelecao(Short idJogo, Short idSelecao, Short idJogador)
    {
        return "from Jogador jgdr "
                + "where (("
                //jogadores titulares, da seleção passada como parâmetro, que nao sairam de campo
                + "jgdr.selecao.id = " + idSelecao + " "
                + "and jgdr.titular = true and jgdr.id not in " + auxQueryJogadoresSairamJogo(idJogo) + ") "
                //jogadores reservas, da seleção passada como parâmetro, que entraram em campo
                + "or ("
                + "jgdr.id in " + auxQueryJogadoresEntraramJogoSelecao(idJogo, idSelecao) + ")) "
                //menos os jogadores expulsos
                + "and jgdr.id not in " + auxQueryJogadoresExpulsosJogo(idJogo) + " "
                + "and jgdr.id != " + idJogador;
    }

    //reservas disponíveis, num dado jogo, para uma dada seleção
    private String auxQueryJogadoresReservaSelecao(Short idJogo, Short idSelecao)
    {
        return "from Jogador jgdr "
                //reservas da seleção
                + "where jgdr.selecao.id = " + idSelecao + " and jgdr.titular = false and "
                //menos os reservas que entraram em campo
                + "jgdr.id not in " + auxQueryJogadoresEntraramJogoSelecao(idJogo, idSelecao);
    }

    public int queryVerificarCamisaExistente(Short camisa, Short idSelecao)
    {
        String hql;
        int resultado;
        hql = "select count(j.id) from Jogador j where j.ncamisa = " + camisa + " and j.selecao.id = " + idSelecao;
        resultado = Integer.parseInt(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult().toString());
        return resultado;
    }
}
