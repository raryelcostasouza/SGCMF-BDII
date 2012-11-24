package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.hibernate.Jogo;

public class JogoDAO
{
    private static JogoDAO instance;
    
    private JogoDAO()
    {
        
    }
    
    public static JogoDAO getInstance()
    {
        if (instance == null)
        {
            instance = new JogoDAO();
        }
        return instance;
    }
    
    public Jogo carregar(Jogo entidade, Serializable id)
    {
        SGCMFSessionManager.getCurrentSession().load(entidade, id);
        return entidade;
    }
    
    public ArrayList<Jogo> listaTodos()
    {
        return (ArrayList<Jogo>) SGCMFSessionManager.getCurrentSession().createQuery("from Jogo j order by j.datahora").list();
    }

    public ArrayList<Jogo> queryJogoBySelecao(String selecao)
    {
        String hql;

        hql = "from Jogo j "
                + "where lower(j.selecaoByIdselecaoi.pais) like lower('%" + selecao + "%') or "
                + "lower(j.selecaoByIdselecaoii.pais) like lower('%" + selecao + "%')";

        return (ArrayList<Jogo>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Jogo> queryJogoByCidade(String cidade)
    {
        String hql;

        hql = "from Jogo j "
                + "where lower(j.cidade) like lower('%" + cidade + "%')";

        return (ArrayList<Jogo>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Jogo> queryJogoByEstadio(String estadio)
    {
        String hql;

        hql = "from Jogo j "
                + "where lower(j.nomeestadio) like lower('%" + estadio + "%')";

        return (ArrayList<Jogo>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Jogo> queryJogoByTipo(String tipo)
    {
        String hql;

        hql = "from Jogo j "
                + "where j.tipo = '" + tipo + "'";

        return (ArrayList<Jogo>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public Jogo queryInfoJogoById(Short idJogo)
    {
        String hql;
        hql = "from Jogo j "
                + "where j.id = " + idJogo;

        return (Jogo) SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult();
    }

    public ArrayList<Jogo> queryJogoByGrupo(String grupo)
    {
        String hql;
        
        hql = "from Jogo j "
                + "where j.selecaoByIdselecaoi.grupo = '" + grupo+"'";
        
        return (ArrayList<Jogo>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }
}
