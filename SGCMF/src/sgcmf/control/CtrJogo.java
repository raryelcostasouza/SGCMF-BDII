package sgcmf.control;

import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.JogoDAO;
import sgcmf.model.hibernate.Jogo;
import sgcmf.model.other.SGCMFDate;

public class CtrJogo
{    
    public String[][] queryJogoTodos()
    {
        JogoDAO jDAO;
        ArrayList<Jogo> alJogos;
        String[][] dadosJogos;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();
        
        alJogos = jDAO.listaTodos();
        dadosJogos = arrayList2StringMatrix(alJogos);
        
        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }

    public String[][] queryJogoBySelecao(String nomeSelecao)
    {
        JogoDAO jDAO;
        String[][] dadosJogos;
        ArrayList<Jogo> alJogo;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();
        
        alJogo = jDAO.queryJogoBySelecao(nomeSelecao);
        dadosJogos = arrayList2StringMatrix(alJogo);
        
        SGCMFSessionManager.fecharSessao();
        
        return dadosJogos;
    }

    public String[][] queryJogoByCidade(String cidade)
    {
        JogoDAO jDAO;
        String[][] dadosJogos;
        ArrayList<Jogo> alJogo;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();
        
        alJogo = jDAO.queryJogoByCidade(cidade);
        dadosJogos = arrayList2StringMatrix(alJogo);
        
        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }

    public String[][] queryJogoByEstadio(String estadio)
    {
        JogoDAO jDAO;
        String[][] dadosJogos;
        ArrayList<Jogo> alJogo;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();
        
        alJogo = jDAO.queryJogoByEstadio(estadio);
        dadosJogos = arrayList2StringMatrix(alJogo);
        
        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }

    public String[][] queryJogoByTipo(String tipo)
    {
        JogoDAO jDAO;
        String[][] dadosJogos;
        ArrayList<Jogo> alJogo;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();
        alJogo = jDAO.queryJogoByTipo(tipo);
        
        dadosJogos = arrayList2StringMatrix(alJogo);
        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }
    
    public String[][] queryJogoByGrupo(String grupo)
    {
        JogoDAO jDAO;
        String[][] dadosJogos;
        ArrayList<Jogo> alJogo;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();
        alJogo = jDAO.queryJogoByGrupo(grupo);
        
        dadosJogos = arrayList2StringMatrixParaTabelaCampeonato(alJogo);
        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }

    private String[][] arrayList2StringMatrix(ArrayList<Jogo> alJogo)
    {
        String[][] dadosJogos;
        Jogo j;

        dadosJogos = new String[alJogo.size()][7];
        for (int i = 0; i < alJogo.size(); i++)
        {
            j = alJogo.get(i);
            dadosJogos[i][0] = String.valueOf(j.getId());
            dadosJogos[i][1] = String.valueOf(j.getTipo());
            dadosJogos[i][2] = SGCMFDate.toStringDataHoraFormatoBrasil(j.getDatahora());
            dadosJogos[i][3] = j.getCidade();
            dadosJogos[i][4] = j.getNomeestadio();
            dadosJogos[i][5] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
            dadosJogos[i][6] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
        }

        return dadosJogos;
    }
    
    private String[][] arrayList2StringMatrixParaTabelaCampeonato(ArrayList<Jogo> alJogo)
    {
        String[][] dadosJogos;
        Jogo j;

        dadosJogos = new String[alJogo.size()][5];
        for (int i = 0; i < alJogo.size(); i++)
        {
            j = alJogo.get(i);
            dadosJogos[i][0] = SGCMFDate.toStringDataHoraFormatoBrasil(j.getDatahora());
            dadosJogos[i][1] = j.getCidade();
            dadosJogos[i][2] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
            dadosJogos[i][3] = "";
            dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());            
        }

        return dadosJogos;
    }

    public String queryInfoJogoById(Short idJogo)
    {
        String infoJogo;
        JogoDAO jDao;
        Jogo j;
             
        SGCMFSessionManager.abrirSessao();
        jDao = JogoDAO.getInstance();
        j = jDao.queryInfoJogoById(idJogo);
        
        infoJogo = SGCMFDate.toStringDataHoraFormatoBrasil(j.getDatahora()) + " | " + j.getTipo() + " | "
                + j.getSelecaoByIdselecaoi().getPais() + " vs " + j.getSelecaoByIdselecaoii().getPais();
        SGCMFSessionManager.fecharSessao();

        return infoJogo;
    }
}
