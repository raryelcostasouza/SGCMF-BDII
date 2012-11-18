package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.hibernate.Cartao;

public class CartaoDAO2
{
    private static CartaoDAO2 instance;
    
    private CartaoDAO2()
    {
        
    }
            
    public static CartaoDAO2 getInstance()
    {
        if (instance == null)
        {
            instance = new CartaoDAO2();
        }
        return instance;        
    }
    
    public void salvar(Cartao entidade)
    {
        SGCMFSessionManager.getCurrentSession().save(entidade);
    }

    public Cartao carregar(Cartao entidade, Serializable id)
    {
        SGCMFSessionManager.getCurrentSession().load(entidade, id);
        return entidade;
    }

    public void apagar(Cartao entidade)
    {
        SGCMFSessionManager.getCurrentSession().delete(entidade);
    }
    
    public ArrayList<Cartao> queryCartaoByIdJogo(Short idJogo)
    {
        String hql;
        
        hql = "from Cartao c "
                + "where c.ocorrencia.jogo.id = " + idJogo + " order by c.ocorrencia.instantetempo";
        
        return (ArrayList<Cartao>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }
    
    public int queryNumCartaoAmareloJogadorJogo(Short idJogo, Short idJogador)
    {
        String hql;
        
        hql = "select count(c.id) "
                + "from Cartao c "
                + "where c.ocorrencia.jogo.id = " +idJogo + " "
                + "and c.jogador.id = " +idJogador + " "
                + "and c.cor = 'Amarelo'";
        
        return Integer.parseInt(String.valueOf(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult()));
    }            
}
