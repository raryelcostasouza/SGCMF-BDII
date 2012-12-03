package sgcmf.control;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.UsuarioDAO;
import sgcmf.model.hibernate.Usuario;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;

/**
 *
 * @author Thatiane
 */
public class CtrUsuario
{
    String[][] dadosUsuarios;

    public Usuario loginUsuario(String login, String senha)
    {
        SGCMFSessionManager.abrirSessao();
        ArrayList<Usuario> usuarios = UsuarioDAO.getInstance().queryUsuarioByLogin(login, senha);
        
        if (usuarios.size() <= 0)
        {
            return null;
        }
        Usuario u = usuarios.get(0);
        SGCMFSessionManager.fecharSessao();
        
        return u;
    }

    public String[][] queryUsuarioTodos()
    {

        ArrayList alUsuario;
        SGCMFSessionManager.abrirSessao();
        alUsuario = UsuarioDAO.getInstance().listaTodos();
        dadosUsuarios = arrayList2StringMatrix(alUsuario);
        SGCMFSessionManager.fecharSessao();

        return dadosUsuarios;
    }

    public String[][] queryUsuarioByNome(String nome)
    {

        ArrayList<Usuario> alUsuario;
        SGCMFSessionManager.abrirSessao();
        alUsuario = UsuarioDAO.getInstance().queryUsuarioByNome(nome);
        dadosUsuarios = arrayList2StringMatrix(alUsuario);
        SGCMFSessionManager.fecharSessao();

        return dadosUsuarios;
    }

    public Object[][] queryUsuarioByNomeUsuario(String nome)
    {
        UsuarioDAO sDAO;
        ArrayList<Usuario> alUsuario;
        Object[][] dadosUsuario;

        SGCMFSessionManager.abrirSessao();
        sDAO = UsuarioDAO.getInstance();

        alUsuario = sDAO.queryUsuarioByNomeUsuario(nome);
        dadosUsuario = arrayList2StringMatrix(alUsuario);

        SGCMFSessionManager.fecharSessao();

        return dadosUsuario;
    }

    public Object[][] queryUsuarioByPerfilUsuario(String perfil)
    {
        UsuarioDAO sDAO;
        ArrayList<Usuario> alUsuario;
        Object[][] dadosUsuario;

        SGCMFSessionManager.abrirSessao();
        sDAO = UsuarioDAO.getInstance();

        alUsuario = sDAO.queryUsuarioByPerfilUsuario(perfil);
        dadosUsuario = arrayList2StringMatrix(alUsuario);

        SGCMFSessionManager.fecharSessao();

        return dadosUsuario;
    }

    public Object[][] queryUsuarioByLoginUsuario(String login)
    {
        UsuarioDAO sDAO;
        ArrayList<Usuario> alUsuario;
        Object[][] dadosUsuario;

        SGCMFSessionManager.abrirSessao();
        sDAO = UsuarioDAO.getInstance();

        alUsuario = sDAO.queryUsuarioByLoginUsuario(login);
        dadosUsuario = arrayList2StringMatrix(alUsuario);

        SGCMFSessionManager.fecharSessao();

        return dadosUsuario;
    }

    public Object[][] queryUsuarioByEmailUsuario(String email)
    {
        UsuarioDAO sDAO;
        ArrayList<Usuario> alUsuario;
        Object[][] dadosUsuario;

        SGCMFSessionManager.abrirSessao();
        sDAO = UsuarioDAO.getInstance();

        alUsuario = sDAO.queryUsuarioByEmailUsuario(email);
        dadosUsuario = arrayList2StringMatrix(alUsuario);

        SGCMFSessionManager.fecharSessao();

        return dadosUsuario;
    }

    private String[][] arrayList2StringMatrix(ArrayList<Usuario> alUsuario)
    {
        Usuario u;

        dadosUsuarios = new String[alUsuario.size()][7];
        for (int i = 0; i < alUsuario.size(); i++)
        {
            u = alUsuario.get(i);
            dadosUsuarios[i][0] = String.valueOf(u.getId());
            dadosUsuarios[i][1] = u.getCpf();
            dadosUsuarios[i][2] = u.getEmail();
            dadosUsuarios[i][3] = u.getNome();
            dadosUsuarios[i][4] = u.getLogin();
            dadosUsuarios[i][5] = u.getSenha();
            dadosUsuarios[i][6] = u.getPerfil();
        }

        return dadosUsuarios;
    }

