package sgcmf.control;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.GolDAO2;
import sgcmf.model.dao.JogadorDAO2;
import sgcmf.model.dao.OcorrenciaDAO2;
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
    
    public String[][] queryGolByIdJogo(Short idJogo)
    {
        ArrayList<Gol> alGol;
        String[][] dadosGol;

        SGCMFSessionManager.openSession();
        alGol = GolDAO2.getInstance().queryGolByIdJogo(idJogo);
        dadosGol = arrayList2StringMatrix(alGol);
        SGCMFSessionManager.closeSession();

        return dadosGol;
    }

    public ResultadoOperacao registraGol(String min, String seg, Short idJogo, String idJogadorAutor, String idJogadorAssist,
                                         String tipoGol, String modoGol)
    {
        Short shortIdJogadorAutor;
        Short shortIdJogadorAssist;
        Transaction tr;
        Gol g;
        Ocorrencia o;
        Jogador jAutor;
        Jogador jAssist;
        String errorMessage;
        ResultadoOperacao result;

        errorMessage = validaCamposGol(min, seg, idJogadorAutor, idJogo);

        //se nao tiver erros nos campos, entao faz o cadastro
        if (errorMessage.equals(""))
        {
            SGCMFSessionManager.openSession();
            tr = SGCMFSessionManager.getCurrentSession().beginTransaction();
            try
            {
                o = ctrMain.getCtrOcorrenciaJogo().registraOcorrencia(min, seg, idJogo);

                //carrega o jogador autor
                shortIdJogadorAutor = Short.parseShort(idJogadorAutor);
                jAutor = new Jogador();
                JogadorDAO2.getInstance().carregar(jAutor, shortIdJogadorAutor);

                //se tiver jogador assistente, ele eh carregado
                if (!idJogadorAssist.equals(""))
                {
                    shortIdJogadorAssist = Short.parseShort(idJogadorAssist);
                    jAssist = new Jogador();
                    jAssist = JogadorDAO2.getInstance().carregar(jAssist, shortIdJogadorAssist);
                    g = new Gol(o.getId(), jAutor, o, jAssist, tipoGol, modoGol);
                }
                else
                {
                    g = new Gol(o.getId(), jAutor, o, tipoGol, modoGol);
                }

                //salva o gol e commita
                GolDAO2.getInstance().salvar(g);
                tr.commit();

                result = new ResultadoOperacao("Gol cadastrado com êxito!", TipoResultadoOperacao.EXITO);
            }
            catch (HibernateException hex)
            {
                tr.rollback();
                result = new ResultadoOperacao("Erro do Hibernate:\n" + hex.getMessage(), TipoResultadoOperacao.ERRO);
            }
            SGCMFSessionManager.closeSession();
        }
        else
        {
            result = new ResultadoOperacao(errorMessage, TipoResultadoOperacao.ERRO);
        }

        return result;
    }

    private String validaCamposGol(String min, String seg, String idJogadorAutor, Short idJogo)
    {
        String errorMessage;

        errorMessage = ctrMain.getCtrOcorrenciaJogo().validaCampos(min, seg, idJogo);

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
        String errorMessage;

        golParaRemover = new Gol();
        ocParaRemover = new Ocorrencia();

        SGCMFSessionManager.openSession();
        tr = SGCMFSessionManager.getCurrentSession().beginTransaction();

        try
        {
            errorMessage = ctrMain.getCtrOcorrenciaJogo().validaRemocao(ocParaRemover, idOc);
            //se a remocao for possivel de acordo com as regras de negocio
            if (errorMessage.equals(""))
            {
                golParaRemover = GolDAO2.getInstance().carregar(golParaRemover, idOc);

                GolDAO2.getInstance().apagar(golParaRemover);
                OcorrenciaDAO2.getInstance().apagar(ocParaRemover);

                tr.commit();
                result = new ResultadoOperacao("Gol removido com êxito!", TipoResultadoOperacao.EXITO);
            }
            else
            {
                result = new ResultadoOperacao(errorMessage, TipoResultadoOperacao.ERRO);
            }
        }
        catch (HibernateException hex)
        {
            tr.rollback();
            result = new ResultadoOperacao("Erro do Hibernate:\n" + hex.getMessage(), TipoResultadoOperacao.ERRO);
        }
        SGCMFSessionManager.closeSession();;

        return result;
    }    

    public String[][] arrayList2StringMatrix(ArrayList<Gol> alGol)
    {
        String[][] dadosGol;
        Gol g;

        dadosGol = new String[alGol.size()][7];
        for (int i = 0; i < alGol.size(); i++)
        {
            g = alGol.get(i);
            dadosGol[i][0] = String.valueOf(g.getIdoc());
            dadosGol[i][1] = String.valueOf(g.getOcorrencia().getInstantetempo());
            dadosGol[i][2] = g.getJogadorByIdjogadorautor().getSelecao().getPais();
            dadosGol[i][3] = g.getJogadorByIdjogadorautor().getNome();

            if (g.getJogadorByIdjogadorassistencia() != null)
            {
                dadosGol[i][4] = g.getJogadorByIdjogadorassistencia().getNome();
            }
            else
            {
                dadosGol[i][4] = "";
            }

            dadosGol[i][5] = g.getTipo();
            dadosGol[i][6] = g.getModo();
        }

        return dadosGol;
    }
}
