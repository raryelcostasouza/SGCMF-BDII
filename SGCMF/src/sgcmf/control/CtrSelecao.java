package sgcmf.control;

import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.SelecaoDAO;
import sgcmf.model.hibernate.Selecao;

public class CtrSelecao
{
    public String[][] querySelecaoTodos()
    {
        SelecaoDAO sdao;
        ArrayList<Selecao> alSelecao;
        String[][] dadosSelecoes;

        SGCMFSessionManager.abrirSessao();
        sdao = SelecaoDAO.getInstance();
        
        alSelecao = sdao.listaTodos();
        dadosSelecoes = arrayList2StringMatrix(alSelecao);
        
        SGCMFSessionManager.fecharSessao();

        return dadosSelecoes;
    }

    public String[][] querySelecaoByNomePais(String pais)
    {
        SelecaoDAO sDAO;
        ArrayList<Selecao> alSelecao;
        String[][] dadosSelecoes;

        SGCMFSessionManager.abrirSessao();
        sDAO = SelecaoDAO.getInstance();
        
        alSelecao = sDAO.querySelecaoByNomePais(pais);
        dadosSelecoes = arrayList2StringMatrix(alSelecao);
        
        SGCMFSessionManager.fecharSessao();

        return dadosSelecoes;
    }

    public String[][] querySelecaoByNomeTecnico(String nomeTecnico)
    {
        SelecaoDAO sDAO;
        String[][] dadosSelecoes;
        ArrayList<Selecao> alSelecao;

        SGCMFSessionManager.abrirSessao();
        sDAO = SelecaoDAO.getInstance();
        
        alSelecao = sDAO.querySelecaoByNomeTecnico(nomeTecnico);
        dadosSelecoes = arrayList2StringMatrix(alSelecao);
        
        SGCMFSessionManager.fecharSessao();

        return dadosSelecoes;
    }

    private String[][] arrayList2StringMatrix(ArrayList<Selecao> alSelecao)
    {
        String[][] dadosSelecoes;
        Selecao s;

        dadosSelecoes = new String[alSelecao.size()][3];
        for (int i = 0; i < alSelecao.size(); i++)
        {
            s = alSelecao.get(i);
            dadosSelecoes[i][0] = s.getId() + "";
            dadosSelecoes[i][1] = s.getPais();
            dadosSelecoes[i][2] = s.getUsuario().getNome();
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
