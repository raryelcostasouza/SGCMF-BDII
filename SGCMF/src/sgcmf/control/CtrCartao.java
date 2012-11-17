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
    
    public ResultadoOperacao registraCartao(String min, String seg, Short idJogo, String idJogador, String cor)
    {
        GeneralDAO gdao;
        CartaoDAO cDAO;
        Short shortIdJogador;
        Transaction tr;
        Cartao objCartao;
        Cartao objCartaoVermDerivado;
        Ocorrencia objOcorrencia;
        Jogador objJogador;
        String errorMessage;
        ResultadoOperacao result;
        int numCartaoAmareloJogadorNoJogo;
        
        errorMessage = validaCampos(min, seg, idJogador, idJogo);

        //se nao tiver erros nos campos, entao faz o cadastro
        if (errorMessage.equals(""))
        {
            gdao = new GeneralDAO();
            cDAO = new CartaoDAO();
            
            tr = gdao.getSessao().beginTransaction();
            try
            {
                objOcorrencia = ctrMain.getCtrOcorrenciaJogo().registraOcorrencia(gdao, min, seg, idJogo);

                //carrega o jogador
                shortIdJogador = Short.parseShort(idJogador);
                objJogador = ctrMain.getCtrJogador().carregaJogadorById(gdao, shortIdJogador);
                
                numCartaoAmareloJogadorNoJogo = cDAO.queryNumCartaoAmareloJogadorJogo(idJogo, shortIdJogador);
                
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
                    gdao.salvar(objCartaoVermDerivado);
                }                
                
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
    
    public ResultadoOperacao removerCartao(Short idOc, Short idCartao)
    {
        ResultadoOperacao result;
        Transaction tr;
        GeneralDAO gdao;
        Falta faltaParaRemover;
        Cartao cartaoParaRemover;
        Cartao cartaoAssociado;
        Ocorrencia ocParaRemover;
        String errorMessage;
        Iterator<Falta> itFalta;
        Iterator<Cartao> itCartao;
        
        cartaoParaRemover = new Cartao();
        ocParaRemover = new Ocorrencia();
        
        gdao = new GeneralDAO();
        tr = gdao.getSessao().beginTransaction();
        
        try
        {
            errorMessage = ctrMain.getCtrOcorrenciaJogo().validaRemocao(gdao, ocParaRemover, idOc);
            if (errorMessage.equals(""))
            {
                gdao.carregar(cartaoParaRemover, idCartao);
                ocParaRemover = cartaoParaRemover.getOcorrencia();
                
                //apaga faltas associadas
                if (!ocParaRemover.getFaltas().isEmpty())
                {
                    itFalta = ocParaRemover.getFaltas().iterator();
                    faltaParaRemover = itFalta.next();
                    gdao.apagar(faltaParaRemover);
                }
                
                //remove todos os cartoes associados
                if (!ocParaRemover.getCartaos().isEmpty())
                {
                    itCartao = ocParaRemover.getCartaos().iterator();
                    
                    while(itCartao.hasNext())
                    {
                        cartaoAssociado = itCartao.next();
                        gdao.apagar(cartaoAssociado);
                    }
                }
                //remove a ocorrência
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
