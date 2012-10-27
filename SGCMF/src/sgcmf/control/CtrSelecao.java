package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.Selecao;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.SelecaoDAO;

public class CtrSelecao
{
	public String[][] querySelecaoTodos()
	{
		GeneralDAO<Selecao> gdao;
		ArrayList<Selecao> alSelecao;
		String[][] dadosSelecoes;
		
		gdao = new GeneralDAO<Selecao>();
		alSelecao = gdao.listaTodos("Selecao");
		
		dadosSelecoes = arrayList2StringMatrix(alSelecao);
		
		gdao.fecharSessao();	
		return dadosSelecoes;
	}
	
	public String[][] querySelecaoByNomePais(String pais)
	{
		SelecaoDAO sdao;
		ArrayList<Selecao> alSelecao;
		String[][] dadosSelecoes;
		
		sdao = new SelecaoDAO();
		alSelecao = sdao.querySelecaoByNomePais(pais);
		
		dadosSelecoes = arrayList2StringMatrix(alSelecao);

		sdao.fecharSessao();
		return dadosSelecoes;
	}
	
	public String[][] querySelecaoByNomeTecnico(String nomeTecnico)
	{
		String[][] dadosSelecoes;
		SelecaoDAO sdao;
		ArrayList<Selecao> alSelecao;
		
		sdao = new SelecaoDAO();
		alSelecao = sdao.querySelecaoByNomeTecnico(nomeTecnico);
		dadosSelecoes = arrayList2StringMatrix(alSelecao);
		
		sdao.fecharSessao();
		return dadosSelecoes;
	}
	
	private String[][] arrayList2StringMatrix(ArrayList<Selecao> alSelecao)
	{
		String[][] dadosSelecoes;
		Selecao s;
		
		dadosSelecoes = new String[alSelecao.size()][3];
		for (int i = 0; i < alSelecao.size(); i++)
		{
			s = alSelecao.get(i);
			dadosSelecoes[i][0] = s.getPais();
			dadosSelecoes[i][1] = s.getUsuario().getNome();			
			dadosSelecoes[i][2] = s.getCaminhoimgbandeira();			
		}
		
		return dadosSelecoes;
	}
			
}
