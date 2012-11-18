package sgcmf.model.dao;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.Query;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.hibernate.Ocorrencia;

public class OcorrenciaDAO2
{
    private static OcorrenciaDAO2 instance;

    public static OcorrenciaDAO2 getInstance()
    {
        if (instance == null)
        {
            instance = new OcorrenciaDAO2();
        }
        return instance;
    }

    public void salvar(Ocorrencia entidade)
    {
        SGCMFSessionManager.getCurrentSession().save(entidade);
    }

    public Ocorrencia carregar(Ocorrencia entidade, Serializable id)
    {
        SGCMFSessionManager.getCurrentSession().load(entidade, id);
        return entidade;
    }

    public void apagar(Ocorrencia entidade)
    {
        SGCMFSessionManager.getCurrentSession().delete(entidade);
    }

    public Integer queryNumOcorrenciasComInstanteTempoMaior(Short idJogo, Date instanteTempo)
    {
        String hql;
        String resultado;

        hql = "select count (o.id) "
                + "from Ocorrencia o "
                + "where o.jogo.id = " + idJogo + " and o.instantetempo > :it";
        Query q = SGCMFSessionManager.getCurrentSession().createQuery(hql);
        q.setParameter("it", instanteTempo);

        resultado = String.valueOf(q.uniqueResult());
        return Integer.parseInt(resultado);
    }
}
