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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrJogo;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.table.DefaultTableModelC5C6;
import sgcmf.view.table.JTableSGCMF;
import sgcmf.view.table.JLabelTableCellRenderer;

public class LimConsultarJogo extends JDialog
{
    protected JPanel mainPanel;
    protected JTableSGCMF jt;
    private CtrJogo ctrJogo;
    private JPanel northEastPanel;
    private JRadioButton jrbCidade;
    private JRadioButton jrbEstadio;
    private JRadioButton jrbSelecao;
    private JRadioButton jrbGrupo;
    private JTextField jtfSearchBox;
    private JComboBox jcb;
    private final String nameCardPanelSearchBox = "SEARCH_BOX";
    private final String nameCardPanelComboBox = "COMBO_BOX";

    public LimConsultarJogo(CtrJogo ctrJogo)
    {
        setIconImage(SGCMFIcons.JOGO.getImage());
        this.ctrJogo = ctrJogo;
        setTitle("Consulta Jogo");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        add(montaMainPanel());
        setSize(950, 600);

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
        jrbGrupo = new JRadioButton("Grupo");

        jrbGrupo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) northEastPanel.getLayout();
                cl.show(northEastPanel, nameCardPanelComboBox);

                pesquisa(String.valueOf(jcb.getSelectedItem()));
            }
        });
        jrbCidade.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                trocarNorthEastPanelParaSearchBox();
            }
        });
        jrbEstadio.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                trocarNorthEastPanelParaSearchBox();
            }
        });
        jrbSelecao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                trocarNorthEastPanelParaSearchBox();
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbCidade);
        bg.add(jrbEstadio);
        bg.add(jrbSelecao);
        bg.add(jrbGrupo);

        northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
        northWestPanel.add(jrbGrupo);
        northWestPanel.add(jrbCidade);
        northWestPanel.add(jrbEstadio);
        northWestPanel.add(jrbSelecao);

        return northWestPanel;
    }

    private JPanel montaNorthEastPanel()
    {
        northEastPanel = new JPanel(new CardLayout());
        northEastPanel.add(nameCardPanelSearchBox, montaNorthEastPanelSearchBox());
        northEastPanel.add(nameCardPanelComboBox, montaNorthEastPanelComboBox());
        return northEastPanel;
    }

    private JPanel montaNorthEastPanelSearchBox()
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

    private JPanel montaNorthEastPanelComboBox()
    {
        String[] opcoesJCB =
        {
            "A", "B", "C", "D", "E", "F", "G", "H"
        };
        JPanel northEastPanelComboBox = new JPanel();
        northEastPanelComboBox.setBorder(BorderFactory.createTitledBorder("Grupo:"));

        jcb = new JComboBox(opcoesJCB);
        jcb.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pesquisa(String.valueOf(jcb.getSelectedItem()));
            }
        });

        northEastPanelComboBox.add(jcb);
        return northEastPanelComboBox;
    }

    private void trocarNorthEastPanelParaSearchBox()
    {
        CardLayout cl = (CardLayout) northEastPanel.getLayout();
        cl.show(northEastPanel, nameCardPanelSearchBox);

        recarregaTodosJogos();
        jtfSearchBox.setText("");
    }

    private JScrollPane montaCenterPanel()
    {
        String[] nomesColunas =
        {
            "ID", "Data/Hora", "Cidade", "Estádio", "Grupo", "Seleção I", "Seleção II"
        };

        jt = new JTableSGCMF(null, nomesColunas);
        jt.setModel(new DefaultTableModelC5C6(null, nomesColunas));
        jt.setDefaultRenderer(JLabel.class, new JLabelTableCellRenderer());
        jt.setRowHeight(32);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    public void ativaTela()
    {
        recarregaTodosJogos();
        setVisible(true);
    }

    private void resetCamposInterface()
    {
        jrbSelecao.setSelected(true);
        jtfSearchBox.setText("");
    }

    private void recarregaTodosJogos()
    {
        Object[][] dadosJogos;

        dadosJogos = ctrJogo.queryJogoTodos();
        jt.preencheTabela(dadosJogos);
    }

    private void pesquisa(String chave)
    {
        Object[][] dadosJogo;

        if (jrbSelecao.isSelected())
        {
            dadosJogo = ctrJogo.queryJogoBySelecao(chave);
        }
        else if (jrbCidade.isSelected())
        {
            dadosJogo = ctrJogo.queryJogoByCidade(chave);
        }
        else if (jrbEstadio.isSelected())
        {
            dadosJogo = ctrJogo.queryJogoByEstadio(chave);
        }
        else
        {
            dadosJogo = ctrJogo.queryJogoByGrupoParaConsultarJogo(chave);
        }
        jt.preencheTabela(dadosJogo);
    }
}
