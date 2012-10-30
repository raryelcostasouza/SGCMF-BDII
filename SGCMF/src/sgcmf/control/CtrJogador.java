package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.dao.JogadorDAO;
import sgcmf.model.hibernate.Jogador;

public class CtrJogador
{
	private CtrMain ctrMain;
	private JogadorDAO jDAO;

	public CtrJogador(CtrMain ctrMain)
	{
		this.ctrMain = ctrMain;
		jDAO = new JogadorDAO();
	}
	
	public Jogador carregaJogadorById(Short idJogador)
	{
		Jogador jogador;
		
		jogador = new Jogador();
		jogador = (Jogador) ctrMain.getGeneralDAO().carregar(jogador, idJogador);
		
		return jogador;
	}
	
	public String[][] queryJogadorTodos()
	{
		String[][] dadosJogadores;
		ArrayList alJogador;
		
		alJogador = ctrMain.getGeneralDAO().listaTodos("Jogador");
		dadosJogadores = arrayList2StringMatrix(alJogador);
		
		return dadosJogadores;
	}
	
	public String[][] queryJogadorByNome(String nome)
	{
		String[][] dadosJogador;
		ArrayList<Jogador> alJogador;
		
		alJogador = jDAO.queryJogadorByNome(nome);
		dadosJogador = arrayList2StringMatrix(alJogador);
		
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
