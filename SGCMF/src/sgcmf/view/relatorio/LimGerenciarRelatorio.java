/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.relatorio;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sgcmf.control.CtrRelatorio;
import sgcmf.model.other.SGCMFIcons;

/**
 *
 * @author Helio
 */
public class LimGerenciarRelatorio extends JDialog
{
    private PanelRelatorioSelecao panelRelatorioSelecao;
    private PanelRelatorioJogo panelRelatorioJogo;

    public LimGerenciarRelatorio(CtrRelatorio ctrRelatorio)
    {
        setIconImage(SGCMFIcons.RELATORIO.getImage());
        panelRelatorioSelecao = new PanelRelatorioSelecao(ctrRelatorio);
        panelRelatorioJogo = new PanelRelatorioJogo();
        setTitle("Relatórios");

        setResizable(false);
        setModal(true);

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(montaPanel());
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                panelRelatorioSelecao.limparTela();
            }
        });
    }

    public JTabbedPane montaPanel()
    {
        final JTabbedPane jtp = new JTabbedPane();
        jtp.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                String tituloAba = jtp.getTitleAt(jtp.getSelectedIndex());
                if (tituloAba.equals("Relatório para Seleção"))
                {
                    setSize(600, 300);
                }
                else if (tituloAba.equals("Relatório para Jogo"))
                {
                    setSize(590, 400);
                }
                setLocationRelativeTo(null);
            }
        });
        jtp.add(panelRelatorioSelecao, "Relatório para Seleção");
        jtp.add(panelRelatorioJogo, "Relatório para Jogo");

        return jtp;
    }
}
