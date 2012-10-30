/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.view.UtilView;
import sgcmf.view.table.DefaultTableModelSGCMF;
import sgcmf.view.table.JTableSGCMF;

/**
 *
 * @author Helio
 */
public class LimConsultarSelecao extends JDialog
{
    public LimConsultarSelecao()
    {
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
        String[] columnNames = {"País", "Técnico", "Bandeira"};
        
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
}
