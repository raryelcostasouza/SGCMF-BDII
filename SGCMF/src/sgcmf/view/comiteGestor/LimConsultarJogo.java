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
    private final String nameCardPanelSearchBox = "SEARCH_BOX";
    private final String nameCardPanelComboBox = "COMBO_BOX";
    private JRadioButton jrbTipoJogo;
    private JRadioButton jrbCidade;
    private JRadioButton jrbEstadio;
    private JRadioButton jrbSelecao;
    private JComboBox jcb;
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

        jrbTipoJogo = new JRadioButton("Tipo de Jogo");
        jrbCidade = new JRadioButton("Cidade");
        jrbEstadio = new JRadioButton("Estádio");
        jrbSelecao = new JRadioButton("Seleção");
        jrbSelecao.setSelected(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbTipoJogo);
        bg.add(jrbCidade);
        bg.add(jrbEstadio);
        bg.add(jrbSelecao);

        jrbTipoJogo.addActionListener(new ActionListener()
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

        northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
        northWestPanel.add(jrbTipoJogo);
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
            "Primeira Fase", "Oitavas de Final", "Quartas de final", "Semi-final", "Final", "Terceiro Lugar"
        };
        JPanel northEastPanelComboBox = new JPanel();
        northEastPanelComboBox.setBorder(BorderFactory.createTitledBorder("Tipo jogo:"));

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

    private JScrollPane montaCenterPanel()
    {
        String[] nomesColunas =
        {
            "ID", "Tipo do Jogo", "Data/Hora", "Cidade", "Estádio", "Seleção I", "Seleção II", "Prorrogacao"
        };

        jt = new JTableSGCMF(null, nomesColunas);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    private void trocarNorthEastPanelParaSearchBox()
    {
        CardLayout cl = (CardLayout) northEastPanel.getLayout();
        cl.show(northEastPanel, nameCardPanelSearchBox);
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
        jcb.setSelectedIndex(0);
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
        else if (jrbEstadio.isSelected())
        {
            dadosJogo = ctrJogo.queryJogoByEstadio(chave);
        }
        else
        {
            dadosJogo = ctrJogo.queryJogoByTipo(chave);
        }
        jt.preencheTabela(dadosJogo);
    }
}
