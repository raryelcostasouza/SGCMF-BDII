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
		Jogo j;
		
		gdao = new GeneralDAO<Jogo>();
		alJogos = gdao.listaTodos("Jogo");
		dadosJogos = new String[alJogos.size()][7];
		for (int i = 0; i < alJogos.size(); i++)
		{
			j = alJogos.get(i);
			dadosJogos[i][0] = String.valueOf(j.getTipo());
			dadosJogos[i][1] = String.valueOf(j.getDatahora());
			dadosJogos[i][2] = j.getCidade();
			dadosJogos[i][3] = j.getNomeestadio();
			dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
			dadosJogos[i][5] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
			dadosJogos[i][6] = String.valueOf(j.isProrrogacao());
		}
		
		gdao.fecharSessao();
		return dadosJogos;
	}
	
	public String[][] queryJogoBySelecao(String nomeSelecao)
	{
		String[][] dadosJogos;
		JogoDAO jDao;
		ArrayList<Jogo> alJogo;
		Jogo j;
		
		jDao = new JogoDAO();
		alJogo = jDao.queryJogoBySelecao(nomeSelecao);
		dadosJogos = new String[alJogo.size()][7];
		for (int i = 0; i < alJogo.size(); i++)
		{
			j = alJogo.get(i);
			dadosJogos[i][0] = String.valueOf(j.getTipo());
			dadosJogos[i][1] = String.valueOf(j.getDatahora());
			dadosJogos[i][2] = j.getCidade();
			dadosJogos[i][3] = j.getNomeestadio();
			dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
			dadosJogos[i][5] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
			dadosJogos[i][6] = String.valueOf(j.isProrrogacao());
		}
		
		jDao.fecharSessao();	
		
		return dadosJogos;
	}
	
	public String[][] queryJogoByCidade(String cidade)
	{
		String[][] dadosJogos;
		JogoDAO jDao;
		ArrayList<Jogo> alJogo;
		Jogo j;
		
		jDao = new JogoDAO();
		alJogo = jDao.queryJogoByCidade(cidade);
		dadosJogos = new String[alJogo.size()][7];
		for (int i = 0; i < alJogo.size(); i++)
		{
			j = alJogo.get(i);
			dadosJogos[i][0] = String.valueOf(j.getTipo());
			dadosJogos[i][1] = String.valueOf(j.getDatahora());
			dadosJogos[i][2] = j.getCidade();
			dadosJogos[i][3] = j.getNomeestadio();
			dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
			dadosJogos[i][5] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
			dadosJogos[i][6] = String.valueOf(j.isProrrogacao());
		}
		
		jDao.fecharSessao();	
		
		return dadosJogos;
	}
	
	public String[][] queryJogoByEstadio(String estadio)
	{
		String[][] dadosJogos;
		JogoDAO jDao;
		ArrayList<Jogo> alJogo;
		Jogo j;
		
		jDao = new JogoDAO();
		alJogo = jDao.queryJogoByEstadio(estadio);
		dadosJogos = new String[alJogo.size()][7];
		for (int i = 0; i < alJogo.size(); i++)
		{
			j = alJogo.get(i);
			dadosJogos[i][0] = String.valueOf(j.getTipo());
			dadosJogos[i][1] = String.valueOf(j.getDatahora());
			dadosJogos[i][2] = j.getCidade();
			dadosJogos[i][3] = j.getNomeestadio();
			dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
			dadosJogos[i][5] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
			dadosJogos[i][6] = String.valueOf(j.isProrrogacao());
		}
		
		jDao.fecharSessao();	
		
		return dadosJogos;
	}

	public String[][] queryJogoByTipo(String tipo)
	{
		String[][] dadosJogos;
		JogoDAO jDao;
		ArrayList<Jogo> alJogo;
		Jogo j;
		
		jDao = new JogoDAO();
		alJogo = jDao.queryJogoByTipo(tipo);
		dadosJogos = new String[alJogo.size()][7];
		for (int i = 0; i < alJogo.size(); i++)
		{
			j = alJogo.get(i);
			dadosJogos[i][0] = String.valueOf(j.getTipo());
			dadosJogos[i][1] = String.valueOf(j.getDatahora());
			dadosJogos[i][2] = j.getCidade();
			dadosJogos[i][3] = j.getNomeestadio();
			dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
			dadosJogos[i][5] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
			dadosJogos[i][6] = String.valueOf(j.isProrrogacao());
		}
		
		jDao.fecharSessao();	
		
		return dadosJogos;
	}
}
