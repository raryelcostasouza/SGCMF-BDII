package sgcmf.control;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.JogadorDAO;
import sgcmf.model.dao.OcorrenciaDAO;
import sgcmf.model.dao.SubstituicaoDAO;
import sgcmf.model.hibernate.Jogador;
import sgcmf.model.hibernate.Ocorrencia;
import sgcmf.model.hibernate.Substituicao;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;

public class CtrSubstituicao
{
    private CtrMain ctrMain;

    public CtrSubstituicao(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
    }

    public Object[][] querySubstByIdJogo(Short idJogo)
    {
        ArrayList<Substituicao> alSubst;
        Object[][] dadosCartao;

        SGCMFSessionManager.abrirSessao();
        alSubst = SubstituicaoDAO.getInstance().querySubstByIdJogo(idJogo);
        dadosCartao = arrayList2StringMatrix(alSubst);
        SGCMFSessionManager.fecharSessao();

        return dadosCartao;
    }

    public Object[][] arrayList2StringMatrix(ArrayList<Substituicao> alSubst)
    {
        Object[][] dadosSubst;
        Substituicao s;
        Jogador jEntrou;
        Jogador jSaiu;

        dadosSubst = new Object[alSubst.size()][6];
        for (int i = 0; i < alSubst.size(); i++)
        {
            s = alSubst.get(i);

            jEntrou = s.getJogadorByIdjogadorentrou();
            jSaiu = s.getJogadorByIdjogadorsaiu();

            dadosSubst[i][0] = String.valueOf(s.getIdoc());
            dadosSubst[i][1] = String.valueOf(s.getOcorrencia().getInstantetempo());
            dadosSubst[i][2] = new JLabel(s.getJogadorByIdjogadorsaiu().getSelecao().getPais(), new ImageIcon(s.getJogadorByIdjogadorsaiu().getSelecao().getCaminhoimgbandeira()), JLabel.LEFT);
            dadosSubst[i][3] = "(" + jSaiu.getNcamisa() + ") " + jSaiu.getNome();
            dadosSubst[i][4] = "(" + jEntrou.getNcamisa() + ") " + jEntrou.getNome();
            dadosSubst[i][5] = s.getMotivo();
        }

        return dadosSubst;
    }

    public ResultadoOperacao registraSubstituicao(String min, String seg, Short idJogo, String idJogadorSaiu,
                                                  String idJogadorEntrou, String motivo)
    {
        Short shortIdJogadorSaiu;
        Short shortIdJogadorEntrou;
        Transaction tr;
        Substituicao objSubst;
        Ocorrencia objOcorrencia;
        Jogador objJogadorSaiu;
        Jogador objJogadorEntrou;
        String errorMessage;
        ResultadoOperacao result;

        errorMessage = validaCampos(min, seg, idJogadorSaiu, idJogadorEntrou, idJogo);

        //se nao tiver erros nos campos, entao faz o cadastro
        if (errorMessage.equals(""))
        {
            SGCMFSessionManager.abrirSessao();
            tr = SGCMFSessionManager.getCurrentSession().beginTransaction();
            try
            {
                objOcorrencia = ctrMain.getCtrOcorrenciaJogo().registraOcorrencia(min, seg, idJogo);

                shortIdJogadorSaiu = Short.parseShort(idJogadorSaiu);
                shortIdJogadorEntrou = Short.parseShort(idJogadorEntrou);

                objJogadorSaiu = new Jogador();
                objJogadorEntrou = new Jogador();
                JogadorDAO.getInstance().carregar(objJogadorSaiu, shortIdJogadorSaiu);
                JogadorDAO.getInstance().carregar(objJogadorEntrou, shortIdJogadorEntrou);

                objSubst = new Substituicao(objOcorrencia.getId(), objJogadorEntrou, objJogadorSaiu, objOcorrencia, motivo);
                SubstituicaoDAO.getInstance().salvar(objSubst);
                tr.commit();

                result = new ResultadoOperacao("Substituição registrada com êxito!", TipoResultadoOperacao.EXITO);
            }
            catch (HibernateException hex)
            {
                tr.rollback();
                result = new ResultadoOperacao("Erro do Hibernate:\n" + hex.getMessage(), TipoResultadoOperacao.ERRO);
            }
            SGCMFSessionManager.fecharSessao();
        }
        else
        {
            result = new ResultadoOperacao(errorMessage, TipoResultadoOperacao.ERRO);
        }

        return result;
    }

    private String validaCampos(String min, String seg, String idJogadorSaiu, String idJogadorEntrou, Short idJogo)
    {
        String errorMessage;
        SubstituicaoDAO sDAO;
        int nSubJogo;
        Jogador jSaiu;

        errorMessage = ctrMain.getCtrOcorrenciaJogo().validaCampos(min, seg, idJogo);

        //so faz o outro teste se passou no primeiro teste
        if (errorMessage.equals(""))
        {
            try
            {
                Short.parseShort(idJogadorSaiu);
            }
            catch (NumberFormatException nfe)
            {
                errorMessage = "Jogador: é obrigatório selecionar o jogador que saiu de campo.";
            }
        }

        if (errorMessage.equals(""))
        {
            try
            {
                Short.parseShort(idJogadorEntrou);
            }
            catch (NumberFormatException nfe)
            {
                errorMessage = "Jogador: é obrigatório selecionar o jogador que entrou em campo.";
            }
        }

        if (errorMessage.equals(""))
        {
            SGCMFSessionManager.abrirSessao();
            jSaiu = new Jogador();
            JogadorDAO.getInstance().carregar(jSaiu, Short.parseShort(idJogadorSaiu));
            sDAO = SubstituicaoDAO.getInstance();

            nSubJogo = sDAO.queryNumSubstituicoesSelecaoJogo(idJogo, jSaiu.getSelecao().getId());
            SGCMFSessionManager.fecharSessao();

            if (nSubJogo == 3)
            {
                errorMessage = "Já há três substituições cadastradas\npara essa seleção nesse jogo.";
            }
        }

        return errorMessage;
    }

    public ResultadoOperacao removeSubstituicao(Short idOc)
    {
        ResultadoOperacao result;
        Transaction tr;
        Substituicao substParaRemover;
        Ocorrencia ocParaRemover;
        String errorMessage;

        substParaRemover = new Substituicao();
        ocParaRemover = new Ocorrencia();

        SGCMFSessionManager.abrirSessao();
        tr = SGCMFSessionManager.getCurrentSession().beginTransaction();

        try
        {
            errorMessage = ctrMain.getCtrOcorrenciaJogo().validaRemocao(ocParaRemover, idOc);
            if (errorMessage.equals(""))
            {
                SubstituicaoDAO.getInstance().carregar(substParaRemover, idOc);
                SubstituicaoDAO.getInstance().apagar(substParaRemover);
                ctrMain.getCtrOcorrenciaJogo().removerOcorrencia(ocParaRemover);

                tr.commit();
                result = new ResultadoOperacao("Substituição removida com êxito!", TipoResultadoOperacao.EXITO);
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
        SGCMFSessionManager.fecharSessao();

        return result;
    }
}
