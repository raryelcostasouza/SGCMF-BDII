package sgcmf.view.comiteGestor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrSelecao;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.table.JTableSGCMF;

public class LimConsultaSelecao extends JDialog
{
    private CtrSelecao ctrSelecao;
    private JRadioButton jrbPais;
    private JRadioButton jrbNomeTecnico;
    protected JTableSGCMF jt;
    private JTextField jtfSearchBox;
    protected JPanel mainPanel;

    public LimConsultaSelecao(CtrSelecao ctrSelecao)
    {
        setIconImage(SGCMFIcons.SELECAO.getImage());
        this.ctrSelecao = ctrSelecao;

        setTitle("Consulta Seleção");
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

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbPais);
        bg.add(jrbNomeTecnico);

        northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
        northWestPanel.add(jrbPais);
        northWestPanel.add(jrbNomeTecnico);

        return northWestPanel;
    }

    private JPanel montaNorthEastPanel()
    {
        JPanel northEastPanel = new JPanel();
        northEastPanel.setBorder(BorderFactory.createTitledBorder("Busca:"));

        jtfSearchBox = new JTextField(15);
        jtfSearchBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pesquisa(jtfSearchBox.getText());
            }
        });

        northEastPanel.add(jtfSearchBox);

        return northEastPanel;
    }

    private JScrollPane montaCenterPanel()
    {
        String[] nomesColunas =
        {
            "ID", "País", "Técnico", "Bandeira"
        };

        jt = new JTableSGCMF(null, nomesColunas);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    public void ativaTela()
    {
        String[][] dadosSelecoes;

        dadosSelecoes = ctrSelecao.querySelecaoTodos();
        jt.preencheTabela(dadosSelecoes);

        setVisible(true);
    }

    private void resetCamposInterface()
    {
        jrbPais.setSelected(true);
        jtfSearchBox.setText("");
    }

    private void pesquisa(String chavePesquisa)
    {
        String[][] dadosSelecoes;

        if (jrbPais.isSelected())
        {
            dadosSelecoes = ctrSelecao.querySelecaoByNomePais(chavePesquisa);
        }
        else
        {
            dadosSelecoes = ctrSelecao.querySelecaoByNomeTecnico(chavePesquisa);
        }
        jt.preencheTabela(dadosSelecoes);
    }
}
