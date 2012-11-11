package sgcmf.control;

import java.sql.Time;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.hibernate.Jogo;
import sgcmf.model.hibernate.Ocorrencia;

public class CtrOcorrenciaJogo
{
    private CtrMain ctrMain;

    public CtrOcorrenciaJogo(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
    }

    public Ocorrencia registraOcorrencia(GeneralDAO gdao, String min, String seg, Short idJogo)
    {
        Ocorrencia oc;
        Jogo jogo;
        Time tempo;

        jogo = new Jogo();
        gdao.carregar(jogo, idJogo);

        tempo = new Time(0, Integer.parseInt(min), Integer.parseInt(seg));

        oc = new Ocorrencia();
        oc.setInstantetempo(tempo);
        oc.setJogo(jogo);

        gdao.salvar(oc);

        return oc;
    }
}
