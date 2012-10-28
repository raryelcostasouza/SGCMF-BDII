package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Jogo;

public class JogoDAO extends GeneralDAO<Jogo>
{
	public ArrayList<Jogo> queryJogoBySelecao(String selecao)
	{
		String hql;
		
		hql = "from Jogo j "
				+ "where lower(j.selecaoByIdselecaoi.pais) like lower('%" +selecao+"%') or "
				+ "lower(j.selecaoByIdselecaoii.pais) like lower('%" + selecao+"%')";
		
		return (ArrayList<Jogo>)sessao.createQuery(hql).list();
	}
	
	public ArrayList<Jogo> queryJogoByCidade(String cidade)
	{
		String hql;
		
		hql = "from Jogo j "
				+ "where lower(j.cidade) like lower('%" +cidade+"%')";
		
		return (ArrayList<Jogo>)sessao.createQuery(hql).list();
	}

	public ArrayList<Jogo> queryJogoByEstadio(String estadio)
	{
		String hql;
		
		hql = "from Jogo j "
				+ "where lower(j.nomeestadio) like lower('%" +estadio+"%')";
		
		return (ArrayList<Jogo>)sessao.createQuery(hql).list();
	}

	public ArrayList<Jogo> queryJogoByTipo(String tipo)
	{
		String hql;
		
		hql = "from Jogo j "
				+ "where j.tipo = '" +tipo+"'";
		
		return (ArrayList<Jogo>)sessao.createQuery(hql).list();
	}
	
	public Jogo queryInfoJogoById(Short idJogo)
	{
		String hql;
		hql = "from Jogo j "
				+ "where j.id = " + idJogo;
		
		return (Jogo) sessao.createQuery(hql).uniqueResult();
	}
			
}
