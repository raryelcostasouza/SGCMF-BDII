/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.relatorio;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sgcmf.model.other.SGCMFIcons;

/**
 *
 * @author Helio
 */
public class LimGerenciarRelatorio extends JDialog
{
    private PanelRelatorioSelecao panelRelatorioSelecao;

    public LimGerenciarRelatorio()
    {
        setIconImage(SGCMFIcons.RELATORIO.getImage());
        panelRelatorioSelecao = new PanelRelatorioSelecao();
        setTitle("Relatórios");
        
        //setModal(true);
        setVisible(true);
        
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(montaPanel());
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
                    setSize(750, 400);
                }
                else if (tituloAba.equals("Relatório para Jogo"))
                {
                    setSize(400, 400);
                }
                setLocationRelativeTo(null);
            }
        });
        jtp.add(panelRelatorioSelecao, "Relatório para Seleção");
        jtp.add(null,"Relatório para Jogo");

        return jtp;
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new LimGerenciarRelatorio();
            }
        });
    }
}
