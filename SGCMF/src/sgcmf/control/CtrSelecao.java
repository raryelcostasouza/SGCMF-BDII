package sgcmf.control;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.CartaoDAO;
import sgcmf.model.dao.FaltaDAO;
import sgcmf.model.dao.GolDAO;
import sgcmf.model.dao.SelecaoDAO;
import sgcmf.model.hibernate.Selecao;
import sgcmf.view.comiteGestor.LimConsultaSelecao;

public class CtrSelecao
{
    private LimConsultaSelecao limConsultaSelecao;

    public CtrSelecao()
    {
        limConsultaSelecao = new LimConsultaSelecao(this);
    }

    public void ativaLimConsultaSelecao()
    {
        limConsultaSelecao.ativaTela();
    }

    public Object[][] querySelecaoTodos()
    {
        SelecaoDAO sdao;
        ArrayList<Selecao> alSelecao;
        Object[][] dadosSelecoes;

        SGCMFSessionManager.abrirSessao();
        sdao = SelecaoDAO.getInstance();

        alSelecao = sdao.listaTodos();
        dadosSelecoes = arrayList2StringMatrix(alSelecao);

        SGCMFSessionManager.fecharSessao();

        return dadosSelecoes;
    }

    public Object[][] querySelecaoByNomePais(String pais)
    {
        SelecaoDAO sDAO;
        ArrayList<Selecao> alSelecao;
        Object[][] dadosSelecoes;

        SGCMFSessionManager.abrirSessao();
        sDAO = SelecaoDAO.getInstance();

        alSelecao = sDAO.querySelecaoByNomePais(pais);
        dadosSelecoes = arrayList2StringMatrix(alSelecao);

        SGCMFSessionManager.fecharSessao();

        return dadosSelecoes;
    }

    public Object[][] querySelecaoByNomeTecnico(String nomeTecnico)
    {
        SelecaoDAO sDAO;
        Object[][] dadosSelecoes;
        ArrayList<Selecao> alSelecao;

        SGCMFSessionManager.abrirSessao();
        sDAO = SelecaoDAO.getInstance();

        alSelecao = sDAO.querySelecaoByNomeTecnico(nomeTecnico);
        dadosSelecoes = arrayList2StringMatrix(alSelecao);

        SGCMFSessionManager.fecharSessao();

        return dadosSelecoes;
    }

    public Object[][] querySelecaoByGrupo(String grupo)
    {
        SelecaoDAO sDAO;
        Object[][] dadosSelecoes;
        ArrayList<Selecao> alSelecao;

        SGCMFSessionManager.abrirSessao();
        sDAO = SelecaoDAO.getInstance();

        alSelecao = sDAO.querySelecaoByGrupo(grupo);
        dadosSelecoes = arrayList2StringMatrix(alSelecao);

        SGCMFSessionManager.fecharSessao();

        return dadosSelecoes;
    }

    private Object[][] arrayList2StringMatrix(ArrayList<Selecao> alSelecao)
    {
        Object[][] dadosSelecoes;
        Selecao s;

        dadosSelecoes = new Object[alSelecao.size()][4];
        for (int i = 0; i < alSelecao.size(); i++)
        {
            s = alSelecao.get(i);
            dadosSelecoes[i][0] = s.getId() + "";
            dadosSelecoes[i][1] = new JLabel(s.getPais(), new ImageIcon(s.getCaminhoimgbandeira()), JLabel.LEFT);
            dadosSelecoes[i][2] = s.getGrupo();
            dadosSelecoes[i][3] = s.getUsuario().getNome();
        }

        return dadosSelecoes;
    }

    public Short capturarIdSelecao(String nomeSelecao)
    {
        ArrayList alIdSelecao;
        SelecaoDAO sDao;
        Short idSelecao;

        SGCMFSessionManager.abrirSessao();
        sDao = SelecaoDAO.getInstance();
        alIdSelecao = sDao.queryIdSelecao(nomeSelecao);
        idSelecao = (Short) (alIdSelecao.get(0));
        SGCMFSessionManager.fecharSessao();

        return idSelecao;
    }

    public String pesquisarNomeSelecao(Short idSelecao)
    {
        SelecaoDAO selecaoDAO;
        String nomeSelecao;

        SGCMFSessionManager.abrirSessao();
        selecaoDAO = SelecaoDAO.getInstance();
        nomeSelecao = selecaoDAO.queryNomeSelecao(idSelecao);
        SGCMFSessionManager.fecharSessao();

        return nomeSelecao;
    }
}
