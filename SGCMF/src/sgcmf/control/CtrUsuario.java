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
public class CtrUsuario {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    String[][] dadosUsuarios;

    public Usuario loginUsuario(String login, String senha){

        ArrayList<Usuario> usuarios = usuarioDAO.queryUsuarioByLogin(login, senha);
        if(usuarios.size() < 0){
            return null;
        }
        Usuario u = usuarioDAO.queryUsuarioByLogin(login, senha).get(0);
        return u;
    }

     public String[][] queryJogadorTodos()
    {
       
        ArrayList alUsuario;

        alUsuario = usuarioDAO.listaTodos();
        dadosUsuarios = arrayList2StringMatrix(alUsuario);
        usuarioDAO.fecharSessao();

        return dadosUsuarios;
    }

    public String[][] queryJogadorByNome(String nome)
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

        dadosUsuarios = new String[alUsuario.size()][5];
        for (int i = 0; i < alUsuario.size(); i++)
        {
            u = alUsuario.get(i);
            dadosUsuarios[i][0] = String.valueOf(u.getId());
            dadosUsuarios[i][1] = u.getCpf();
            dadosUsuarios[i][2] = u.getEmail();
            dadosUsuarios[i][3] = u.getNome();
            dadosUsuarios[i][4] = u.getLogin();
            dadosUsuarios[i][4] = u.getSenha();
            dadosUsuarios[i][4] = u.getPerfil();
        }

        return dadosUsuarios;
    }

     public ResultadoOperacao cadastrarUsuario(String cpf, String nome, String email,
            String login, String senha, String perfil)
    {
        Transaction tr;
        Usuario u = new Usuario();
        ResultadoOperacao result;
        String errorMessege;
        GeneralDAO gdao;

        gdao = new GeneralDAO();
        errorMessege = validaCampos('c', cpf, nome, email, login);
        if (errorMessege.equals(""))
        {
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
                result = new ResultadoOperacao("Erro no Hibernate.\n" + he.getMessage(), TipoResultadoOperacao.ERRO);
            }
            gdao.fecharSessao();
        }
        else
        {
            result = new ResultadoOperacao("Falha no cadastro de jogador.\n" + errorMessege, TipoResultadoOperacao.ERRO);
        }
        return result;
    }

    public ResultadoOperacao alterarJogador(String strIdUsuario, String cpf , String nome, String email, String login,
            String senha, String perfil)
    {
        Short shortIdUsuario;
        Usuario u = new Usuario();
        Transaction tr;
        GeneralDAO gdao;
        ResultadoOperacao result;
        String errorMessege;


        shortIdUsuario = new Short(strIdUsuario);
        errorMessege = validaCampos('c', cpf, nome, email, login);
        if (errorMessege.equals(""))
        {
            gdao = new GeneralDAO();
            tr = gdao.getSessao().beginTransaction();
            gdao.carregar(u, shortIdUsuario);
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
                result = new ResultadoOperacao("Erro no Hibernate.\n" + he.getMessage(), TipoResultadoOperacao.ERRO);
            }
            gdao.fecharSessao();
        }
        else
        {
            result = new ResultadoOperacao("Falha na alteracao do usuario.\n" + errorMessege, TipoResultadoOperacao.ERRO);
        }
        return result;
    }


    public ResultadoOperacao removerJogador(String idUsuario)
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

    private String validaCampos(char metodo, String numCamisa, String dataNascimento,
            String altura, String selecao)
    {
        String errorMessege = "";
        Short camisa;
        try
        {
            camisa = Short.parseShort(numCamisa);
            if (camisa < 1 || camisa > 23)
            {
                errorMessege = "O valor da camisa deve estar entre 1 e 23.";
                return errorMessege;
            }
        }
        catch (Exception e)
        {
            errorMessege = "Digite um número valido para camisa.";
        }

        return errorMessege;
    }


}
