package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Gol;

public class GolDAO extends GeneralDAO<Gol>
{
    public ArrayList<Gol> queryGolByIdJogo(Short idJogo)
    {
        String hql;

        hql = "from Gol g "
                + "where g.ocorrencia.jogo.id = " + idJogo + " order by g.ocorrencia.instantetempo";

        return (ArrayList<Gol>) sessao.createQuery(hql).list();
    }
}
