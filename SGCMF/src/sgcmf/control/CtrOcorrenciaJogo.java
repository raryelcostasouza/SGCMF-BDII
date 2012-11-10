package sgcmf.control;

import java.sql.Time;
import java.util.ArrayList;
import sgcmf.model.dao.FaltaDAO;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.JogoDAO;
import sgcmf.model.hibernate.Falta;
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
        JogoDAO jDAO;

        jDAO = new JogoDAO();
        jogo = ctrMain.getCtrJogo().carregarJogoById(idJogo);

        tempo = new Time(0, Integer.parseInt(min), Integer.parseInt(seg));

        oc = new Ocorrencia();
        oc.setInstantetempo(tempo);
        oc.setJogo(jogo);

        gdao.salvar(oc);

        return oc;
    }

    

    public String[][] queryFaltaByIdJogo(Short idJogo)
    {
        ArrayList<Falta> alFalta;
        String[][] dadosFalta;
        FaltaDAO faltaDAO;

        faltaDAO = new FaltaDAO();
        alFalta = faltaDAO.queryFaltaByIdJogo(idJogo);
        dadosFalta = arrayListFalta2StringMatrix(alFalta);
        faltaDAO.fecharSessao();

        return dadosFalta;
    }

    public String[][] arrayListFalta2StringMatrix(ArrayList<Falta> alFalta)
    {
        String[][] dadosFalta;
        Falta f;

        dadosFalta = new String[alFalta.size()][5];
        for (int i = 0; i < alFalta.size(); i++)
        {
            f = alFalta.get(i);
            dadosFalta[i][0] = String.valueOf(f.getIdoc());
            dadosFalta[i][1] = String.valueOf(f.getOcorrencia().getInstantetempo());
            dadosFalta[i][2] = f.getJogador().getNome();

            if (f.getCartao() != null)
            {
                dadosFalta[i][3] = f.getCartao().getCor();
            }
            else
            {
                dadosFalta[i][3] = "";
            }

            dadosFalta[i][4] = f.getTipo();
        }

        return dadosFalta;
    }

    public ResultadoOperacao registrarFalta(String min, String seg, Short idJogo, String idJogador,
                                            String tipo, String cartao)
    {
        
        
        return null;
    }
}
