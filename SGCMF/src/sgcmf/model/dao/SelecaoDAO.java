package sgcmf.model.dao;

import java.util.ArrayList;
import sgcmf.model.hibernate.Selecao;

public class SelecaoDAO extends GeneralDAO
{	
	public ArrayList<Selecao> querySelecaoByNomePais(String pais)
	{
		String hql;
		
		hql ="from Selecao s "
				+ "where lower(s.pais) like lower('%" +pais+"%')";
		
		return (ArrayList<Selecao>)sessao.createQuery(hql).list();
	}
	
	public ArrayList<Selecao> querySelecaoByNomeTecnico(String tecnico)
	{
		String hql;
		
		hql = "from Selecao s "
				+ "where lower(s.usuario.nome) like lower('%"+tecnico+"%')";
		
		return (ArrayList<Selecao>)sessao.createQuery(hql).list();
	}
        
        public ArrayList queryIdSelecao(String selecao)
        {
            String hql;
            hql = "select s.id from Selecao s where s.pais = '"+selecao+"'";
            return (ArrayList) sessao.createQuery(hql).list();
        }
}
