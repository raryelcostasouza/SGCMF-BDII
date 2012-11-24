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
    
    public int queryNumGolsJogoSelecao(Short idJogo, Short idSelecaoI, Short idSelecaoII)
    {
        String hql;
        
        hql = "select count(g.idoc) "
                + "from Gol g "
                + "where g.ocorrencia.jogo.id = " + idJogo + " "
                //gols a favor da selecaoI
                + "and ((g.tipo = 'A Favor' and g.jogadorByIdjogadorautor.selecao.id = " + idSelecaoI+") "
                //+ gols contra da selecao II
                + "or (g.tipo = 'Contra' and g.jogadorByIdjogadorautor.selecao.id = "+ idSelecaoII  +")"
                + ")";
        return Integer.parseInt(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult()+"");
    }
}
