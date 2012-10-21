package sgcmf.control;

import sgcmf.view.LimAdmin;

public class CtrAdmin
{
	private LimAdmin limAdmin;
	
	public CtrAdmin()
	{
		limAdmin = new LimAdmin();
	}
	
	public void ativaTela()
	{
		limAdmin.setVisible(true);
	}			
}
