package sgcmf.control;

import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.SelecaoDAO2;
import sgcmf.model.hibernate.Selecao;

public class CtrSelecao
{
    public String[][] querySelecaoTodos()
    {
        SelecaoDAO2 sdao;
        ArrayList<Selecao> alSelecao;
        String[][] dadosSelecoes;

        SGCMFSessionManager.abrirSessao();
        sdao = SelecaoDAO2.getInstance();
        
        alSelecao = sdao.listaTodos();
        dadosSelecoes = arrayList2StringMatrix(alSelecao);
        
        SGCMFSessionManager.fecharSessao();

        return dadosSelecoes;
    }

    public String[][] querySelecaoByNomePais(String pais)
    {
        SelecaoDAO2 sDAO;
        ArrayList<Selecao> alSelecao;
        String[][] dadosSelecoes;

        SGCMFSessionManager.abrirSessao();
        sDAO = SelecaoDAO2.getInstance();
        
        alSelecao = sDAO.querySelecaoByNomePais(pais);
        dadosSelecoes = arrayList2StringMatrix(alSelecao);
        
        SGCMFSessionManager.fecharSessao();

        return dadosSelecoes;
    }

    public String[][] querySelecaoByNomeTecnico(String nomeTecnico)
    {
        SelecaoDAO2 sDAO;
        String[][] dadosSelecoes;
        ArrayList<Selecao> alSelecao;

        SGCMFSessionManager.abrirSessao();
        sDAO = SelecaoDAO2.getInstance();
        
        alSelecao = sDAO.querySelecaoByNomeTecnico(nomeTecnico);
        dadosSelecoes = arrayList2StringMatrix(alSelecao);
        
        SGCMFSessionManager.fecharSessao();

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
        SelecaoDAO2 sDao;
        Short idSelecao;
        
        SGCMFSessionManager.abrirSessao();
        sDao = SelecaoDAO2.getInstance();
        alIdSelecao = sDao.queryIdSelecao(nomeSelecao);
        idSelecao = (Short) (alIdSelecao.get(0));
        SGCMFSessionManager.fecharSessao();
        
        return idSelecao;
    }
    
    public String pesquisarNomeSelecao(Short idSelecao)
    {
        SelecaoDAO2 selecaoDAO;
        String nomeSelecao;
        
        SGCMFSessionManager.abrirSessao();
        selecaoDAO = SelecaoDAO2.getInstance();
        nomeSelecao = selecaoDAO.queryNomeSelecao(idSelecao);
        SGCMFSessionManager.fecharSessao();
        
        return nomeSelecao;
    }
}
