package sgcmf.view.comiteGestor;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrJogo;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.table.JTableSGCMF;

public class LimConsultarJogo extends JDialog
{
    protected JPanel mainPanel;
    protected JTableSGCMF jt;
    private CtrJogo ctrJogo;
    private JPanel northEastPanel;
    private JRadioButton jrbCidade;
    private JRadioButton jrbEstadio;
    private JRadioButton jrbSelecao;
    private JTextField jtfSearchBox;

    public LimConsultarJogo(CtrJogo ctrJogo)
    {
        setIconImage(SGCMFIcons.JOGO.getImage());
        this.ctrJogo = ctrJogo;
        setTitle("Consulta Jogo");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        add(montaMainPanel());
        pack();

        setModal(true);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                resetCamposInterface();
            }
        });
    }

    private JPanel montaMainPanel()
    {
        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(montaNorthPanel(), BorderLayout.NORTH);
        mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel montaNorthPanel()
    {
        JPanel northPanel = new JPanel(new BorderLayout());

        northPanel.add(montaNorthEastPanel(), BorderLayout.EAST);
        northPanel.add(montaNorthWestPanel(), BorderLayout.WEST);

        return northPanel;
    }

    private JPanel montaNorthWestPanel()
    {
        JPanel northWestPanel = new JPanel();

        jrbCidade = new JRadioButton("Cidade");
        jrbEstadio = new JRadioButton("Estádio");
        jrbSelecao = new JRadioButton("Seleção");
        jrbSelecao.setSelected(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbCidade);
        bg.add(jrbEstadio);
        bg.add(jrbSelecao);

        northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
        northWestPanel.add(jrbCidade);
        northWestPanel.add(jrbEstadio);
        northWestPanel.add(jrbSelecao);

        return northWestPanel;
    }
    
    private JPanel montaNorthEastPanel()
    {
        JPanel northEastPanelSearchBox = new JPanel();
        northEastPanelSearchBox.setBorder(BorderFactory.createTitledBorder("Busca:"));

        jtfSearchBox = new JTextField(15);
        jtfSearchBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pesquisa(jtfSearchBox.getText());
            }
        });

        northEastPanelSearchBox.add(jtfSearchBox);
        return northEastPanelSearchBox;
    }

    private JScrollPane montaCenterPanel()
    {
        String[] nomesColunas =
        {
            "ID", "Data/Hora", "Cidade", "Estádio", "Seleção I", "Seleção II"
        };

        jt = new JTableSGCMF(null, nomesColunas);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    public void ativaTela()
    {
        String[][] dadosJogos;

        dadosJogos = ctrJogo.queryJogoTodos();
        jt.preencheTabela(dadosJogos);

        setVisible(true);
    }

    private void resetCamposInterface()
    {
        jrbSelecao.setSelected(true);
        jtfSearchBox.setText("");
    }

    private void pesquisa(String chave)
    {
        String[][] dadosJogo;

        if (jrbSelecao.isSelected())
        {
            dadosJogo = ctrJogo.queryJogoBySelecao(chave);
        }
        else if (jrbCidade.isSelected())
        {
            dadosJogo = ctrJogo.queryJogoByCidade(chave);
        }
        else
        {
            dadosJogo = ctrJogo.queryJogoByEstadio(chave);
        }
        jt.preencheTabela(dadosJogo);
    }
}
