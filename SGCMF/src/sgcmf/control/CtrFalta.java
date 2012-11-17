package sgcmf.control;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import sgcmf.model.dao.FaltaDAO;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.hibernate.Cartao;
import sgcmf.model.hibernate.Falta;
import sgcmf.model.hibernate.Jogador;
import sgcmf.model.hibernate.Ocorrencia;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;

public class CtrFalta
{
    private CtrMain ctrMain;

    public CtrFalta(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
    }

    public String[][] queryFaltaByIdJogo(Short idJogo)
    {
        ArrayList<Falta> alFalta;
        String[][] dadosFalta;
        FaltaDAO faltaDAO;

        faltaDAO = new FaltaDAO();
        alFalta = faltaDAO.queryFaltaByIdJogo(idJogo);
        dadosFalta = arrayList2StringMatrix(alFalta);
        faltaDAO.fecharSessao();

        return dadosFalta;
    }

    public String[][] arrayList2StringMatrix(ArrayList<Falta> alFalta)
    {
        String[][] dadosFalta;
        Falta f;

        dadosFalta = new String[alFalta.size()][6];
        for (int i = 0; i < alFalta.size(); i++)
        {
            f = alFalta.get(i);
            dadosFalta[i][0] = String.valueOf(f.getIdoc());
            dadosFalta[i][1] = String.valueOf(f.getOcorrencia().getInstantetempo());
            dadosFalta[i][2] = f.getJogador().getSelecao().getPais();
            dadosFalta[i][3] = f.getJogador().getNome();

            if (f.getCartao() != null)
            {
                dadosFalta[i][4] = f.getCartao().getCor();
            }
            else
            {
                dadosFalta[i][4] = "";
            }

            dadosFalta[i][5] = f.getTipo();
        }

        return dadosFalta;
    }

    public ResultadoOperacao registrarFalta(String min, String seg, Short idJogo, String idJogador,
                                            String tipo, String cartao)
    {
        GeneralDAO gdao;
        Short shortIdJogador;
        Transaction tr;
        Falta objFalta;
        Ocorrencia objOcorrencia;
        Cartao objCartao;
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

                //carrega o jogador autor
                shortIdJogador = Short.parseShort(idJogador);
                objJogador = ctrMain.getCtrJogador().carregaJogadorById(gdao, shortIdJogador);

                //se tiver jogador assistente, ele eh carregado
                if (cartao.equals("Nenhum"))
                {
                    objFalta = new Falta(objOcorrencia.getId(), objOcorrencia, objJogador, tipo);
                }
                else
                {
                    objCartao = new Cartao();
                    objCartao.setOcorrencia(objOcorrencia);
                    objCartao.setJogador(objJogador);
                    objCartao.setCor(cartao);
                    
                    gdao.salvar(objCartao);
                    objFalta = new Falta(objOcorrencia.getId(), objOcorrencia, objCartao, objJogador, tipo);
                }

                gdao.salvar(objFalta);
                tr.commit();

                result = new ResultadoOperacao("Falta cadastrada com êxito!", TipoResultadoOperacao.EXITO);
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
                errorMessage = "Jogador: é obrigatório selecionar o jogador que realizou a falta.";
            }
        }

        return errorMessage;
    }

    public ResultadoOperacao removeFalta(Short idOc)
    {
        ResultadoOperacao result;
        Transaction tr;
        GeneralDAO gdao;
        Falta faltaParaRemover;
        Cartao cartaoParaRemover;
        Ocorrencia ocParaRemover;
        String errorMessage;

        cartaoParaRemover = new Cartao();
        faltaParaRemover = new Falta();
        ocParaRemover = new Ocorrencia();

        gdao = new GeneralDAO();
        tr = gdao.getSessao().beginTransaction();

        try
        {
            errorMessage = ctrMain.getCtrOcorrenciaJogo().validaRemocao(gdao, ocParaRemover, idOc);
            if (errorMessage.equals(""))
            {
                gdao.carregar(faltaParaRemover, idOc);

                if (faltaParaRemover.getCartao() != null)
                {
                    cartaoParaRemover = faltaParaRemover.getCartao();
                }
                else
                {
                    cartaoParaRemover = null;
                }

                if (cartaoParaRemover != null)
                {
                    gdao.apagar(cartaoParaRemover);
                }
                gdao.apagar(faltaParaRemover);
                gdao.apagar(ocParaRemover);

                tr.commit();
                result = new ResultadoOperacao("Falta e cartão associado removidos com êxito!", TipoResultadoOperacao.EXITO);
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
