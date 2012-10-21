package sgcmf.control;

import sgcmf.view.LimTecnico;

public class CtrTecnico
{
	private LimTecnico limTecnico;
	
	public CtrTecnico()
	{
		limTecnico = new LimTecnico();
	}
	
	public void ativaTela()
	{
		limTecnico.setVisible(true);
	}
}
