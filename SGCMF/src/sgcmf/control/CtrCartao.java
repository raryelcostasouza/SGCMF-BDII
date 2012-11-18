package sgcmf.control;

import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.CartaoDAO2;
import sgcmf.model.dao.FaltaDAO2;
import sgcmf.model.dao.JogadorDAO2;
import sgcmf.model.dao.OcorrenciaDAO2;
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

        SGCMFSessionManager.openSession();
        alCartao = CartaoDAO2.getInstance().queryCartaoByIdJogo(idJogo);
        dadosCartao = arrayList2StringMatrix(alCartao);
        SGCMFSessionManager.closeSession();

        return dadosCartao;
    }

    public String[][] arrayList2StringMatrix(ArrayList<Cartao> alCartao)
    {
        String[][] dadosCartao;
        Cartao c;

        dadosCartao = new String[alCartao.size()][6];
        for (int i = 0; i < alCartao.size(); i++)
        {
            c = alCartao.get(i);
            dadosCartao[i][0] = String.valueOf(c.getId());
            dadosCartao[i][1] = String.valueOf(c.getOcorrencia().getId());
            dadosCartao[i][2] = String.valueOf(c.getOcorrencia().getInstantetempo());
            dadosCartao[i][3] = c.getJogador().getSelecao().getPais();
            dadosCartao[i][4] = c.getJogador().getNome();
            dadosCartao[i][5] = c.getCor();
        }

        return dadosCartao;
    }

    public ResultadoOperacao registraCartaoUI(String min, String seg, Short idJogo, String idJogador, String cor)
    {
        Short shortIdJogador;
        Transaction tr;
        Ocorrencia objOcorrencia;
        Jogador objJogador;
        String errorMessage;
        ResultadoOperacao result;

        errorMessage = validaCampos(min, seg, idJogador, idJogo);

        //se nao tiver erros nos campos, entao faz o cadastro
        if (errorMessage.equals(""))
        {
            SGCMFSessionManager.openSession();
            tr = SGCMFSessionManager.getCurrentSession().beginTransaction();
            try
            {
                objOcorrencia = ctrMain.getCtrOcorrenciaJogo().registraOcorrencia(min, seg, idJogo);

                //carrega o jogador
                shortIdJogador = Short.parseShort(idJogador);
                
                objJogador = new Jogador();
                objJogador = JogadorDAO2.getInstance().carregar(objJogador, shortIdJogador);
                
                registraCartao(objOcorrencia, objJogador, cor);
                tr.commit();

                result = new ResultadoOperacao("Cartão cadastrado com êxito!", TipoResultadoOperacao.EXITO);
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

    public Cartao registraCartao(Ocorrencia objOcorrencia, Jogador objJogador, String cor) throws HibernateException
    {
        int numCartaoAmareloJogadorNoJogo;
        Cartao objCartao, objCartaoVermDerivado;        

        numCartaoAmareloJogadorNoJogo = CartaoDAO2.getInstance().queryNumCartaoAmareloJogadorJogo(objOcorrencia.getJogo().getId(), objJogador.getId());

        objCartao = new Cartao();
        objCartao.setOcorrencia(objOcorrencia);
        objCartao.setJogador(objJogador);
        objCartao.setCor(cor);
        
        //se o jogador ja levou um amarelo no jogo e acabou de levar mais um
        //alem de lancar o cartao amarelo, vai receber automaticamente um vermelho
        if (numCartaoAmareloJogadorNoJogo == 1 && cor.equals("Amarelo"))
        {
            objCartaoVermDerivado = new Cartao();
            objCartaoVermDerivado.setOcorrencia(objOcorrencia);
            objCartaoVermDerivado.setJogador(objJogador);
            objCartaoVermDerivado.setCor("Vermelho");

            objCartao.setCartao(objCartaoVermDerivado);
            CartaoDAO2.getInstance().salvar(objCartaoVermDerivado);
        }

        CartaoDAO2.getInstance().salvar(objCartao);
        
        return objCartao;
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

    public ResultadoOperacao removerCartao(Short idOc, Short idCartao)
    {
        ResultadoOperacao result;
        Transaction tr;
        Falta faltaParaRemover;
        Cartao cartaoParaRemover;
        Cartao cartaoAssociado;
        Ocorrencia ocParaRemover;
        String errorMessage;
        Iterator<Falta> itFalta;
        Iterator<Cartao> itCartao;

        cartaoParaRemover = new Cartao();
        ocParaRemover = new Ocorrencia();

        SGCMFSessionManager.openSession();
        tr = SGCMFSessionManager.getCurrentSession().beginTransaction();

        try
        {
            errorMessage = ctrMain.getCtrOcorrenciaJogo().validaRemocao(ocParaRemover, idOc);
            if (errorMessage.equals(""))
            {
                CartaoDAO2.getInstance().carregar(cartaoParaRemover, idCartao);
                ocParaRemover = cartaoParaRemover.getOcorrencia();

                //apaga faltas associadas
                if (!ocParaRemover.getFaltas().isEmpty())
                {
                    itFalta = ocParaRemover.getFaltas().iterator();
                    faltaParaRemover = itFalta.next();
                    FaltaDAO2.getInstance().apagar(faltaParaRemover);
                }

                //remove todos os cartoes associados
                if (!ocParaRemover.getCartaos().isEmpty())
                {
                    itCartao = ocParaRemover.getCartaos().iterator();

                    while (itCartao.hasNext())
                    {
                        cartaoAssociado = itCartao.next();
                        CartaoDAO2.getInstance().apagar(cartaoAssociado);
                    }
                }
                //remove a ocorrência
                OcorrenciaDAO2.getInstance().apagar(ocParaRemover);

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
        SGCMFSessionManager.closeSession();

        return result;
    }
}
