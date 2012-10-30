/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.view.UtilView;
import sgcmf.view.table.JTableSGCMF;

/**
 *
 * @author Helio
 */
public class PanelConsultarJogador extends JPanel
{
    public PanelConsultarJogador()
    {
        setLayout(new BorderLayout());
        montaPainelPrincipal();
    }

    private void montaPainelPrincipal()
    {
        JPanel jpNorth = montaPainelNorte();
        JScrollPane jpCenter = montaPainelCentral();
        this.add(jpNorth, BorderLayout.NORTH);
        this.add(jpCenter, BorderLayout.CENTER);
    }

    private JPanel montaPainelNorte()
    {
        JPanel jpNorth = new JPanel(new BorderLayout());
        JPanel jpEsquerda = new JPanel();
        JPanel jpDireita = new JPanel();

        JTextField jtfPesquisar = new JTextField(15);

        JRadioButton jrbNome = new JRadioButton("Nome");
        jrbNome.setSelected(true);
        JRadioButton jrbPosicao = new JRadioButton("Posição");

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbNome);
        bg.add(jrbPosicao);

        jpEsquerda.add(jrbNome);
        jpEsquerda.add(jrbPosicao);
        jpEsquerda.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));

        jpDireita.add(UtilView.putComponentInFlowLayoutPanel(jtfPesquisar));
        jpDireita.setBorder(BorderFactory.createTitledBorder("Buscar:"));

        jpNorth.add(jpEsquerda, BorderLayout.WEST);
        jpNorth.add(jpDireita, BorderLayout.EAST);

        return jpNorth;
    }

    private JScrollPane montaPainelCentral()
    {
        String[] nomeColunas =
        {
            "ID", "Número Camisa", "Nome", "Data Nascimento", "Altura", "Posição", "Seleção"
        };
        JTableSGCMF jt = new JTableSGCMF(null, nomeColunas);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }
}
