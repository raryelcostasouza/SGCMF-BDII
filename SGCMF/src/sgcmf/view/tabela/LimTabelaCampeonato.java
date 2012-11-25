package sgcmf.view.tabela;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sgcmf.control.CtrTabelaCampeonato;

public class LimTabelaCampeonato extends JDialog
{
    private PanelTabelaCampeonato panelTabela;
    private CtrTabelaCampeonato ctrTabelaCampeonato;
    private String[] nomesColunas;

    public LimTabelaCampeonato(CtrTabelaCampeonato ctrTabelaCampeonato)
    {
        this.ctrTabelaCampeonato = ctrTabelaCampeonato;
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
        dfm.setDataVector(dados,nomesColunas);
    }

    public void ativaTela()
    {
        ctrTabelaCampeonato.getCtrJogo().getClassificadosGrupo("A");
        preencheJTable(panelTabela.getJtGrupoA(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupo("A"));
        preencheJTable(panelTabela.getJtGrupoB(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupo("B"));
        preencheJTable(panelTabela.getJtGrupoC(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupo("C"));
        preencheJTable(panelTabela.getJtGrupoD(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupo("D"));
        preencheJTable(panelTabela.getJtGrupoE(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupo("E"));
        preencheJTable(panelTabela.getJtGrupoF(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupo("F"));
        preencheJTable(panelTabela.getJtGrupoG(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupo("G"));
        preencheJTable(panelTabela.getJtGrupoH(), ctrTabelaCampeonato.getCtrJogo().queryJogoByGrupo("H"));
        
        setVisible(true);
    }
}
