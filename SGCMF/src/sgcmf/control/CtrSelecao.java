package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.SelecaoDAO;
import sgcmf.model.hibernate.Selecao;

public class CtrSelecao
{
    public String[][] querySelecaoTodos()
    {
        SelecaoDAO sdao;
        ArrayList<Selecao> alSelecao;
        String[][] dadosSelecoes;

        sdao = new SelecaoDAO();
        alSelecao = sdao.listaTodos();
        dadosSelecoes = arrayList2StringMatrix(alSelecao);
        sdao.fecharSessao();

        return dadosSelecoes;
    }

    public String[][] querySelecaoByNomePais(String pais)
    {
        SelecaoDAO sDAO;
        ArrayList<Selecao> alSelecao;
        String[][] dadosSelecoes;

        sDAO = new SelecaoDAO();
        alSelecao = sDAO.querySelecaoByNomePais(pais);
        dadosSelecoes = arrayList2StringMatrix(alSelecao);
        sDAO.fecharSessao();

        return dadosSelecoes;
    }

    public String[][] querySelecaoByNomeTecnico(String nomeTecnico)
    {
        SelecaoDAO sDAO;
        String[][] dadosSelecoes;
        ArrayList<Selecao> alSelecao;

        sDAO = new SelecaoDAO();
        alSelecao = sDAO.querySelecaoByNomeTecnico(nomeTecnico);
        dadosSelecoes = arrayList2StringMatrix(alSelecao);
        sDAO.fecharSessao();

        return dadosSelecoes;
    }

    private String[][] arrayList2StringMatrix(ArrayList<Selecao> alSelecao)
    {
        String[][] dadosSelecoes;
        Selecao s;

        dadosSelecoes = new String[alSelecao.size()][4];
        for (int i = 0; i < alSelecao.size(); i++)
        {
            s = alSelecao.get(i);
            dadosSelecoes[i][0] = s.getId() + "";
            dadosSelecoes[i][1] = s.getPais();
            dadosSelecoes[i][2] = s.getUsuario().getNome();
            dadosSelecoes[i][3] = s.getCaminhoimgbandeira();
        }

        return dadosSelecoes;
    }

    public Short capturarIdSelecao(String nomeSelecao)
    {
        ArrayList alIdSelecao;
        SelecaoDAO sDao;
        Short idSelecao;
        sDao = new SelecaoDAO();
        alIdSelecao = sDao.queryIdSelecao(nomeSelecao);
        idSelecao = (Short) (alIdSelecao.get(0));
        return idSelecao;
    }
    
    public String pesquisarNomeSelecao(Short idSelecao)
    {
        SelecaoDAO selecaoDAO = new SelecaoDAO();
        String nomeSelecao;
        nomeSelecao = selecaoDAO.queryNomeSelecao(idSelecao);
        selecaoDAO.fecharSessao();
        return nomeSelecao;
    }
}
