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

	public CtrMain()
	{
		ctrAdmin = new CtrAdmin();
		ctrComiteGestor = new CtrComiteGestor();
		ctrTecnico = new CtrTecnico();
		ctrEntusiasta = new CtrEntusiasta();
		
		limLogin = new LimLogin(this);
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
}
