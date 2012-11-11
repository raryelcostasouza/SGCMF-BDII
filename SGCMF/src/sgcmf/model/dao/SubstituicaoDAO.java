package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Substituicao;

public class SubstituicaoDAO extends GeneralDAO<Substituicao>
{
    public ArrayList<Substituicao> querySubstByIdJogo(Short idJogo)
    {
        String hql;

        hql = "from Substituicao s "
                + "where s.ocorrencia.jogo.id = " + idJogo + " order by s.ocorrencia.instantetempo";
        
        return (ArrayList<Substituicao>) sessao.createQuery(hql).list();
    }
}
