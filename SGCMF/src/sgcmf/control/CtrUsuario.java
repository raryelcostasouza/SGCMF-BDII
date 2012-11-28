package sgcmf.control;

import java.util.ArrayList;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import sgcmf.model.dao.GeneralDAO;
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
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    String[][] dadosUsuarios;

    public Usuario loginUsuario(String login, String senha)
    {

        ArrayList<Usuario> usuarios = usuarioDAO.queryUsuarioByLogin(login, senha);
        
        if (usuarios.size() <= 0)
        {
            return null;
        }
        Usuario u = usuarioDAO.queryUsuarioByLogin(login, senha).get(0);

        return u;
    }

    public String[][] queryUsuarioTodos()
    {

        ArrayList alUsuario;

        alUsuario = usuarioDAO.listaTodos();
        dadosUsuarios = arrayList2StringMatrix(alUsuario);
        usuarioDAO.fecharSessao();

        return dadosUsuarios;
    }

    public String[][] queryUsuarioByNome(String nome)
    {

        ArrayList<Usuario> alUsuario;

        alUsuario = usuarioDAO.queryUsuarioByNome(nome);
        dadosUsuarios = arrayList2StringMatrix(alUsuario);
        usuarioDAO.fecharSessao();

        return dadosUsuarios;
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
        GeneralDAO gdao;
        
        if(usuarioDAO.queryUsuarioOnlyByLogin(login).size() > 0)
        {
            return (new ResultadoOperacao("Login já cadastrado.\n", TipoResultadoOperacao.ERRO));
        }

        gdao = new GeneralDAO();

        tr = gdao.getSessao().beginTransaction();
        try
        {
            u.setCpf(cpf);
            u.setNome(nome);
            u.setEmail(email);
            u.setLogin(login);
            u.setPerfil(perfil);
            u.setSenha(senha);
            gdao.salvar(u);
            tr.commit();
            
            result = new ResultadoOperacao("Usuario Cadastrado com êxito.", TipoResultadoOperacao.EXITO);
        }
        catch (HibernateException he)
        {
            result = new ResultadoOperacao("Erro no cadastro do usuário.\n" + he.getMessage(), TipoResultadoOperacao.ERRO);
        }
        gdao.fecharSessao();

        return result;
    }

    public ResultadoOperacao alterarUsuario(String strIdUsuario, String cpf, String nome, String email, String login,
            String senha, String perfil)
    {
        Short shortIdUsuario;
        Usuario u = new Usuario();
        Transaction tr;
        GeneralDAO gdao;
        ResultadoOperacao result;

        shortIdUsuario = new Short(strIdUsuario);

        gdao = new GeneralDAO();
        tr = gdao.getSessao().beginTransaction();
        gdao.carregar(u, shortIdUsuario);

        if(u.getPerfil().equals("Administrador") && usuarioDAO.numeroAdmins() <= 1 && !perfil.equals("Administrador"))
        {
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
            gdao.atualizar(u);
            tr.commit();
            result = new ResultadoOperacao("Usuario Alterado com êxito.", TipoResultadoOperacao.EXITO);
        }
        catch (HibernateException he)
        {
            result = new ResultadoOperacao("Falha na alteracao do usuario.\n" + he.getMessage(), TipoResultadoOperacao.ERRO);
        }
        gdao.fecharSessao();

        return result;
    }

    public ResultadoOperacao removerUsuario(String idUsuario)
    {
        Usuario u = new Usuario();
        Transaction tr;
        GeneralDAO gDAO;
        ResultadoOperacao resultado;

        try
        {
            gDAO = new GeneralDAO();
            tr = gDAO.getSessao().beginTransaction();
            gDAO.carregar(u, new Short(idUsuario));

            if(u.getPerfil().equals("Administrador") && usuarioDAO.numeroAdmins() <= 1)
            {
                resultado = new ResultadoOperacao("Um único administrador não pode ser excluido.\n",
                        TipoResultadoOperacao.ERRO);
                return resultado;
            }

            gDAO.apagar(u);
            tr.commit();
            resultado = new ResultadoOperacao("Usuario excluido com sucesso.", TipoResultadoOperacao.EXITO);
        }
        catch (HibernateException he)
        {
            resultado = new ResultadoOperacao("Falha na exclusão do usuario.\n" + he.getMessage(),
                    TipoResultadoOperacao.ERRO);
        }
        return resultado;
    }
}
