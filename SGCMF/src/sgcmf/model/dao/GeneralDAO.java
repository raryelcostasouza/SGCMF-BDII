package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.Session;
import sgcmf.hibernate.SGCMFHibernateUtil;

public class GeneralDAO<T>
{
	protected Session sessao;

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

	public void salvar(T entidade)
	{
		sessao.save(entidade);
	}

	public T carregar(T entidade, Serializable id)
	{
		sessao.load(entidade, id);
		return entidade;
	}

	public void apagar(T entidade)
	{
		sessao.delete(entidade);
	}

	public void atualizar(T entidade)
	{
		sessao.update(entidade);
	}

	public void fecharSessao()
	{
		sessao.close();
	}

	public ArrayList<T> listaTodos(String tabela)
	{
		 return (ArrayList<T>) sessao.createQuery("from " + tabela).list();
	}
}