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
}
