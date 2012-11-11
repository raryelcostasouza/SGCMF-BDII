package sgcmf.control;

import java.sql.Time;
import java.util.GregorianCalendar;
import java.util.Locale;
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
        GregorianCalendar gc;

        jogo = new Jogo();
        gdao.carregar(jogo, idJogo);

        gc = new GregorianCalendar(0, 0, 0, 0, Integer.parseInt(min), Integer.parseInt(seg));

        oc = new Ocorrencia();
        oc.setInstantetempo(gc.getTime());
        oc.setJogo(jogo);

        gdao.salvar(oc);

        return oc;
    }

    public String validaCampos(String min, String seg)
    {
        int intMin;
        int intSeg;
        String errorMessage;

        errorMessage = "";
        try
        {
            intMin = Integer.parseInt(min);
            intSeg = Integer.parseInt(seg);
            
            if (intSeg > 59 || intSeg < 0)
            {
                errorMessage = "Instante de tempo: os segundos devem ser um número de 0 a 59";
            }
            if (intMin < 0 || intMin > 120)
            {
                errorMessage = "Instante de tempo: os minutos devem ser um número de 0 a 120";
            }
        }
        catch (NumberFormatException nfe)
        {
            errorMessage = "Instante de tempo: digite números válidos.";
        }
        
        return errorMessage;
    }
}
