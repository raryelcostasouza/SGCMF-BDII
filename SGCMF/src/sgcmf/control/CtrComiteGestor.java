package sgcmf.control;

import sgcmf.view.comiteGestor.LimComiteGestor;

public class CtrComiteGestor
{
	private LimComiteGestor limComiteGestor;
	
	public CtrComiteGestor()
	{
		limComiteGestor = new LimComiteGestor();
	}
	
	public void ativaTela()
	{
		limComiteGestor.setVisible(true);
	}
}
