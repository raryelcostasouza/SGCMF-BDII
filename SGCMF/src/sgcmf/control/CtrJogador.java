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
        JogadorDAO jDAO;
        String[][] dadosJogadores;
        ArrayList alJogador;

        jDAO = new JogadorDAO();
        alJogador = jDAO.listaTodos();
        dadosJogadores = arrayList2StringMatrix(alJogador);
        jDAO.fecharSessao();

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
        JogadorDAO jDAO;
        String[][] dadosJogadores;
        ArrayList alJogador;

        jDAO = new JogadorDAO();
        alJogador = jDAO.listaTodos();
        dadosJogadores = arrayList2StringMatrixFull(alJogador);
        jDAO.fecharSessao();

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
        String titular;

        dadosJogadores = new String[alJogador.size()][8];
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
            if (j.isTitular())
            {
                titular = "Sim";
            }
            else
            {
                titular = "Não";
            }
            dadosJogadores[i][7] = titular;
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
        ResultadoOperacao result;
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

    public ResultadoOperacao alterarJogador(String idJogador, String numCamisa, String nome, String dtaNascimento,
            String altura, String posicao, String selecao)
    {
        Short nCamisa;
        Date dataNascimento;
        BigDecimal pAltura;
        Short idSelecao;
        Selecao s = new Selecao();
        Jogador j = new Jogador();
        Transaction tr;
        GeneralDAO gdao;
        ResultadoOperacao result;
        String errorMessege;

        errorMessege = validaCampos(numCamisa, dtaNascimento, altura, selecao);
        if (errorMessege.equals(""))
        {
            gdao = new GeneralDAO();
            nCamisa = Short.parseShort(numCamisa);
            dataNascimento = new Date(dtaNascimento);
            pAltura = new BigDecimal(altura);
            idSelecao = Short.parseShort(selecao);
            tr = gdao.getSessao().beginTransaction();
            gdao.carregar(s, idSelecao);
            gdao.carregar(j, new Short(idJogador));
            try
            {
                j.setNcamisa(nCamisa);
                j.setNome(nome);
                j.setDatanasc(dataNascimento);
                j.setAltura(pAltura);
                j.setPosicao(posicao);
                j.setSelecao(s);
                gdao.atualizar(j);
                tr.commit();
                result = new ResultadoOperacao("Jogador Alterado com êxito.", TipoResultadoOperacao.EXITO);
            }
            catch (HibernateException he)
            {
                result = new ResultadoOperacao("Erro no Hibernate.\n" + he.getMessage(), TipoResultadoOperacao.ERRO);
            }
            gdao.fecharSessao();
        }
        else
        {
            result = new ResultadoOperacao("Falha na alteracao do jogador.\n" + errorMessege, TipoResultadoOperacao.ERRO);
        }
        return result;
    }

    public ResultadoOperacao removerJogador(String idJogador)
    {
        Jogador j = new Jogador();
        Transaction tr;
        GeneralDAO gDAO;
        ResultadoOperacao resultado;
        try
        {
            gDAO = new GeneralDAO();
            tr = gDAO.getSessao().beginTransaction();
            gDAO.carregar(j, new Short(idJogador));
            gDAO.apagar(j);
            tr.commit();
            resultado = new ResultadoOperacao("Jogador excluido com sucesso.", TipoResultadoOperacao.EXITO);
        }
        catch (HibernateException he)
        {
            resultado = new ResultadoOperacao("Falha na exclusão do jogador.\n" + he.getMessage(),
                    TipoResultadoOperacao.ERRO);
        }
        return resultado;
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
        if (errorMessege.equals(""))
        {
            Short idSelecao = new Short(selecao);
            int resultado = qtdeTitularesSelecao(idSelecao);
            if (resultado >= 11)
            {
                errorMessege = "É permitido apenas 11 jogadores titulares por seleção.";
            }
        }
        if (errorMessege.equals(""))
        {
            Short idSelecao = new Short(selecao);
            int resultado = qtdeGoleirosSelecao(idSelecao);
            if (resultado >= 3)
            {
                errorMessege = "É permitido apenas 3 Goleiros por seleção.";
            }
        }
        return errorMessege;
    }

    private int qtdeTitularesSelecao(Short idSelecao)
    {
        JogadorDAO jogadorDAO = new JogadorDAO();
        ArrayList array;
        int qtdeTitulares;
        array = jogadorDAO.queryQuantidadeJogadorTitularesSelecao(idSelecao);
        qtdeTitulares = Integer.parseInt(array.get(0).toString());
        jogadorDAO.fecharSessao();
        return qtdeTitulares;
    }

    private int qtdeGoleirosSelecao(Short idSelecao)
    {
        JogadorDAO jogadorDAO = new JogadorDAO();
        ArrayList array;
        int qtdeGoleiros;
        array = jogadorDAO.queryQuantidadeGoleirosSelecao(idSelecao);
        qtdeGoleiros = Integer.parseInt(array.get(0).toString());
        jogadorDAO.fecharSessao();
        return qtdeGoleiros;
    }
}
