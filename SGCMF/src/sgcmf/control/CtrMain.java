package sgcmf.control;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.view.LimLogin;
import sgcmf.view.ShowSplash;

public class CtrMain
{
    private LimLogin limLogin;
    private CtrAdmin ctrAdmin;
    private CtrFalta ctrFalta;
    private CtrCartao ctrCartao;
    private CtrSubstituicao ctrSubstituicao;
    private CtrComiteGestor ctrComiteGestor;
    private CtrTecnico ctrTecnico;
    private CtrEntusiasta ctrEntusiasta;
    private CtrJogo ctrJogo;
    private CtrGol ctrGol;
    private CtrSelecao ctrSelecao;
    private CtrOcorrenciaJogo ctrOcorrenciaJogo;
    private CtrJogador ctrJogador;
    private CtrRelatorio ctrRelatorio;
    private Thread t;

    public CtrMain()
    {
        ctrCartao = new CtrCartao(this);
        ctrSubstituicao = new CtrSubstituicao(this);
        ctrFalta = new CtrFalta(this);
        ctrGol = new CtrGol(this);
        ctrJogo = new CtrJogo();
        ctrSelecao = new CtrSelecao();
        ctrRelatorio = new CtrRelatorio(this);
        ctrOcorrenciaJogo = new CtrOcorrenciaJogo(this);
        ctrJogador = new CtrJogador();

        ctrAdmin = new CtrAdmin(this);
        ctrComiteGestor = new CtrComiteGestor(this);
        ctrTecnico = new CtrTecnico(this);
        ctrEntusiasta = new CtrEntusiasta();

        limLogin = new LimLogin(this);

        t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                new ShowSplash().start();
                GeneralDAO gdao = new GeneralDAO();
                gdao.fecharSessao();
                gdao = null;
                
                try
                {
                    //Tem que arrumar um jeito de receber um aviso da Thread ShowSplash que terminou e esta pode
                    //setar a tela de limLogin como visivel.
                    Thread.sleep(4500);
                    limLogin.setVisible(true);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(CtrMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();
        //Aqui tem que esperar a thread A(Splash) terminar o trabalho.
    }

    public void ativaTela()
    {
        limLogin.setVisible(true);
    }

    public void login(String login, char[] senha)
    {
        if (login.equals("a"))
        {
            limLogin.setVisible(false);
            ctrAdmin.ativaTela();
        }
        else if (login.equals("c"))
        {
            limLogin.setVisible(false);
            ctrComiteGestor.ativaTela();
        }
        else if (login.equals("t"))
        {
            limLogin.setVisible(false);
            ctrTecnico.ativaTela();
        }
        else if (login.equals("e"))
        {
            limLogin.setVisible(false);
            ctrEntusiasta.ativaTela();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Login inválido!", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public CtrJogo getCtrJogo()
    {
        return ctrJogo;
    }

    public CtrSelecao getCtrSelecao()
    {
        return ctrSelecao;
    }

    public CtrRelatorio getCtrRelatorio()
    {
        return ctrRelatorio;
    }

    public CtrOcorrenciaJogo getCtrOcorrenciaJogo()
    {
        return ctrOcorrenciaJogo;
    }

    public CtrJogador getCtrJogador()
    {
        return ctrJogador;
    }

    public CtrGol getCtrGol()
    {
        return ctrGol;
    }

    public CtrFalta getCtrFalta()
    {
        return ctrFalta;
    }

    public CtrCartao getCtrCartao()
    {
        return ctrCartao;
    }

    public CtrSubstituicao getCtrSubstituicao()
    {
        return ctrSubstituicao;
    }    
}
