package sgcmf.control;

import javax.swing.JOptionPane;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.view.LimLogin;

public class CtrMain
{
    private LimLogin limLogin;
    private CtrAdmin ctrAdmin;
    private CtrComiteGestor ctrComiteGestor;
    private CtrTecnico ctrTecnico;
    private CtrEntusiasta ctrEntusiasta;
    private CtrJogo ctrJogo;
    private CtrSelecao ctrSelecao;
    private CtrOcorrenciaJogo ctrOcorrenciaJogo;
    private CtrJogador ctrJogador;
    private CtrRelatorio ctrRelatorio;

    public CtrMain()
    {
        GeneralDAO gdao = new GeneralDAO();
        gdao.fecharSessao();
        gdao = null;

        ctrJogo = new CtrJogo();
        ctrRelatorio = new CtrRelatorio();
        ctrSelecao = new CtrSelecao();
        ctrOcorrenciaJogo = new CtrOcorrenciaJogo(this);
        ctrJogador = new CtrJogador();

        ctrAdmin = new CtrAdmin(this);
        ctrComiteGestor = new CtrComiteGestor(this);
        ctrTecnico = new CtrTecnico(this);
        ctrEntusiasta = new CtrEntusiasta();

        

        limLogin = new LimLogin(this);

        //Aqui tem que esperar a thread A(Splash) terminar o trabalho.

        limLogin.setVisible(true);
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
}
