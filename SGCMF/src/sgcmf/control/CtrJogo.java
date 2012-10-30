package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.dao.JogoDAO;
import sgcmf.model.hibernate.Jogo;

public class CtrJogo
{
	private CtrMain ctrMain;
	private JogoDAO jDAO;
	
	public CtrJogo(CtrMain ctrMain)
	{
		this.ctrMain = ctrMain;
		jDAO = new JogoDAO();
	}	
	
	public Jogo carregarJogoById(Short idJogo)
	{
		Jogo j;
		
		j = new Jogo();
		j = (Jogo) ctrMain.getGeneralDAO().carregar(j, idJogo);
	
		return j;
	}
	
	public String[][] queryJogoTodos()
	{
		ArrayList<Jogo> alJogos;
		String[][] dadosJogos;
		
		alJogos = ctrMain.getGeneralDAO().listaTodos("Jogo");
		dadosJogos = arrayList2StringMatrix(alJogos);
		
		return dadosJogos;
	}
	
	public String[][] queryJogoBySelecao(String nomeSelecao)
	{
		String[][] dadosJogos;
		ArrayList<Jogo> alJogo;
		
		alJogo = jDAO.queryJogoBySelecao(nomeSelecao);
		dadosJogos = arrayList2StringMatrix(alJogo);
		
		return dadosJogos;
	}
	
	public String[][] queryJogoByCidade(String cidade)
	{
		String[][] dadosJogos;
		ArrayList<Jogo> alJogo;
		
		alJogo = jDAO.queryJogoByCidade(cidade);
		dadosJogos = arrayList2StringMatrix(alJogo);
		
		return dadosJogos;
	}
	
	public String[][] queryJogoByEstadio(String estadio)
	{
		String[][] dadosJogos;
		ArrayList<Jogo> alJogo;
		
		alJogo = jDAO.queryJogoByEstadio(estadio);
		dadosJogos = arrayList2StringMatrix(alJogo);
			
		return dadosJogos;
	}

	public String[][] queryJogoByTipo(String tipo)
	{
		String[][] dadosJogos;
		ArrayList<Jogo> alJogo;
		
		alJogo = jDAO.queryJogoByTipo(tipo);
		dadosJogos = arrayList2StringMatrix(alJogo);
			
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
