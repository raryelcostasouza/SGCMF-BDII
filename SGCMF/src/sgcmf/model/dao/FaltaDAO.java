package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Falta;

public class FaltaDAO extends GeneralDAO<Falta>
{
    public ArrayList<Falta> queryFaltaByIdJogo(Short idJogo)
    {
        String hql;

        hql = "from Falta f "
                + "where f.ocorrencia.jogo.id = " + idJogo;
        
        return (ArrayList<Falta>) sessao.createQuery(hql).list();
    }
}
