package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.hibernate.Cartao;
import sgcmf.model.hibernate.Gol;
import sgcmf.model.hibernate.Substituicao;

public class SubstituicaoDAO2
{
    private static SubstituicaoDAO2 instance;
    
    public static SubstituicaoDAO2 getInstance()
    {
        if (instance == null)
        {
            instance = new SubstituicaoDAO2();
        }
        return instance;
    }
    
    public void salvar(Substituicao entidade)
    {
        SGCMFSessionManager.getCurrentSession().save(entidade);
    }
    
    public Substituicao carregar(Substituicao entidade, Serializable id)
    {
        SGCMFSessionManager.getCurrentSession().load(entidade, id);
        return entidade;
    }
    
     public void apagar(Substituicao entidade)
    {
        SGCMFSessionManager.getCurrentSession().delete(entidade);
    }
    
    public ArrayList<Substituicao> querySubstByIdJogo(Short idJogo)
    {
        String hql;

        hql = "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + " order by s.ocorrencia.instantetempo";
        
        return (ArrayList<Substituicao>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }
}
