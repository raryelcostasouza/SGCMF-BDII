package sgcmf.control;

import javax.swing.JOptionPane;
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
	
    public CtrMain()
    {
		ctrJogo = new CtrJogo();
		ctrSelecao = new CtrSelecao();
		ctrOcorrenciaJogo = new CtrOcorrenciaJogo();
		ctrJogador = new CtrJogador();
		
        ctrAdmin = new CtrAdmin();
        ctrComiteGestor = new CtrComiteGestor(this);
        ctrTecnico = new CtrTecnico(this);
        ctrEntusiasta = new CtrEntusiasta();

        limLogin = new LimLogin(this);
        limLogin.setVisible(true);
	}

    public void ativaTela()
    {
        limLogin.setVisible(true);
    }

    public void login(String login, char[] senha)
    {
        if (login.equals("admin"))
        {
            limLogin.setVisible(false);
            ctrAdmin.ativaTela();
        }
        else if (login.equals("comiteGestor"))
        {
            limLogin.setVisible(false);
            ctrComiteGestor.ativaTela();
        }
        else if (login.equals("tecnico"))
        {
            limLogin.setVisible(false);
            ctrTecnico.ativaTela();
        }
        else if (login.equals("entusiasta"))
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

	public CtrOcorrenciaJogo getCtrOcorrenciaJogo()
	{
		return ctrOcorrenciaJogo;
	}
	
	public CtrJogador getCtrJogador()
	{
		return ctrJogador;
	}
}
