package sgcmf.control;

import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import sgcmf.model.dao.CartaoDAO;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.hibernate.Cartao;
import sgcmf.model.hibernate.Falta;
import sgcmf.model.hibernate.Jogador;
import sgcmf.model.hibernate.Ocorrencia;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;

public class CtrCartao
{
    private CtrMain ctrMain;

    public CtrCartao(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
    }

    public String[][] queryCartaoByIdJogo(Short idJogo)
    {
        ArrayList<Cartao> alCartao;
        String[][] dadosCartao;
        CartaoDAO cartaoDAO;

        cartaoDAO = new CartaoDAO();
        alCartao = cartaoDAO.queryCartaoByIdJogo(idJogo);
        dadosCartao = arrayList2StringMatrix(alCartao);
        cartaoDAO.fecharSessao();

        return dadosCartao;
    }

    public String[][] arrayList2StringMatrix(ArrayList<Cartao> alCartao)
    {
        String[][] dadosCartao;
        Cartao c;

        dadosCartao = new String[alCartao.size()][5];
        for (int i = 0; i < alCartao.size(); i++)
        {
            c = alCartao.get(i);
            dadosCartao[i][0] = String.valueOf(c.getIdoc());
            dadosCartao[i][1] = String.valueOf(c.getOcorrencia().getInstantetempo());
            dadosCartao[i][2] = c.getJogador().getSelecao().getPais();
            dadosCartao[i][3] = c.getJogador().getNome();
            dadosCartao[i][4] = c.getCor();
        }

        return dadosCartao;
    }

    public ResultadoOperacao registraCartao(String min, String seg, Short idJogo, String idJogador, String cor)
    {
        GeneralDAO gdao;
        Short shortIdJogador;
        Transaction tr;
        Cartao objCartao;
        Ocorrencia objOcorrencia;
        Jogador objJogador;
        String errorMessage;
        ResultadoOperacao result;

        errorMessage = validaCampos(min, seg, idJogador, idJogo);

        //se nao tiver erros nos campos, entao faz o cadastro
        if (errorMessage.equals(""))
        {
            gdao = new GeneralDAO();
            tr = gdao.getSessao().beginTransaction();
            try
            {
                objOcorrencia = ctrMain.getCtrOcorrenciaJogo().registraOcorrencia(gdao, min, seg, idJogo);

                //carrega o jogador
                shortIdJogador = Short.parseShort(idJogador);
                objJogador = ctrMain.getCtrJogador().carregaJogadorById(gdao, shortIdJogador);

                objCartao = new Cartao(objOcorrencia.getId(), objOcorrencia, objJogador, cor);

                gdao.salvar(objCartao);
                tr.commit();

                result = new ResultadoOperacao("Cartão cadastrado com êxito!", TipoResultadoOperacao.EXITO);
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

    private String validaCampos(String min, String seg, String idJogador, Short idJogo)
    {
        String errorMessage;

        errorMessage = ctrMain.getCtrOcorrenciaJogo().validaCampos(min, seg, idJogo);

        //so faz o outro teste se passou no primeiro teste
        if (errorMessage.equals(""))
        {
            try
            {
                Short.parseShort(idJogador);
            }
            catch (NumberFormatException nfe)
            {
                errorMessage = "Jogador: é obrigatório selecionar o jogador que levou o cartão.";
            }
        }

        return errorMessage;
    }

    public ResultadoOperacao removerCartao(Short idOc)
    {
        ResultadoOperacao result;
        Transaction tr;
        GeneralDAO gdao;
        Falta faltaParaRemover;
        Cartao cartaoParaRemover;
        Ocorrencia ocParaRemover;
        String errorMessage;

        cartaoParaRemover = new Cartao();
        ocParaRemover = new Ocorrencia();

        gdao = new GeneralDAO();
        tr = gdao.getSessao().beginTransaction();

        try
        {
            errorMessage = ctrMain.getCtrOcorrenciaJogo().validaRemocao(gdao, ocParaRemover, idOc);
            if (errorMessage.equals(""))
            {
                gdao.carregar(cartaoParaRemover, idOc);

                //se  houver faltas associadas com o cartao
                if (!cartaoParaRemover.getFaltas().isEmpty())
                {
                    Iterator it = cartaoParaRemover.getFaltas().iterator();

                    //remove a falta associada primeiro
                    faltaParaRemover = (Falta) it.next();
                    gdao.apagar(faltaParaRemover);
                }

                gdao.apagar(cartaoParaRemover);
                gdao.apagar(ocParaRemover);

                tr.commit();
                result = new ResultadoOperacao("Cartão e faltas associadas removidos com êxito!", TipoResultadoOperacao.EXITO);
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
        gdao.fecharSessao();

        return result;
    }
}
