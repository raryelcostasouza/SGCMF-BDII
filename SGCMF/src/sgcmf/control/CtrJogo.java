package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.JogadorDAO;
import sgcmf.model.dao.JogoDAO;
import sgcmf.model.hibernate.Jogo;

public class CtrJogo
{

	public Jogo carregarJogoById(Short idJogo)
	{
		Jogo j;
		GeneralDAO<Jogo> gdao;
		
		gdao = new GeneralDAO<Jogo>();
		j = new Jogo();
		j = gdao.carregar(j, idJogo);
		gdao.fecharSessao();
		
		return j;
	}
	
	public String[][] queryJogoTodos()
	{
		JogoDAO jDAO;
		ArrayList<Jogo> alJogos;
		String[][] dadosJogos;
		
		jDAO = new JogoDAO();
		alJogos = jDAO.listaTodos();
		dadosJogos = arrayList2StringMatrix(alJogos);
		jDAO.fecharSessao();
		
		return dadosJogos;
	}
	
	public String[][] queryJogoBySelecao(String nomeSelecao)
	{
		JogoDAO jDAO;
		String[][] dadosJogos;
		ArrayList<Jogo> alJogo;
		
		jDAO = new JogoDAO();
		alJogo = jDAO.queryJogoBySelecao(nomeSelecao);
		dadosJogos = arrayList2StringMatrix(alJogo);
		jDAO.fecharSessao();
		
		return dadosJogos;
	}
	
	public String[][] queryJogoByCidade(String cidade)
	{
		JogoDAO jDAO;
		String[][] dadosJogos;
		ArrayList<Jogo> alJogo;
		
		jDAO = new JogoDAO();
		alJogo = jDAO.queryJogoByCidade(cidade);
		dadosJogos = arrayList2StringMatrix(alJogo);
		jDAO.fecharSessao();
		
		return dadosJogos;
	}
	
	public String[][] queryJogoByEstadio(String estadio)
	{
		JogoDAO jDAO;
		String[][] dadosJogos;
		ArrayList<Jogo> alJogo;
		
		jDAO = new JogoDAO();
		alJogo = jDAO.queryJogoByEstadio(estadio);
		dadosJogos = arrayList2StringMatrix(alJogo);
		jDAO.fecharSessao();
		
		return dadosJogos;
	}

	public String[][] queryJogoByTipo(String tipo)
	{
		JogoDAO jDAO;
		String[][] dadosJogos;
		ArrayList<Jogo> alJogo;
		
		jDAO = new JogoDAO();
		alJogo = jDAO.queryJogoByTipo(tipo);
		dadosJogos = arrayList2StringMatrix(alJogo);
		jDAO.fecharSessao();	
		
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
		jDao.fecharSessao();
		
		return infoJogo;		
	}
}
