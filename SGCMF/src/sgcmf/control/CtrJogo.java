package sgcmf.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.GolDAO;
import sgcmf.model.dao.JogoDAO;
import sgcmf.model.dao.OcorrenciaDAO;
import sgcmf.model.dao.SelecaoDAO;
import sgcmf.model.hibernate.Jogo;
import sgcmf.model.hibernate.Selecao;
import sgcmf.model.other.AproveitamentoSelecao;
import sgcmf.model.other.ResultadoGolsSelecao;
import sgcmf.model.other.ResultadoSelecao;
import sgcmf.model.other.SGCMFDate;
import sgcmf.view.comiteGestor.LimConsultarJogo;

public class CtrJogo
{
    private LimConsultarJogo limConsultarJogo;
    private CtrMain ctrMain;

    public CtrJogo(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        this.limConsultarJogo = new LimConsultarJogo(this);
        
    }

    public void ativaLimConsultarJogo()
    {
        limConsultarJogo.ativaTela();
    }   
    
    public Object[][] queryJogoTodos()
    {
        JogoDAO jDAO;
        ArrayList<Jogo> alJogos;
        Object[][] dadosJogos;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();

        alJogos = jDAO.listaTodos();
        dadosJogos = arrayList2StringMatrix(alJogos);

        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }

    public Object[][] queryJogoBySelecao(String nomeSelecao)
    {
        JogoDAO jDAO;
        Object[][] dadosJogos;
        ArrayList<Jogo> alJogo;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();

        alJogo = jDAO.queryJogoBySelecao(nomeSelecao);
        dadosJogos = arrayList2StringMatrix(alJogo);

        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }

    public Object[][] queryJogoByCidade(String cidade)
    {
        JogoDAO jDAO;
        Object[][] dadosJogos;
        ArrayList<Jogo> alJogo;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();

        alJogo = jDAO.queryJogoByCidade(cidade);
        dadosJogos = arrayList2StringMatrix(alJogo);

        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }

    public Object[][] queryJogoByEstadio(String estadio)
    {
        JogoDAO jDAO;
        Object[][] dadosJogos;
        ArrayList<Jogo> alJogo;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();

        alJogo = jDAO.queryJogoByEstadio(estadio);
        dadosJogos = arrayList2StringMatrix(alJogo);

        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }

    public Object[][] queryJogoByGrupoParaConsultarJogo(String grupo)
    {
        JogoDAO jDAO;
        Object[][] dadosJogos;
        ArrayList<Jogo> alJogo;

        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();
        alJogo = jDAO.queryJogoByGrupo(grupo);

        dadosJogos = arrayList2StringMatrix(alJogo);
        SGCMFSessionManager.fecharSessao();

        return dadosJogos;
    }

    public String[][] queryJogoByGrupoParaTabelaCampeonato(String grupo)
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

    private Object[][] arrayList2StringMatrix(ArrayList<Jogo> alJogo)
    {
        Object[][] dadosJogos;
        Jogo j;
        Selecao s1, s2;

        dadosJogos = new Object[alJogo.size()][7];
        for (int i = 0; i < alJogo.size(); i++)
        {
            j = alJogo.get(i);
            dadosJogos[i][0] = String.valueOf(j.getId());
            dadosJogos[i][1] = SGCMFDate.toStringDataHoraFormatoBrasil(j.getDatahora());
            dadosJogos[i][2] = j.getCidade();
            dadosJogos[i][3] = j.getNomeestadio();
            dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoi().getGrupo());
            s1 = j.getSelecaoByIdselecaoi();
            s2 = j.getSelecaoByIdselecaoii();
            dadosJogos[i][5] = new JLabel(s1.getPais(), new ImageIcon(s1.getCaminhoimgbandeira()), JLabel.LEFT);
            dadosJogos[i][6] = new JLabel(s2.getPais(), new ImageIcon(s2.getCaminhoimgbandeira()), JLabel.LEFT);
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

    public String[] getClassificadosGrupo(String grupo)
    {
        OcorrenciaDAO oDAO;
        ResultadoSelecao[] resultadoSelecoesGrupo;
        String[] strSelecoesClassificadas;

        SGCMFSessionManager.abrirSessao();
        oDAO = OcorrenciaDAO.getInstance();

        //somente gera a classificação se já ocorreu algum jogo do grupo
        if (oDAO.queryAlgumJogoDoGrupoOcorreu(grupo) != 0)
        {
            resultadoSelecoesGrupo = calculaResultadoSelecoesDoGrupo(grupo);
            Arrays.sort(resultadoSelecoesGrupo);

            strSelecoesClassificadas = new String[2];
            strSelecoesClassificadas[0] = resultadoSelecoesGrupo[3].getSelecao().getPais();
            strSelecoesClassificadas[1] = resultadoSelecoesGrupo[2].getSelecao().getPais();
        }
        else
        {
            strSelecoesClassificadas = null;
        }
        SGCMFSessionManager.fecharSessao();
        return strSelecoesClassificadas;
    }

    private ResultadoSelecao[] calculaResultadoSelecoesDoGrupo(String grupo)
    {
        ArrayList<Selecao> selecoesGrupo;
        SelecaoDAO sDAO;
        ResultadoSelecao[] resultadoSelecoesGrupo;
        ResultadoGolsSelecao objRGS;
        int i;
        int numPontos;

        sDAO = SelecaoDAO.getInstance();
        selecoesGrupo = sDAO.querySelecaoByGrupo(grupo);

        i = 0;
        resultadoSelecoesGrupo = new ResultadoSelecao[4];
        for (Selecao s : selecoesGrupo)
        {
            numPontos = calculaPontosSelecao(s);
            objRGS = ctrMain.getCtrGol().calculaResultadoGolsSelecao(s);
            resultadoSelecoesGrupo[i] = new ResultadoSelecao(s, numPontos, objRGS.getSaldoGols(),
                                                             objRGS.getNumGolsMarcados());
            i++;
        }
        return resultadoSelecoesGrupo;
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
    
    public AproveitamentoSelecao calculaNumVitoriasDerrotaEmpate(Short idSelecao)
    {
        ArrayList<Jogo> jogosSelecao;
        JogoDAO jDAO;
        int resultado;
        int derrota;
        int empate;
        int vitoria;
        int jogosDisputados;
        float aproveitamento;
        AproveitamentoSelecao objAproveitamento;

        jDAO = JogoDAO.getInstance();
        SGCMFSessionManager.abrirSessao();
        jogosSelecao = jDAO.queryJogoByIdSelecao(idSelecao);

        vitoria = 0;
        derrota = 0;
        empate = 0;
        jogosDisputados = 0;
        for (Jogo j : jogosSelecao)
        {
            resultado = resultadoJogo(j, idSelecao);
            if (resultado != -1)
            {
                if (resultado == 3)
                {
                    vitoria++;
                }
                else if (resultado == 1)
                {
                    empate++;
                }
                else
                {
                    derrota++;
                }
                jogosDisputados++;
            }

        }
        aproveitamento = (vitoria * 100 + empate * 33) / (float) jogosDisputados;

        objAproveitamento = new AproveitamentoSelecao(jogosDisputados, vitoria, derrota, empate, aproveitamento);
        SGCMFSessionManager.fecharSessao();
        return objAproveitamento;
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
}
