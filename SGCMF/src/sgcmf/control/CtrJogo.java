package sgcmf.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.CartaoDAO;
import sgcmf.model.dao.FaltaDAO;
import sgcmf.model.dao.GolDAO;
import sgcmf.model.dao.JogoDAO;
import sgcmf.model.dao.OcorrenciaDAO;
import sgcmf.model.dao.SelecaoDAO;
import sgcmf.model.hibernate.Jogo;
import sgcmf.model.hibernate.Selecao;
import sgcmf.model.other.AproveitamentoSelecao;
import sgcmf.model.other.ModelRelatorioJogo;
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
        int[] placar;
        dadosJogos = new String[alJogo.size()][5];
        for (int i = 0; i < alJogo.size(); i++)
        {

            j = alJogo.get(i);
            dadosJogos[i][0] = SGCMFDate.toStringDataHoraFormatoBrasil(j.getDatahora());
            dadosJogos[i][1] = j.getCidade();
            dadosJogos[i][2] = String.valueOf(j.getSelecaoByIdselecaoi().getPais());
            placar = geraPlacarJogo(j);
            if (placar != null)
            {
                dadosJogos[i][3] = placar[0] + " x " + placar[1];
            }
            else
            {
                dadosJogos[i][3] = "";
            }

            dadosJogos[i][4] = String.valueOf(j.getSelecaoByIdselecaoii().getPais());
        }

        return dadosJogos;
    }

    private int[] geraPlacarJogo(Jogo j)
    {
        int numGolsSelI;
        int numGolsSelII;
        int[] placar = new int[2];

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

            placar[0] = numGolsSelI;
            placar[1] = numGolsSelII;
        }
        else
        {
            return null;
        }

        return placar;
    }

    public void geraRelatorioJogo(Short idJogo)
    {
        int[] placar;
        int[] cartoesAmarelos;
        int[] cartoesVermelhos;
        int[] faltas;
        ModelRelatorioJogo mrljI;
        ModelRelatorioJogo mrljII;

        Jogo j = new Jogo();
        cartoesAmarelos = new int[2];
        cartoesVermelhos = new int[2];
        faltas = new int[2];

        JogoDAO jDAO;
        SGCMFSessionManager.abrirSessao();
        jDAO = JogoDAO.getInstance();
        jDAO.carregar(j, idJogo);
        placar = geraPlacarJogo(j);
        if (placar != null)
        {
            cartoesAmarelos = qtdeCartoesByCor(j, "Amarelo");
            cartoesVermelhos = qtdeCartoesByCor(j, "Vermelho");
            faltas = qtdeFaltas(j);
        }
        else
        {
            placar = new int[2];
            placar[0] = 0;
            cartoesAmarelos[0] = 0;
            cartoesVermelhos[0] = 0;
            faltas[0] = 0;
            placar[1] = 0;
            cartoesAmarelos[1] = 0;
            cartoesVermelhos[1] = 0;
            faltas[1] = 0;
        }

        mrljI = new ModelRelatorioJogo(placar[0], cartoesAmarelos[0], cartoesVermelhos[0], faltas[0],j.getSelecaoByIdselecaoi().getPais());
        mrljII = new ModelRelatorioJogo(placar[1], cartoesAmarelos[1], cartoesVermelhos[1], faltas[1],j.getSelecaoByIdselecaoii().getPais());
        SGCMFSessionManager.fecharSessao();
        
        montaRelatorioJogo(mrljI, mrljII);
    }

    private int[] qtdeFaltas(Jogo j)
    {
        int[] faltas;
        FaltaDAO faltaDAO;
        faltaDAO = FaltaDAO.getInstance();
        faltas = new int[2];
        faltas[0] = faltaDAO.queryQtdeFaltasByJogoBySelecao(j.getId(), j.getSelecaoByIdselecaoi().getId());
        faltas[1] = faltaDAO.queryQtdeFaltasByJogoBySelecao(j.getId(), j.getSelecaoByIdselecaoii().getId());

        return faltas;
    }

    private int[] qtdeCartoesByCor(Jogo j, String cor)
    {
        int[] cartoes;
        CartaoDAO cartaoDAO;
        cartaoDAO = CartaoDAO.getInstance();
        cartoes = new int[2];
        cartoes[0] = cartaoDAO.queryQtdeCartoesByJogoByCor(j.getId(), cor, j.getSelecaoByIdselecaoi().getId());
        cartoes[1] = cartaoDAO.queryQtdeCartoesByJogoByCor(j.getId(), cor, j.getSelecaoByIdselecaoii().getId());

        return cartoes;
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
            objRGS = ctrMain.getCtrGol().calculaResultadoGolsSelecao(s.getId());
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
        if (jogosDisputados == 0)
        {
            aproveitamento = 0;
        }
        else
        {
            aproveitamento = (vitoria * 100 + empate * 33) / (float) jogosDisputados;
        }

        objAproveitamento = new AproveitamentoSelecao(vitoria, derrota, empate, aproveitamento);
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
    
    private void montaRelatorioJogo(ModelRelatorioJogo mrjI, ModelRelatorioJogo mrjII)
    {
        ArrayList<ModelRelatorioJogo> lista = new ArrayList<ModelRelatorioJogo>();
        lista.add(mrjI);
        lista.add(mrjII);
        JRDataSource jrds = new JRBeanCollectionDataSource(lista);
        try
        {
            JasperReport jr = JasperCompileManager.compileReport("relatorio/RelatorioJogo.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(jr, null, jrds);
            JasperViewer.viewReport(jp, false);
        }
        catch (JRException ex)
        {
            Logger.getLogger(CtrRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
