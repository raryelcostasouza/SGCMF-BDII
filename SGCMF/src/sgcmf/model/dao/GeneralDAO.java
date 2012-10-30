package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.Session;
import sgcmf.hibernate.SGCMFHibernateUtil;

public class GeneralDAO
{
	Session sessao;

	public GeneralDAO()
	{
		sessao = (Session) SGCMFHibernateUtil.getSessionFactory().openSession();
	}

	public Session getSessao()
	{
		return sessao;
	}

	public void setSessao(Session sessao)
	{
		this.sessao = sessao;
	}

	public void salvar(Object entidade)
	{
		sessao.save(entidade);
	}

	public Object carregar(Object entidade, Serializable id)
	{
		sessao.load(entidade, id);
		return entidade;
	}

	public void apagar(Object entidade)
	{
		sessao.delete(entidade);
	}

	public void atualizar(Object entidade)
	{
		sessao.update(entidade);
	}

	public void fecharSessao()
	{
		sessao.close();
	}

	public ArrayList listaTodos(String tabela)
	{
		 return (ArrayList) sessao.createQuery("from " + tabela).list();
	}
}