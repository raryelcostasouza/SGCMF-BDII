package sgcmf.control;

import java.util.ArrayList;
import java.util.Arrays;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.GolDAO;
import sgcmf.model.dao.JogoDAO;
import sgcmf.model.dao.OcorrenciaDAO;
import sgcmf.model.dao.SelecaoDAO;
import sgcmf.model.hibernate.Jogo;
import sgcmf.model.hibernate.Selecao;
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

    public void getClassificadosGrupo(String grupo)
    {
        Object[][] ptosSelecoes;
        int p;

        SGCMFSessionManager.abrirSessao();
        ptosSelecoes = calculaPontosSelecoesNoGrupo(grupo);
        for (int i = 0; i < 4; i++)
        {
            Selecao s;
            s = (Selecao) ptosSelecoes[i][0];
            System.out.println(s.getPais() + ": " + ptosSelecoes[i][1]);

        }

        SGCMFSessionManager.fecharSessao();

        //return classificados;
    }

    private String[][] arrayList2StringMatrix(ArrayList<Jogo> alJogo)
    {
        String[][] dadosJogos;
        Jogo j;

        dadosJogos = new String[alJogo.size()][6];
        for (int i = 0; i < alJogo.size(); i++)
        {
            j = alJogo.get(i);
            dadosJogos[i][0] = String.valueOf(j.getId());
            dadosJogos[i][1] = SGCMFDate.toStringDataHoraFormatoBrasil(j.getDatahora());
            dadosJogos[i][2] = j.getCidade();
            dadosJogos[i][3] = j.getNomeestadio();
            dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
            dadosJogos[i][5] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
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
            dadosJogos[i][3] = geraPlacarJogo(j);
            dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
        }

        return dadosJogos;
    }

    private String geraPlacarJogo(Jogo j)
    {
        String placar = "";
        int numGolsSelI;
        int numGolsSelII;
        OcorrenciaDAO oDAO;
        GolDAO gDAO;

        oDAO = OcorrenciaDAO.getInstance();
        gDAO = GolDAO.getInstance();
        //se o jogo ocorreu
        //(se há alguma ocorrência cadastrada)
        if (oDAO.queryHaOcorrenciaParaJogo(j.getId()) != 0)
        {
            numGolsSelI = gDAO.queryNumGolsJogoSelecao(j.getId(), j.getSelecaoByIdselecaoi().getId(), j.getSelecaoByIdselecaoii().getId());
            numGolsSelII = gDAO.queryNumGolsJogoSelecao(j.getId(), j.getSelecaoByIdselecaoii().getId(), j.getSelecaoByIdselecaoi().getId());

            placar = numGolsSelI + " x " + numGolsSelII;
        }

        return placar;
    }

    private Object[][] calculaPontosSelecoesNoGrupo(String grupo)
    {
        ArrayList<Selecao> selecoesGrupo;
        SelecaoDAO sDAO;
        Object[][] ptosSelecoes = new Object[4][2];
        int i;

        sDAO = SelecaoDAO.getInstance();
        selecoesGrupo = sDAO.querySelecaoByGrupo(grupo);

        i = 0;
        for (Selecao s : selecoesGrupo)
        {
            ptosSelecoes[i][0] = s;
            ptosSelecoes[i][1] = calculaPontosSelecao(s);
            i++;
        }
        return ptosSelecoes;
    }

    private int calculaPontosSelecao(Selecao s)
    {
        int totalPtos;
        ArrayList<Jogo> jogosSelecao;
        JogoDAO jDAO;
        int resultado;

        jDAO = JogoDAO.getInstance();
        jogosSelecao = jDAO.queryJogoByIdSelecao(s.getId());

        totalPtos = 0;
        for (Jogo j : jogosSelecao)
        {
            resultado = resultadoJogo(j, s.getId());
            if (resultado != -1)
            {
                totalPtos += resultado;
            }
        }

        return totalPtos;
    }

    private int resultadoJogo(Jogo j, Short idSelecao)
    {
        int numGolsSelI;
        int numGolsSelII;
        GolDAO gDAO;
        OcorrenciaDAO oDAO;
        int resultado;
        short idSelRival;

        oDAO = OcorrenciaDAO.getInstance();
        gDAO = GolDAO.getInstance();

        if (idSelecao == j.getSelecaoByIdselecaoi().getId())
        {
            idSelRival = j.getSelecaoByIdselecaoii().getId();
        }
        else
        {
            idSelRival = j.getSelecaoByIdselecaoi().getId();
        }

        if (oDAO.queryHaOcorrenciaParaJogo(j.getId()) != 0)
        {
            numGolsSelI = gDAO.queryNumGolsJogoSelecao(j.getId(), idSelecao, idSelRival);
            numGolsSelII = gDAO.queryNumGolsJogoSelecao(j.getId(), idSelRival, idSelecao);

            if (numGolsSelI > numGolsSelII)
            {
                resultado = 3;
            }
            else if (numGolsSelI == numGolsSelII)
            {
                resultado = 1;
            }
            else
            {
                resultado = 0;
            }
        }
        else
        {
            resultado = -1;
        }

        return resultado;
    }

    public String queryInfoJogoById(Short idJogo)
    {
        String infoJogo;
        JogoDAO jDao;
        Jogo j;

        SGCMFSessionManager.abrirSessao();
        jDao = JogoDAO.getInstance();
        j = jDao.queryInfoJogoById(idJogo);

        infoJogo = SGCMFDate.toStringDataHoraFormatoBrasil(j.getDatahora()) + " | "
                + j.getSelecaoByIdselecaoi().getPais() + " vs " + j.getSelecaoByIdselecaoii().getPais();
        SGCMFSessionManager.fecharSessao();

        return infoJogo;
    }
    
    public int pesquisarQtdeJogosDisputados(Short idSelecao)
    {
        OcorrenciaDAO oDao;
        int qtdeJogosDisputados;
        SGCMFSessionManager.abrirSessao();
        oDao = OcorrenciaDAO.getInstance();
        qtdeJogosDisputados = oDao.queryQtdeJogosDisputados(idSelecao);
        SGCMFSessionManager.fecharSessao();
        
        return qtdeJogosDisputados;
    }
}
