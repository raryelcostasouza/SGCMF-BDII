package sgcmf.control;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.GolDAO;
import sgcmf.model.hibernate.Gol;
import sgcmf.model.hibernate.Jogador;
import sgcmf.model.hibernate.Ocorrencia;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;

public class CtrGol
{
    private CtrMain ctrMain;

    public CtrGol(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
    }

    public ResultadoOperacao registraGol(String min, String seg, Short idJogo, String idJogadorAutor, String idJogadorAssist,
                                         String tipoGol, String modoGol)
    {
        GeneralDAO gdao;
        Short shortIdJogadorAutor;
        Short shortIdJogadorAssist;
        Transaction tr;
        Gol g;
        Ocorrencia o;
        Jogador jAutor;
        Jogador jAssist;
        String errorMessage;
        ResultadoOperacao result;

        errorMessage = validaCamposGol(min, seg, idJogadorAutor);

        //se nao tiver erros nos campos, entao faz o cadastro
        if (errorMessage.equals(""))
        {
            gdao = new GeneralDAO();
            tr = gdao.getSessao().beginTransaction();
            try
            {
                o = ctrMain.getCtrOcorrenciaJogo().registraOcorrencia(gdao, min, seg, idJogo);

                //carrega o jogador autor
                shortIdJogadorAutor = Short.parseShort(idJogadorAutor);
                jAutor = ctrMain.getCtrJogador().carregaJogadorById(shortIdJogadorAutor);

                //se tiver jogador assistente, ele eh carregado
                if (!idJogadorAssist.equals(""))
                {
                    shortIdJogadorAssist = Short.parseShort(idJogadorAssist);
                    jAssist = ctrMain.getCtrJogador().carregaJogadorById(shortIdJogadorAssist);
                    g = new Gol(o.getId(), jAutor, o, jAssist, tipoGol, modoGol);
                }
                else
                {
                    g = new Gol(o.getId(), jAutor, o, tipoGol, modoGol);
                }

                //salva o gol e commita
                gdao.salvar(g);
                tr.commit();

                result = new ResultadoOperacao("Gol cadastrado com êxito!", TipoResultadoOperacao.EXITO);
            }
            catch (HibernateException hex)
            {
                tr.rollback();
                result = new ResultadoOperacao("Erro do Hibernate:\n" + hex.getMessage(), TipoResultadoOperacao.ERRO);
            }
            gdao.fecharSessao();
        }
        else
        {
            result = new ResultadoOperacao(errorMessage, TipoResultadoOperacao.ERRO);
        }

        return result;
    }

    private String validaCamposGol(String min, String seg, String idJogadorAutor)
    {
        String errorMessage;

        errorMessage = ctrMain.getCtrOcorrenciaJogo().validaCampos(min, seg);

        //so faz o outro teste se passou no primeiro teste
        if (errorMessage.equals(""))
        {
            try
            {
                Short.parseShort(idJogadorAutor);
            }
            catch (NumberFormatException nfe)
            {
                errorMessage = "Jogador autor: é obrigatório selecionar o jogador autor.";
            }
        }

        return errorMessage;
    }

    public ResultadoOperacao removeGol(Short idOc)
    {
        ResultadoOperacao result;
        Transaction tr;
        GeneralDAO gdao;
        Gol golParaRemover;
        Ocorrencia ocParaRemover;

        golParaRemover = new Gol();
        ocParaRemover = new Ocorrencia();

        gdao = new GeneralDAO();
        tr = gdao.getSessao().beginTransaction();

        try
        {
            golParaRemover = (Gol) gdao.carregar(golParaRemover, idOc);
            ocParaRemover = (Ocorrencia) gdao.carregar(ocParaRemover, idOc);

            gdao.apagar(golParaRemover);
            gdao.apagar(ocParaRemover);

            tr.commit();
            result = new ResultadoOperacao("Gol removido com êxito!", TipoResultadoOperacao.EXITO);
        }
        catch (HibernateException hex)
        {
            tr.rollback();
            result = new ResultadoOperacao("Erro do Hibernate:\n" + hex.getMessage(), TipoResultadoOperacao.ERRO);
        }
        gdao.fecharSessao();

        return result;
    }

    public String[][] queryGolByIdJogo(Short idJogo)
    {
        ArrayList<Gol> alGol;
        String[][] dadosGol;
        GolDAO golDAO;

        golDAO = new GolDAO();
        alGol = golDAO.queryGolByIdJogo(idJogo);
        dadosGol = arrayList2StringMatrix(alGol);
        golDAO.fecharSessao();

        return dadosGol;
    }

    public String[][] arrayList2StringMatrix(ArrayList<Gol> alGol)
    {
        String[][] dadosGol;
        Gol g;

        dadosGol = new String[alGol.size()][6];
        for (int i = 0; i < alGol.size(); i++)
        {
            g = alGol.get(i);
            dadosGol[i][0] = String.valueOf(g.getIdoc());
            dadosGol[i][1] = String.valueOf(g.getOcorrencia().getInstantetempo());
            dadosGol[i][2] = g.getJogadorByIdjogadorautor().getNome();

            if (g.getJogadorByIdjogadorassistencia() != null)
            {
                dadosGol[i][3] = g.getJogadorByIdjogadorassistencia().getNome();
            }
            else
            {
                dadosGol[i][3] = "";
            }

            dadosGol[i][4] = g.getTipo();
            dadosGol[i][5] = g.getModo();
        }

        return dadosGol;
    }
}
