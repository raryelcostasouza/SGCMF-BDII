/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrJogador;
import sgcmf.control.CtrMain;
import sgcmf.control.CtrTecnico;
import sgcmf.view.UtilView;
import sgcmf.view.table.JTableSGCMF;

/**
 *
 * @author Helio
 */
public class PanelConsultarJogador extends JPanel
{
    private CtrMain ctrMain;
    private CtrJogador ctrJogador;
    private JTableSGCMF jt;
    private JRadioButton jrbNome;
    private JRadioButton jrbPosicao;
    private JTextField jtfPesquisar;

    public PanelConsultarJogador(CtrTecnico ctrTecnico)
    {
        ctrMain = ctrTecnico.getCtrMain();
        ctrJogador = ctrMain.getCtrJogador();
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

        jtfPesquisar = new JTextField(15);
        jtfPesquisar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pesquisa(jtfPesquisar.getText());
            }
        });

        jrbNome = new JRadioButton("Nome");
        jrbNome.setSelected(true);
        jrbPosicao = new JRadioButton("Posição");

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
            "ID", "Número Camisa", "Nome", "Data Nascimento", "Altura", "Posição", "Seleção", "Titular"
        };
        jt = new JTableSGCMF(null, nomeColunas);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    public void ativaTela()
    {
        String[][] dadosJogadores;

        dadosJogadores = ctrJogador.queryAllDataJogadorTodos();
        jt.preencheTabela(dadosJogadores);
    }

    public void limparCampos()
    {
        jrbNome.setSelected(true);
        jtfPesquisar.setText("");
    }

    private void pesquisa(String chavePesquisa)
    {
        String[][] dadosJogadores;
        if (jrbNome.isSelected())
        {
            dadosJogadores = ctrJogador.queryAllDataJogadorByNome(chavePesquisa);
        }
        else
        {
            dadosJogadores = ctrJogador.queryAllDataJogadorByPosicao(chavePesquisa);
        }
        jt.preencheTabela(dadosJogadores);
    }
}
