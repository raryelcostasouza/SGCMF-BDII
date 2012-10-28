package sgcmf.control;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Transaction;
import sgcmf.model.Gol;
import sgcmf.model.Jogador;
import sgcmf.model.Jogo;
import sgcmf.model.Ocorrencia;
import sgcmf.model.dao.GeneralDAO;

public class CtrOcorrenciaJogo
{
	public void registraGol(String min, String seg, Short idJogo, String idJogadorAutor, String idJogadorAssist,
							String tipoGol, String modoGol)
	{
		Time tempo;
		GeneralDAO gdao;
		Transaction tr;
		Gol g;
		Ocorrencia o;	
		Jogo j;
		Jogador jAutor;
		Short shortIdJogadorAutor;
		
		o = new Ocorrencia();
		j = new Jogo();
		
		gdao = new GeneralDAO();
		j = (Jogo) gdao.carregar(j, idJogo);
		
		tempo = new Time(0, Integer.parseInt(min), Integer.parseInt(seg));
		o.setInstantetempo(tempo);
		o.setJogo(j);
		gdao.salvar(o);
		
		jAutor = new Jogador();
		shortIdJogadorAutor = Short.parseShort(idJogadorAutor);
		jAutor = (Jogador) gdao.carregar(jAutor, shortIdJogadorAutor); 
		g = new Gol(o.getId(), jAutor, o, tipoGol, modoGol);
		
		gdao.salvar(g);
		
		tr = gdao.getSessao().beginTransaction();
		tr.commit();
	
		gdao.fecharSessao();		
	}
	
	public void removeGol(Short idOc)
	{
		GeneralDAO gdao;
		Transaction tr;
		
		Gol golParaRemover;
		Ocorrencia ocParaRemover;
		
		golParaRemover = new Gol();
		ocParaRemover = new Ocorrencia();
		
		gdao= new GeneralDAO();
		
		golParaRemover = (Gol) gdao.carregar(golParaRemover, idOc);
		ocParaRemover = (Ocorrencia) gdao.carregar(ocParaRemover, idOc);
		
		gdao.apagar(golParaRemover);
		gdao.apagar(ocParaRemover);
		
		tr = gdao.getSessao().beginTransaction();
		tr.commit();
		
		gdao.fecharSessao();
	}
	
	public String[][] queryGolTodos()
	{
		GeneralDAO<Gol> gdao;
		ArrayList<Gol> alGol;
		String[][] dadosGol;
		
		gdao = new GeneralDAO<Gol>();
		alGol = gdao.listaTodos("Gol");
		dadosGol = arrayList2StringMatrix(alGol);
		
		gdao.fecharSessao();
		return dadosGol;
	}
	
	public String[][] arrayList2StringMatrix(ArrayList<Gol> alGol)
	{
		String[][] dadosGol;
		Gol g;
		
		dadosGol = new String[alGol.size()][6];
		for (int i = 0; i < alGol.size(); i++)
		{
			g = alGol.get(i);
			dadosGol[i][0] = String.valueOf(g.getIdoc());
			dadosGol[i][1] = String.valueOf(g.getOcorrencia().getInstantetempo());
			dadosGol[i][2] = g.getJogadorByIdjogadorautor().getNome();
			
			if (g.getJogadorByIdjogadorassistencia() != null)
			{
				dadosGol[i][3] = g.getJogadorByIdjogadorassistencia().getNome();
			}
			else
			{
				dadosGol[i][3] = "";
			}
			
			dadosGol[i][4] = g.getTipo();
			dadosGol[i][5] = g.getModo();
		}
		
		return dadosGol;
	}
}
