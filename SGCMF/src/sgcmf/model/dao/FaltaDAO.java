package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.hibernate.Falta;

public class FaltaDAO
{
    private static FaltaDAO instance;
    
    private FaltaDAO()
    {
        
    }
    
    public static FaltaDAO getInstance()
    {
        if (instance == null)
        {
            instance = new FaltaDAO();
        }
        return instance;        
    }
    
    public void salvar(Falta entidade)
    {
        SGCMFSessionManager.getCurrentSession().save(entidade);
    }

    public Falta carregar(Falta entidade, Serializable id)
    {
        SGCMFSessionManager.getCurrentSession().load(entidade, id);
        return entidade;
    }

    public void apagar(Falta entidade)
    {
        SGCMFSessionManager.getCurrentSession().delete(entidade);
    }
    
    public ArrayList<Falta> queryFaltaByIdJogo(Short idJogo)
    {
        String hql;

        hql = "from Falta f "
                + "where f.ocorrencia.jogo.id = " + idJogo + " order by f.ocorrencia.instantetempo";
        
        return (ArrayList<Falta>) SGCMFSessionManager.abrirSessao().createQuery(hql).list();
    }
}
