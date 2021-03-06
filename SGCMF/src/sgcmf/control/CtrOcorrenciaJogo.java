package sgcmf.control;

import java.util.GregorianCalendar;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.JogoDAO;
import sgcmf.model.dao.OcorrenciaDAO;
import sgcmf.model.hibernate.Jogo;
import sgcmf.model.hibernate.Ocorrencia;

public class CtrOcorrenciaJogo
{
    private CtrMain ctrMain;

    public CtrOcorrenciaJogo(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
    }

    public Ocorrencia registraOcorrencia(String min, String seg, Short idJogo)
    {
        Ocorrencia oc;
        Jogo jogo;
        GregorianCalendar gc;

        jogo = new Jogo();
        jogo = JogoDAO.getInstance().carregar(jogo, idJogo);

        gc = new GregorianCalendar(0, 0, 0, 0, Integer.parseInt(min), Integer.parseInt(seg));

        oc = new Ocorrencia();
        oc.setInstantetempo(gc.getTime());
        oc.setJogo(jogo);
        OcorrenciaDAO.getInstance().salvar(oc);
        ctrMain.getCtrTabelaCampeonato().setPrecisaAtualizarTabela(true);

        return oc;
    }
    
    public void removerOcorrencia(Ocorrencia oc)
    {
        OcorrenciaDAO.getInstance().apagar(oc);
        ctrMain.getCtrTabelaCampeonato().setPrecisaAtualizarTabela(true);
    }
    
    public String validaCampos(String min, String seg, Short idJogo)
    {
        int intMin;
        int intSeg;
        int numOcorrenciasDepois;
        int numOcorrenciasMesmoInstante;
        String errorMessage;
        OcorrenciaDAO oDAO;
        GregorianCalendar gc;

        errorMessage = "";
        try
        {
            intMin = Integer.parseInt(min);
            intSeg = Integer.parseInt(seg);

            if (intSeg > 59 || intSeg < 0)
            {
                errorMessage = "Instante de tempo: os segundos devem ser um número de 0 a 59";
            }
            if (intMin < 0 || intMin > 90)
            {
                errorMessage = "Instante de tempo: os minutos devem ser um número de 0 a 90";
            }
        }
        catch (NumberFormatException nfe)
        {
            errorMessage = "Instante de tempo: digite números válidos.";
        }

        if (errorMessage.equals(""))
        {
            //as ocorrências devem ser lançadas sequencialmente pelo instante de tempo
            //se houver alguma ocorrencia depois do instante de tempo da ocorrencia que o usuario
            //quer inserir ele não poderá inseri-la...
            SGCMFSessionManager.abrirSessao();
            oDAO = OcorrenciaDAO.getInstance();
            
            gc = new GregorianCalendar(0, 0, 0, 0, Integer.parseInt(min), Integer.parseInt(seg));
            numOcorrenciasDepois = oDAO.queryNumOcorrenciasComInstanteTempoMaior(idJogo, gc.getTime());
            
            if (numOcorrenciasDepois > 0)
            {
                errorMessage = "As ocorrências só podem ser lançadas de forma sequencial crescentepelo instante de tempo.\n" +
                                "Para esse jogo já existem ocorrências registradas para instantes de tempo posteriores.\n"
                                + "Caso queira adicionar essa ocorrência é necessário antes remover todas as ocorrências posteriores.";
            }
            SGCMFSessionManager.fecharSessao();
        }
        
        if (errorMessage.equals(""))
        {
            //a combinação de Jogo e instante de tempo é UNIQUE
            SGCMFSessionManager.abrirSessao();
            oDAO = OcorrenciaDAO.getInstance();
            
            gc = new GregorianCalendar(0, 0, 0, 0, Integer.parseInt(min), Integer.parseInt(seg));
            numOcorrenciasMesmoInstante = oDAO.queryNumOcorrenciaMesmoInstanteTempoNoJogo(idJogo, gc.getTime());
            
            if (numOcorrenciasMesmoInstante == 1)
            {
                errorMessage = "Já há uma ocorrência cadastrada com o mesmo instante de tempo para esse jogo.";
            }
            SGCMFSessionManager.fecharSessao();
        }

        return errorMessage;
    }
    
    public String validaRemocao(Ocorrencia oc, Short idOc)
    {
        int numOcDepois;
        
        OcorrenciaDAO.getInstance().carregar(oc, idOc);
        
        numOcDepois = OcorrenciaDAO.getInstance().queryNumOcorrenciasComInstanteTempoMaior(oc.getJogo().getId(), oc.getInstantetempo());
        
        if (numOcDepois == 0)
        {
            return "";
        }
        else
        {
           return "As ocorrências só podem ser removidas de forma sequencial descrescente pelo instante de tempo.\n" +
                                "Para esse jogo já existem ocorrências registradas para instantes de tempo posteriores.\n"
                                + "Caso queira remover essa ocorrência é necessário antes remover todas as ocorrências posteriores.";
        }
    }
}
