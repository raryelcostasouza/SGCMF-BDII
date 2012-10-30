/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrMain;
import sgcmf.control.CtrSelecao;
import sgcmf.control.CtrTecnico;
import sgcmf.view.UtilView;
import sgcmf.view.table.JTableSGCMF;

/**
 *
 * @author Helio
 */
public class LimConsultarSelecao extends JDialog
{
    private String[][] dadosSelecoes;
    private CtrTecnico ctrTecnico;
    private CtrMain ctrMain;
    private CtrSelecao ctrSelecao; 

    public LimConsultarSelecao(CtrTecnico ctrTecnico)
    {
        this.ctrTecnico = ctrTecnico;
        this.ctrMain = ctrTecnico.getCtrMain();
        this.ctrSelecao = ctrMain.getCtrSelecao();
        setVisible(false);
        setSize(380, 300);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Pesquisar Seleção");
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(montaPanel());
    }

    private JPanel montaPanel()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(1, 2));
        String[] columnNames =
        {
            "País", "Técnico", "Bandeira"
        };

        JLabel jl = new JLabel("Nome do País:");
        UtilView.alinhaLabel(jl);
        JTextField jtf = new JTextField(15);

        JTableSGCMF jt = new JTableSGCMF(null, columnNames);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JButton jb = new JButton("Selecionar");

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jl));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtf));
        jpAux.setBorder(BorderFactory.createTitledBorder("Buscar por:"));

        jpPrincipal.add(jpAux, BorderLayout.NORTH);
        jpPrincipal.add(jsp, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jb), BorderLayout.SOUTH);
        return jpPrincipal;
    }

    public void ativarTela()
    {
        setVisible(true);
        
    }
}
