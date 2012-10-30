package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.dao.SelecaoDAO;
import sgcmf.model.hibernate.Selecao;

public class CtrSelecao
{
	private CtrMain ctrMain;
	private SelecaoDAO sDAO;
	
	public CtrSelecao(CtrMain ctrMain)
	{
		this.ctrMain = ctrMain;
		sDAO = new SelecaoDAO();
	}	
	
	public String[][] querySelecaoTodos()
	{
		ArrayList<Selecao> alSelecao;
		String[][] dadosSelecoes;
		
		alSelecao = ctrMain.getGeneralDAO().listaTodos("Selecao");	
		dadosSelecoes = arrayList2StringMatrix(alSelecao);
	
		return dadosSelecoes;
	}
	
	public String[][] querySelecaoByNomePais(String pais)
	{
		ArrayList<Selecao> alSelecao;
		String[][] dadosSelecoes;
		
		alSelecao = sDAO.querySelecaoByNomePais(pais);
		dadosSelecoes = arrayList2StringMatrix(alSelecao);
		
		return dadosSelecoes;
	}
	
	public String[][] querySelecaoByNomeTecnico(String nomeTecnico)
	{
		String[][] dadosSelecoes;
		ArrayList<Selecao> alSelecao;
		
		alSelecao = sDAO.querySelecaoByNomeTecnico(nomeTecnico);
		dadosSelecoes = arrayList2StringMatrix(alSelecao);
		
		return dadosSelecoes;
	}
	
	private String[][] arrayList2StringMatrix(ArrayList<Selecao> alSelecao)
	{
		String[][] dadosSelecoes;
		Selecao s;
		
		dadosSelecoes = new String[alSelecao.size()][4];
		for (int i = 0; i < alSelecao.size(); i++)
		{
			s = alSelecao.get(i);
			dadosSelecoes[i][0] = s.getId()+"";
			dadosSelecoes[i][1] = s.getPais();
			dadosSelecoes[i][2] = s.getUsuario().getNome();			
			dadosSelecoes[i][3] = s.getCaminhoimgbandeira();			
		}
		
		return dadosSelecoes;
	}
			
}
