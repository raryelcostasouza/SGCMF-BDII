package sgcmf.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.JogadorDAO;
import sgcmf.model.hibernate.Jogador;
import sgcmf.model.hibernate.Selecao;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;

public class CtrJogador
{
    public Jogador carregaJogadorById(Short idJogador)
    {
        Jogador jogador;
        JogadorDAO jdao;

        jogador = new Jogador();
        jdao = new JogadorDAO();
        jdao.carregar(jogador, idJogador);
        jdao.fecharSessao();

        return jogador;
    }

    public String[][] queryJogadorTodos()
    {
        GeneralDAO<Jogador> gdao;
        String[][] dadosJogadores;
        ArrayList alJogador;

        gdao = new GeneralDAO<Jogador>();
        alJogador = gdao.listaTodos("Jogador");
        dadosJogadores = arrayList2StringMatrix(alJogador);
        gdao.fecharSessao();

        return dadosJogadores;
    }

    public String[][] queryJogadorByNome(String nome)
    {
        JogadorDAO jDAO;
        String[][] dadosJogador;
        ArrayList<Jogador> alJogador;

        jDAO = new JogadorDAO();
        alJogador = jDAO.queryJogadorByNome(nome);
        dadosJogador = arrayList2StringMatrix(alJogador);
        jDAO.fecharSessao();

        return dadosJogador;
    }
    
    public String[][] queryAllDataJogadorTodos()
    {
        GeneralDAO<Jogador> gdao;
        String[][] dadosJogadores;
        ArrayList alJogador;

        gdao = new GeneralDAO<Jogador>();
        alJogador = gdao.listaTodos("Jogador");
        dadosJogadores = arrayList2StringMatrixFull(alJogador);
        gdao.fecharSessao();

        return dadosJogadores;
    }
    
    public String[][] queryAllDataJogadorByNome(String nome)
    {
        JogadorDAO jDAO;
        String[][] dadosJogador;
        ArrayList<Jogador> alJogador;

        jDAO = new JogadorDAO();
        alJogador = jDAO.queryJogadorByNome(nome);
        dadosJogador = arrayList2StringMatrixFull(alJogador);
        jDAO.fecharSessao();

        return dadosJogador;
    }

    public String[][] queryAllDataJogadorByPosicao(String posicao)
    {
        JogadorDAO jDAO;
        String[][] dadosJogador;
        ArrayList<Jogador> alJogador;
        
        jDAO = new JogadorDAO();
        alJogador = jDAO.queryJogadorByPosicao(posicao);
        dadosJogador = arrayList2StringMatrixFull(alJogador);
        jDAO.fecharSessao();
        
        return dadosJogador;
    }

    private String[][] arrayList2StringMatrix(ArrayList<Jogador> alJogador)
    {
        String[][] dadosJogadores;
        Jogador j;

        dadosJogadores = new String[alJogador.size()][5];
        for (int i = 0; i < alJogador.size(); i++)
        {
            j = alJogador.get(i);
            dadosJogadores[i][0] = String.valueOf(j.getId());
            dadosJogadores[i][1] = j.getSelecao().getPais();
            dadosJogadores[i][2] = String.valueOf(j.getNcamisa());
            dadosJogadores[i][3] = j.getNome();
            dadosJogadores[i][4] = j.getPosicao();
        }

        return dadosJogadores;
    }
    
    private String[][] arrayList2StringMatrixFull(ArrayList<Jogador> alJogador)
    {
        String[][] dadosJogadores;
        Jogador j;

        dadosJogadores = new String[alJogador.size()][7];
        for (int i = 0; i < alJogador.size(); i++)
        {
            j = alJogador.get(i);
            dadosJogadores[i][0] = String.valueOf(j.getId());
            dadosJogadores[i][1] = String.valueOf(j.getNcamisa());
            dadosJogadores[i][2] = j.getNome();
            dadosJogadores[i][3] = String.valueOf(j.getDatanasc());
            dadosJogadores[i][4] = String.valueOf(j.getAltura());
            dadosJogadores[i][5] = j.getPosicao();
            dadosJogadores[i][6] = j.getSelecao().getPais();
        }

        return dadosJogadores;
    }

    public ResultadoOperacao cadastrarJogador(String numCamisa, String nome, String dataNascimento,
            String altura, boolean titular, String posicao, String selecao)
    {
        Short nCamisa;
        Date dtaNascimento;
        BigDecimal aAltura;
        Short aSelecao;
        Transaction tr;
        Selecao s = new Selecao();
        Jogador j = new Jogador();
        ResultadoOperacao result = null;
        String errorMessege;
        GeneralDAO gdao;

        gdao = new GeneralDAO();
        errorMessege = validaCampos(numCamisa, dataNascimento, altura, selecao);
        if (errorMessege.equals(""))
        {
            nCamisa = Short.parseShort(numCamisa);
            dtaNascimento = new Date(dataNascimento);
            aAltura = new BigDecimal(altura);
            aSelecao = Short.parseShort(selecao);
            tr = gdao.getSessao().beginTransaction();
            gdao.carregar(s, aSelecao);
            try
            {
                j.setNcamisa(nCamisa);
                j.setNome(nome);
                j.setDatanasc(dtaNascimento);
                j.setAltura(aAltura);
                j.setTitular(titular);
                j.setPosicao(posicao);
                j.setSelecao(s);
                gdao.salvar(j);
                tr.commit();
                result = new ResultadoOperacao("Jogador Cadastrado com êxito.", TipoResultadoOperacao.EXITO);
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

    public String validaCampos(String numCamisa, String dataNascimento, String altura, String selecao)
    {
        String errorMessege = "";
        Short camisa;
        Date date;
        BigDecimal aAltura;
        //Verificar se o numero da camisa esta valido.
        try
        {
            camisa = Short.parseShort(numCamisa);
            if (camisa < 1 || camisa > 24)
            {
                errorMessege = "O valor da camisa deve estar entre 1 e 23.";
                return errorMessege;
            }
        }
        catch (Exception e)
        {
            errorMessege = "Digite um número valido para camisa.";
        }
        if (errorMessege.equals(""))
        {
            try
            {
                date = new Date(dataNascimento);
            }
            catch (IllegalArgumentException ilae)
            {
                errorMessege = "Formato de data é inválido. Deve seguir o padrão AAAA/MM/DD.";
            }
        }
        if (errorMessege.equals(""))
        {
            try
            {
                aAltura = new BigDecimal(altura);
            }
            catch (NumberFormatException nfe)
            {
                errorMessege = "Digite um número valido para altura, usando \".\""
                        + " para separar o metro de centímetro.";
            }
        }
        if (errorMessege.equals(""))
        {
            if (selecao.equals(""))
            {
                errorMessege = "O campo de seleção é obrigatório.";
            }
        }
        return errorMessege;
    }
}
