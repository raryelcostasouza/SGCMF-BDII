package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.hibernate.Gol;
import sgcmf.model.hibernate.Ocorrencia;

public class GolDAO
{
    private static GolDAO instance;

    private GolDAO()
    {
        
    }
    
    public static GolDAO getInstance()
    {
        if (instance == null)
        {
            instance = new GolDAO();
        }
        return instance;
    }

    public void salvar(Gol entidade)
    {
        SGCMFSessionManager.getCurrentSession().save(entidade);
    }

    public Gol carregar(Gol entidade, Serializable id)
    {
        SGCMFSessionManager.getCurrentSession().load(entidade, id);
        return entidade;
    }

    public void apagar(Gol entidade)
    {
        SGCMFSessionManager.getCurrentSession().delete(entidade);
    }

    public ArrayList<Gol> queryGolByIdJogo(Short idJogo)
    {
        String hql;

        hql = "from Gol g "
                + "where g.ocorrencia.jogo.id = " + idJogo + " order by g.ocorrencia.instantetempo";

        return (ArrayList<Gol>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }
}
