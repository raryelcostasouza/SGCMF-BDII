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
import sgcmf.control.CtrSelecao;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.util.DefaultTableModelSGCMF;
import sgcmf.view.util.JTableSGCMF;
import sgcmf.view.util.JLabelTableCellRenderer;

public class LimConsultaSelecao extends JDialog
{
    private CtrSelecao ctrSelecao;
    private JRadioButton jrbPais;
    private JRadioButton jrbNomeTecnico;
    private JRadioButton jrbGrupo;
    private JComboBox jcb;
    protected JTableSGCMF jt;
    private JTextField jtfSearchBox;
    protected JPanel mainPanel;
    private JPanel northEastPanel;
    private final String nameCardPanelSearchBox = "SEARCH_BOX";
    private final String nameCardPanelComboBox = "COMBO_BOX";

    public LimConsultaSelecao(CtrSelecao ctrSelecao)
    {
        setIconImage(SGCMFIcons.SELECAO.getImage());
        this.ctrSelecao = ctrSelecao;

        setTitle("Consulta Seleção");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(montaMainPanel());
        setSize(600, 600);
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

        mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(montaNorthPanel(), BorderLayout.NORTH);

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

        jrbPais = new JRadioButton("País");
        jrbPais.setSelected(true);
        jrbNomeTecnico = new JRadioButton("Nome Técnico");
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
        jrbPais.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                trocarNorthEastPanelParaSearchBox();
            }
        });
        jrbNomeTecnico.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                trocarNorthEastPanelParaSearchBox();
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbPais);
        bg.add(jrbNomeTecnico);
        bg.add(jrbGrupo);

        northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
        northWestPanel.add(jrbGrupo);
        northWestPanel.add(jrbPais);
        northWestPanel.add(jrbNomeTecnico);

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

        recarregaTodasSelecoes();
        jtfSearchBox.setText("");
    }

    private JScrollPane montaCenterPanel()
    {
        String[] nomesColunas =
        {
            "ID", "País", "Grupo", "Técnico"
        };

        jt = new JTableSGCMF(null, nomesColunas);
        jt.setModel(new DefaultTableModelSGCMF(null, nomesColunas));
        jt.setDefaultRenderer(JLabel.class, new JLabelTableCellRenderer());
        jt.setRowHeight(32);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    public void ativaTela()
    {
        recarregaTodasSelecoes();
        setVisible(true);
    }

    private void recarregaTodasSelecoes()
    {
        Object[][] dadosSelecoes;

        dadosSelecoes = ctrSelecao.querySelecaoTodos();
        jt.preencheTabela(dadosSelecoes);
    }

    private void resetCamposInterface()
    {
        jrbPais.setSelected(true);
        trocarNorthEastPanelParaSearchBox();
        jtfSearchBox.setText("");
    }

    private void pesquisa(String chavePesquisa)
    {
        Object[][] dadosSelecoes;

        if (jrbPais.isSelected())
        {
            dadosSelecoes = ctrSelecao.querySelecaoByNomePais(chavePesquisa);
        }
        else if (jrbNomeTecnico.isSelected())
        {
            dadosSelecoes = ctrSelecao.querySelecaoByNomeTecnico(chavePesquisa);
        }
        else
        {
            dadosSelecoes = ctrSelecao.querySelecaoByGrupo(chavePesquisa);
        }
        jt.preencheTabela(dadosSelecoes);
    }
}
