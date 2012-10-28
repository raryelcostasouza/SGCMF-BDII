package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Jogador;

public class JogadorDAO extends GeneralDAO<Jogador>
{
	public ArrayList<Jogador> queryJogadorByNome(String nome)
	{
		String hql;
		
		hql = "from Jogador j "
				+ "where lower(j.nome) like lower('%" + nome + "%')";
		
		return (ArrayList<Jogador>) sessao.createQuery(hql).list();
	}
}
