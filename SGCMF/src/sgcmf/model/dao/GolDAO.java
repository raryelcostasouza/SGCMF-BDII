package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Gol;

public class GolDAO extends GeneralDAO<Gol>
{
    public ArrayList<Gol> listaTodos()
    {
        return (ArrayList<Gol>) sessao.createQuery("from Gol g order by g.ocorrencia.instantetempo").list();
    }

    public ArrayList<Gol> queryGolByIdJogo(Short idJogo)
    {
        String hql;

        hql = "from Gol g "
                + "where g.ocorrencia.jogo.id = " + idJogo;

        return (ArrayList<Gol>) sessao.createQuery(hql).list();
    }
}
