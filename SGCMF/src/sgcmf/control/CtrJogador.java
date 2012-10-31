package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.JogadorDAO;
import sgcmf.model.hibernate.Jogador;

public class CtrJogador
{
	public Jogador carregaJogadorById(Short idJogador)
	{
		Jogador jogador;
		JogadorDAO jdao;
		
		jogador = new Jogador();
		jdao = new JogadorDAO();
		jdao.carregar(jogador, idJogador);
		jdao.fecharSessao();
		
		return jogador;
	}
	
	public String[][] queryJogadorTodos()
	{
		GeneralDAO<Jogador> gdao;
		String[][] dadosJogadores;
		ArrayList alJogador;
		
		gdao = new GeneralDAO<Jogador>();
		alJogador = gdao.listaTodos("Jogador");
		dadosJogadores = arrayList2StringMatrix(alJogador);
		gdao.fecharSessao();
		
		return dadosJogadores;
	}
	
	public String[][] queryJogadorByNome(String nome)
	{
		JogadorDAO jDAO;
		String[][] dadosJogador;
		ArrayList<Jogador> alJogador;
		
		jDAO = new JogadorDAO();
		alJogador = jDAO.queryJogadorByNome(nome);
		dadosJogador = arrayList2StringMatrix(alJogador);
		jDAO.fecharSessao();
		
		return dadosJogador;
	}
	
	private String[][] arrayList2StringMatrix(ArrayList<Jogador> alJogador)
	{
		String[][] dadosJogadores;
		Jogador j;
		
		dadosJogadores = new String[alJogador.size()][5];
		for (int i = 0; i < alJogador.size(); i++)
		{
			j = alJogador.get(i);
			dadosJogadores[i][0] = String.valueOf(j.getId());
			dadosJogadores[i][1] = j.getSelecao().getPais();
			dadosJogadores[i][2] = String.valueOf(j.getNcamisa());
			dadosJogadores[i][3] = j.getNome();
			dadosJogadores[i][4] = j.getPosicao();
		}
		
		return dadosJogadores;
	}
}
>>>>>>> Correção DAO
