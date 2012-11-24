package sgcmf.view.tabela;

import javax.swing.JDialog;
import sgcmf.control.CtrTabelaCampeonato;

public class LimTabelaCampeonato extends JDialog
{
    private PanelTabelaCampeonato panelTabela;
    private CtrTabelaCampeonato ctrTabelaCampeonato;
    
    public LimTabelaCampeonato(CtrTabelaCampeonato ctrTabelaCampeonato)
    {
        this.ctrTabelaCampeonato = ctrTabelaCampeonato;
        setTitle("Tabela do Campeonato");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setModal(true);
        
        panelTabela = new PanelTabelaCampeonato();
        add(panelTabela);
        pack();
        setLocationRelativeTo(null);        
    }
}
