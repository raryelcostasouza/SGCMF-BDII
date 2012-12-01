package sgcmf.view.tabela;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sgcmf.control.CtrTabelaCampeonato;
import sgcmf.model.other.SGCMFIcons;

public class LimTabelaCampeonato extends JDialog
{
    private PanelTabelaCampeonato panelTabela;
    private CtrTabelaCampeonato ctrTabelaCampeonato;
    private String[] nomesColunas;

    public LimTabelaCampeonato(CtrTabelaCampeonato ctrTabelaCampeonato)
    {
        this.ctrTabelaCampeonato = ctrTabelaCampeonato;
        setIconImage(SGCMFIcons.TABELA.getImage());
        setTitle("Tabela do Campeonato");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        setModal(true);

        panelTabela = new PanelTabelaCampeonato();
        add(panelTabela);
        pack();
        setLocationRelativeTo(null);

        nomesColunas = new String[]
        {
            "Data/Hora", "Local", "Seleção I", "Placar", "Seleção II"
        };
    }

    private void preencheJTable(JTable jt, Object[][] dados)
    {
        DefaultTableModel dfm = (DefaultTableModel) jt.getModel();
        dfm.setDataVector(dados, nomesColunas);
    }

    private void preencheClassificacao(JTextField jtf1, JTextField jtf2, String[] selecoesClassificadas)
    {
        if (selecoesClassificadas != null)
        {
            jtf1.setText(selecoesClassificadas[0]);
            jtf2.setText(selecoesClassificadas[1]);
        }
        else
        {
            jtf1.setText("");
            jtf2.setText("");
        }
    }

    public void ativaTela()
    {
        if (ctrTabelaCampeonato.getPrecisaAtualizarTabela())
        {
            preencheJTable(panelTabela.getJtGrupoA(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupoParaTabelaCampeonato("A"));
            preencheJTable(panelTabela.getJtGrupoB(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupoParaTabelaCampeonato("B"));
            preencheJTable(panelTabela.getJtGrupoC(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupoParaTabelaCampeonato("C"));
            preencheJTable(panelTabela.getJtGrupoD(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupoParaTabelaCampeonato("D"));
            preencheJTable(panelTabela.getJtGrupoE(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupoParaTabelaCampeonato("E"));
            preencheJTable(panelTabela.getJtGrupoF(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupoParaTabelaCampeonato("F"));
            preencheJTable(panelTabela.getJtGrupoG(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupoParaTabelaCampeonato("G"));
            preencheJTable(panelTabela.getJtGrupoH(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupoParaTabelaCampeonato("H"));

            preencheClassificacao(panelTabela.getJtf1GrupoA(),
                                  panelTabela.getJtf2GrupoA(),
                                  ctrTabelaCampeonato.getCtrJogo().getClassificadosGrupo("A"));
            preencheClassificacao(panelTabela.getJtf1GrupoB(),
                                  panelTabela.getJtf2GrupoB(),
                                  ctrTabelaCampeonato.getCtrJogo().getClassificadosGrupo("B"));
            preencheClassificacao(panelTabela.getJtf1GrupoC(),
                                  panelTabela.getJtf2GrupoC(),
                                  ctrTabelaCampeonato.getCtrJogo().getClassificadosGrupo("C"));
            preencheClassificacao(panelTabela.getJtf1GrupoD(),
                                  panelTabela.getJtf2GrupoD(),
                                  ctrTabelaCampeonato.getCtrJogo().getClassificadosGrupo("D"));
            preencheClassificacao(panelTabela.getJtf1GrupoE(),
                                  panelTabela.getJtf2GrupoE(),
                                  ctrTabelaCampeonato.getCtrJogo().getClassificadosGrupo("E"));
            preencheClassificacao(panelTabela.getJtf1GrupoF(),
                                  panelTabela.getJtf2GrupoF(),
                                  ctrTabelaCampeonato.getCtrJogo().getClassificadosGrupo("F"));
            preencheClassificacao(panelTabela.getJtf1GrupoG(),
                                  panelTabela.getJtf2GrupoG(),
                                  ctrTabelaCampeonato.getCtrJogo().getClassificadosGrupo("G"));
            preencheClassificacao(panelTabela.getJtf1GrupoH(),
                                  panelTabela.getJtf2GrupoH(),
                                  ctrTabelaCampeonato.getCtrJogo().getClassificadosGrupo("H"));
           ctrTabelaCampeonato.setPrecisaAtualizarTabela(false);
        }

        setVisible(true);
    }
}
