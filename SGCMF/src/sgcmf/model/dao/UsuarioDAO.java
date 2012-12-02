package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.hibernate.Usuario;

/**
 *
 * @author Thatiane
 */
public class UsuarioDAO
{
    private static UsuarioDAO instance;

    private UsuarioDAO()
    {
    }

    public static UsuarioDAO getInstance()
    {
        if (instance == null)
        {
            instance = new UsuarioDAO();
        }
        return instance;
    }

     public Usuario carregar(Usuario entidade, Serializable id)
    {
        SGCMFSessionManager.getCurrentSession().load(entidade, id);
        return entidade;
    }
    
    public void salvar(Usuario entidade)
    {
        SGCMFSessionManager.getCurrentSession().save(entidade);
    }
    
    public void atualizar(Usuario entidade)
    {
        SGCMFSessionManager.getCurrentSession().merge(entidade);
    }
    
    public void apagar(Usuario entidade)
    {
        SGCMFSessionManager.getCurrentSession().delete(entidade);
    }
    
    public ArrayList<Usuario> listaTodos()
    {
        return (ArrayList<Usuario>) SGCMFSessionManager.getCurrentSession().createQuery("from Usuario u order by u.perfil").list();
    }

    public int numeroAdmins()
    {
        String hql;

        hql = "select count(u.perfil) from Usuario u where perfil = 'Administrador' ";

        return Integer.parseInt(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult() + "");
    }

    public ArrayList<Usuario> queryUsuarioByNomeUsuario(String nome)
    {
        String hql;

        hql = "from Usuario u "
                + "where lower(u.nome) like lower('%" + nome + "%')";

        return (ArrayList<Usuario>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Usuario> queryUsuarioByLoginUsuario(String login)
    {
        String hql;

        hql = "from Usuario u "
                + "where lower(u.login) like lower('%" + login + "%')";

        return (ArrayList<Usuario>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Usuario> queryUsuarioByPerfilUsuario(String perfil)
    {
        String hql;

        hql = "from Usuario u "
                + "where lower(u.perfil) like lower('%" + perfil + "%')";

        return (ArrayList<Usuario>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Usuario> queryUsuarioByEmailUsuario(String email)
    {
        String hql;

        hql = "from Usuario u "
                + "where lower(u.email) like lower('%" + email + "%')";

        return (ArrayList<Usuario>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Usuario> queryUsuarioByLogin(String login, String senha)
    {
        String hql;

        hql = "from Usuario u "
                + "where login = '" + login + "' and senha = '" + senha + "' ";

        return (ArrayList<Usuario>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Usuario> queryUsuarioOnlyByLogin(String login)
    {
        String hql;

        hql = "from Usuario u "
                + "where login = '" + login + "'";

        return (ArrayList<Usuario>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Usuario> queryUsuarioByNome(String nome)
    {
        String hql;

        hql = "from Usuario u "
                + "where lower(u.nome) like lower('%" + nome + "%') order by u.id";

        return (ArrayList<Usuario>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }

    public ArrayList<Usuario> queryUsuarioByPerfil(String perfil)
    {
        String hql;

        hql = "from Usuario u "
                + "where lower(u.perfil) like lower('%" + perfil + "%') order by u.id";
        return (ArrayList<Usuario>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }
}
