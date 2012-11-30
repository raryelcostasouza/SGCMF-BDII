package sgcmf.control;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.hibernate.Usuario;
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
    private CtrTabelaCampeonato ctrTabelaCampeonato;
    private Thread t;
    private CtrUsuario ctrUsuario;

    public CtrMain()
    {
        ctrCartao = new CtrCartao(this);
        ctrSubstituicao = new CtrSubstituicao(this);
        ctrFalta = new CtrFalta(this);
        ctrGol = new CtrGol(this);
        ctrJogo = new CtrJogo(this);
        ctrSelecao = new CtrSelecao();
        ctrRelatorio = new CtrRelatorio(this);
        ctrTabelaCampeonato = new CtrTabelaCampeonato(this);
        ctrOcorrenciaJogo = new CtrOcorrenciaJogo(this);
        ctrJogador = new CtrJogador();
        ctrUsuario = new CtrUsuario();

        ctrAdmin = new CtrAdmin(this);
        ctrComiteGestor = new CtrComiteGestor(this);
        ctrTecnico = new CtrTecnico(this);
        ctrEntusiasta = new CtrEntusiasta(this);

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

    public void login(String login, char[] senhaBD)
    {
        String senha = new String(senhaBD);

        Usuario u = ctrUsuario.loginUsuario(login, senha);

        if (u == null)
        {
            JOptionPane.showMessageDialog(null, "Login inválido ou inexistente!", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {

            if (u.getPerfil().equals("Administrador"))
            {
                limLogin.setVisible(false);
                ctrAdmin.ativaTela();
            }
            else if (u.getPerfil().equals("Membro Comite"))
            {
                limLogin.setVisible(false);
                ctrComiteGestor.ativaTela();
            }
            else if (u.getPerfil().equals("Tecnico da Selecao"))
            {
                limLogin.setVisible(false);
                ctrTecnico.ativaTela(u);
            }
            else if (u.getPerfil().equals("Entusiasta"))
            {
                limLogin.setVisible(false);
                ctrEntusiasta.ativaTela();
            }
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
    
    public CtrTabelaCampeonato getCtrTabelaCampeonato()
    {
        return ctrTabelaCampeonato;
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