    public ResultadoOperacao cadastrarUsuario(String cpf, String nome, String email,
            String login, String senha, String perfil)
    {
        Transaction tr;
        Usuario u = new Usuario();
        ResultadoOperacao result;
        
        SGCMFSessionManager.abrirSessao();

        if(UsuarioDAO.getInstance().queryUsuarioOnlyByLogin(login).size() > 0)
        {
            SGCMFSessionManager.fecharSessao();
            return (new ResultadoOperacao("Login já cadastrado.\n", TipoResultadoOperacao.ERRO));
        }
                if(UsuarioDAO.getInstance().queryUsuarioOnlyByEmail(email).size() > 0)
        {
            SGCMFSessionManager.fecharSessao();
            return (new ResultadoOperacao("Email já cadastrado.\n", TipoResultadoOperacao.ERRO));
        }
                if(UsuarioDAO.getInstance().queryUsuarioOnlyByCPF(cpf).size() > 0)
        {
            SGCMFSessionManager.fecharSessao();
            return (new ResultadoOperacao("CPF já cadastrado.\n", TipoResultadoOperacao.ERRO));
        }

        tr = SGCMFSessionManager.getCurrentSession().beginTransaction();
        try
        {
            u.setCpf(cpf);
            u.setNome(nome);
            u.setEmail(email);
            u.setLogin(login);
            u.setPerfil(perfil);
            u.setSenha(senha);
            UsuarioDAO.getInstance().salvar(u);
            tr.commit();
            
            result = new ResultadoOperacao("Usuario Cadastrado com êxito.", TipoResultadoOperacao.EXITO);
        }
        catch (HibernateException he)
        {
            result = new ResultadoOperacao("Erro no cadastro do usuário.\n" + he.getMessage(), TipoResultadoOperacao.ERRO);
        }
        SGCMFSessionManager.fecharSessao();

        return result;
    }

    public ResultadoOperacao alterarUsuario(String stringIdUsuario, String cpf, String nome, String email, String login,
            String senha, String perfil)
    {
        Usuario u = new Usuario();
        Transaction tr;
        ResultadoOperacao result;
        
        SGCMFSessionManager.abrirSessao();

        if(UsuarioDAO.getInstance().queryUsuarioOnlyByLogin(login).size() > 0)
        {
            SGCMFSessionManager.fecharSessao();
            return (new ResultadoOperacao("Login já cadastrado.\n", TipoResultadoOperacao.ERRO));
        }

        Short shortIdUsuario = new Short(stringIdUsuario);

        tr = SGCMFSessionManager.getCurrentSession().beginTransaction();

        UsuarioDAO.getInstance().carregar(u, shortIdUsuario);

        if(u.getPerfil().equals("Administrador") && UsuarioDAO.getInstance().numeroAdmins() <= 1 && !perfil.equals("Administrador"))
        {
            tr.rollback();
            SGCMFSessionManager.fecharSessao();
            result = new ResultadoOperacao("Um único administrador não pode ter seu perfil alterado.\n",
                    TipoResultadoOperacao.ERRO);
            return result;
        }

        try
        {
            u.setCpf(cpf);
            u.setNome(nome);
            u.setEmail(email);
            u.setLogin(login);
            u.setPerfil(perfil);
            u.setSenha(senha);
            UsuarioDAO.getInstance().atualizar(u);
            tr.commit();
            result = new ResultadoOperacao("Usuario Alterado com êxito.", TipoResultadoOperacao.EXITO);
        }
        catch (HibernateException he)
        {
            result = new ResultadoOperacao("Falha na alteracao do usuario.\n" + he.getMessage(), TipoResultadoOperacao.ERRO);
        }
        SGCMFSessionManager.fecharSessao();

        return result;
    }

    public ResultadoOperacao removerUsuario(String stringIdUsuario)
    {
        Usuario u = new Usuario();
        Transaction tr;
        ResultadoOperacao resultado;
        Short shortIdUsuario = new Short(stringIdUsuario);

        SGCMFSessionManager.abrirSessao();

        try
        {
            tr = SGCMFSessionManager.getCurrentSession().beginTransaction();
            UsuarioDAO.getInstance().carregar(u, new Short(shortIdUsuario));

            if(u.getPerfil().equals("Administrador") && UsuarioDAO.getInstance().numeroAdmins() <= 1)
            {
                SGCMFSessionManager.fecharSessao();
                resultado = new ResultadoOperacao("Um único administrador não pode ser excluido.\n",
                        TipoResultadoOperacao.ERRO);
                return resultado;
            }

            UsuarioDAO.getInstance().apagar(u);
            tr.commit();
            resultado = new ResultadoOperacao("Usuario excluido com sucesso.", TipoResultadoOperacao.EXITO);
        }
        catch (HibernateException he)
        {
            resultado = new ResultadoOperacao("Falha na exclusão do usuario.\n" + he.getMessage(),
                    TipoResultadoOperacao.ERRO);
        }
        SGCMFSessionManager.fecharSessao();
        
        return resultado;
    }
}
