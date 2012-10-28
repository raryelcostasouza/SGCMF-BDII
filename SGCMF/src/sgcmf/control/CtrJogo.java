package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.Jogo;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.JogoDAO;

public class CtrJogo
{
	public String[][] queryJogoTodos()
	{
		GeneralDAO<Jogo> gdao;
		ArrayList<Jogo> alJogos;
		String[][] dadosJogos;
		
		gdao = new GeneralDAO<Jogo>();
		alJogos = gdao.listaTodos("Jogo");
		dadosJogos = arrayList2StringMatrix(alJogos);
		
		gdao.fecharSessao();
		return dadosJogos;
	}
	
	public String[][] queryJogoBySelecao(String nomeSelecao)
	{
		String[][] dadosJogos;
		JogoDAO jDao;
		ArrayList<Jogo> alJogo;
		
		jDao = new JogoDAO();
		alJogo = jDao.queryJogoBySelecao(nomeSelecao);
		dadosJogos = arrayList2StringMatrix(alJogo);
		
		jDao.fecharSessao();	
		return dadosJogos;
	}
	
	public String[][] queryJogoByCidade(String cidade)
	{
		String[][] dadosJogos;
		JogoDAO jDao;
		ArrayList<Jogo> alJogo;
		
		jDao = new JogoDAO();
		alJogo = jDao.queryJogoByCidade(cidade);
		dadosJogos = arrayList2StringMatrix(alJogo);
	
		jDao.fecharSessao();	
		return dadosJogos;
	}
	
	public String[][] queryJogoByEstadio(String estadio)
	{
		String[][] dadosJogos;
		JogoDAO jDao;
		ArrayList<Jogo> alJogo;
		
		jDao = new JogoDAO();
		alJogo = jDao.queryJogoByEstadio(estadio);
		dadosJogos = arrayList2StringMatrix(alJogo);
		
		jDao.fecharSessao();	
		return dadosJogos;
	}

	public String[][] queryJogoByTipo(String tipo)
	{
		String[][] dadosJogos;
		JogoDAO jDao;
		ArrayList<Jogo> alJogo;
		
		jDao = new JogoDAO();
		alJogo = jDao.queryJogoByTipo(tipo);
		dadosJogos = arrayList2StringMatrix(alJogo);
		
		jDao.fecharSessao();	
		return dadosJogos;
	}
	
	private String[][] arrayList2StringMatrix(ArrayList<Jogo> alJogo)
	{
		String[][] dadosJogos;
		Jogo j;
		
		dadosJogos = new String[alJogo.size()][8];
		for (int i = 0; i < alJogo.size(); i++)
		{
			j = alJogo.get(i);
			dadosJogos[i][0] = String.valueOf(j.getId());
			dadosJogos[i][1] = String.valueOf(j.getTipo());
			dadosJogos[i][2] = String.valueOf(j.getDatahora());
			dadosJogos[i][3] = j.getCidade();
			dadosJogos[i][4] = j.getNomeestadio();
			dadosJogos[i][5] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
			dadosJogos[i][6] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
			dadosJogos[i][7] = String.valueOf(j.isProrrogacao());
		}
		
		return dadosJogos;
	}
	
	public String queryInfoJogoById(Short idJogo)
	{
		String infoJogo;
		JogoDAO jDao;
		Jogo j;
		
		jDao = new JogoDAO();
		j = jDao.queryInfoJogoById(idJogo);
		
		infoJogo = j.getDatahora() + " | " + j.getTipo() + " | " + 
				j.getSelecaoByIdselecaoi().getPais() + " vs " + j.getSelecaoByIdselecaoii().getPais();
		
		return infoJogo;		
	}
}
