package sgcmf.view.comiteGestor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrJogador;
import sgcmf.view.table.JTableSGCMF;
import sgcmf.view.tecnico.ISelecionarJogador;

public class LimBuscarJogador extends JDialog
{
    private CtrJogador ctrJogador;
    private JTableSGCMF jt;
    private ISelecionarJogador isj;
    private Short idJogo;

    public LimBuscarJogador(CtrJogador ctrJogador)
    {
        this.ctrJogador = ctrJogador;

        setTitle("Selecionar Jogador");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(montaMainPanel());
        setModal(true);
        setSize(500, 400);
        setLocationRelativeTo(null);
    }

    private JPanel montaMainPanel()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(montaNorthPanel(), BorderLayout.NORTH);
        mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(montaSouthPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel montaNorthPanel()
    {
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        northPanel.setBorder(BorderFactory.createTitledBorder("Busca:"));

        JLabel jlNomeJogador = new JLabel("Nome do jogador:");
        final JTextField jtfSearchBox = new JTextField(15);
        jtfSearchBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pesquisa(jtfSearchBox.getText());
            }
        });

        northPanel.add(jlNomeJogador);
        northPanel.add(jtfSearchBox);

        return northPanel;
    }

    private JScrollPane montaCenterPanel()
    {
        String[] nomesColunas =
        {
            "ID", "Seleção", "Número da Camisa", "Nome", "Posição"
        };

        jt = new JTableSGCMF(null, nomesColunas);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    private JPanel montaSouthPanel()
    {
        JPanel southPanel = new JPanel();

        JButton jbSelecionar = new JButton("Selecionar");
        jbSelecionar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selecionaJogador();
            }
        });

        southPanel.add(jbSelecionar);

        return southPanel;
    }

    public void ativaTela(ISelecionarJogador isj, Short idJogo)
    {
        String[][] dadosJogador;

        this.isj = isj;
        this.idJogo = idJogo;

        dadosJogador = ctrJogador.queryJogadoresEmCampo(idJogo);
        jt.preencheTabela(dadosJogador);

        setVisible(true);
    }

    public void ativaTelaSelecionaOutroJogadorSelecao(ISelecionarJogador isj, Short idJogo, Short idSelecao, Short idJogador)
    { 
        String[][] dadosJogador;

        this.isj = isj;
        this.idJogo = idJogo;

        dadosJogador = ctrJogador.queryOutrosJogadoresEmCampoSelecao(idJogo, idSelecao, idJogador);
        jt.preencheTabela(dadosJogador);

        setVisible(true);
    }
    
    public void ativaTelaSelecionaJogadorReservaMesmaSelecao(ISelecionarJogador isj, Short idJogo, Short idSelecao)
    {
        String[][] dadosJogador;

        this.isj = isj;
        this.idJogo = idJogo;

        dadosJogador = ctrJogador.queryJogadoresReservaMesmaSelecao(idJogo, idSelecao);
        jt.preencheTabela(dadosJogador);

        setVisible(true);
    }

    private void pesquisa(String chave)
    {
        String[][] dadosJogador;

        dadosJogador = ctrJogador.queryJogadorByNome(chave);
        jt.preencheTabela(dadosJogador);
    }

    private void selecionaJogador()
    {
        int linhaSelecionada;
        Short idJogador;

        linhaSelecionada = jt.getSelectedRow();
        if (linhaSelecionada != -1)
        {
            idJogador = Short.parseShort((String) jt.getValueAt(linhaSelecionada, 0));
            isj.jogadorSelecionado(idJogador);
            setVisible(false);
        }
    }
}
