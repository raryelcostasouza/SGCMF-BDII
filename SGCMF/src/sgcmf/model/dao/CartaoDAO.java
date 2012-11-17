package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Cartao;

public class CartaoDAO extends GeneralDAO<Cartao>
{
    public ArrayList<Cartao> queryCartaoByIdJogo(Short idJogo)
    {
        String hql;

        hql = "from Cartao c "
                + "where c.ocorrencia.jogo.id = " + idJogo + " order by c.ocorrencia.instantetempo";
        
        return (ArrayList<Cartao>) sessao.createQuery(hql).list();
    }
    
    public int queryNumCartaoAmareloJogadorJogo(Short idJogo, Short idJogador)
    {
        String hql;
        
        hql = "select count(c.id) "
                + "from Cartao c "
                + "where c.ocorrencia.jogo.id = " +idJogo + " "
                + "and c.jogador.id = " +idJogador + " "
                + "and c.cor = 'Amarelo'";
        
        return Integer.parseInt(String.valueOf(sessao.createQuery(hql).uniqueResult()));
    }
    
            
}
