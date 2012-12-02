package sgcmf.view.comiteGestor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrJogador;
import sgcmf.view.table.JLabelTableCellRenderer;
import sgcmf.view.table.JTableSGCMF;
import sgcmf.view.table.DefaultTableModelSGCMF;
import sgcmf.view.tecnico.ISelecionarJogador;

public class LimBuscarJogador extends JDialog
{
    private CtrJogador ctrJogador;
    private JTableSGCMF jt;
    private ISelecionarJogador isj;
    private Short idJogo;
    private Short idSelecao;
    private Short idJogador;
    private static final int SELECIONA_JOGADOR = 0;
    private static final int SELECIONA_JOGADOR_MESMA_SELECAO = 1;
    private static final int SELECIONA_RESERVA_SELECAO = 2;
    private int tipoTela;    

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
        jt.setModel(new DefaultTableModelSGCMF(null, nomesColunas));
        jt.setDefaultRenderer(JLabel.class, new JLabelTableCellRenderer());
        jt.setRowHeight(32);
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

    public void ativaTelaSelecionaJogador(ISelecionarJogador isj, Short idJogo)
    {
        Object[][] dadosJogador;
        tipoTela = SELECIONA_JOGADOR;
        
        this.isj = isj;
        this.idJogo = idJogo;

        dadosJogador = ctrJogador.queryJogadoresEmCampo(idJogo);
        jt.preencheTabela(dadosJogador);

        setVisible(true);
    }

    public void ativaTelaSelecionaOutroJogadorSelecao(ISelecionarJogador isj, Short idJogo, Short idSelecao, Short idJogador)
    { 
        Object[][] dadosJogador;
        tipoTela = SELECIONA_JOGADOR_MESMA_SELECAO;
        
        this.isj = isj;
        this.idJogo = idJogo;
        this.idSelecao = idSelecao;
        this.idJogador = idJogador;

        dadosJogador = ctrJogador.queryOutrosJogadoresEmCampoSelecao(idJogo, idSelecao, idJogador);
        jt.preencheTabela(dadosJogador);

        setVisible(true);
    }
    
    public void ativaTelaSelecionaJogadorReservaSelecao(ISelecionarJogador isj, Short idJogo, Short idSelecao)
    {
        Object[][] dadosJogador;
        tipoTela = SELECIONA_RESERVA_SELECAO;
        
        this.isj = isj;
        this.idJogo = idJogo;
        this.idSelecao = idSelecao;

        dadosJogador = ctrJogador.queryReservasSelecao(idJogo, idSelecao);
        jt.preencheTabela(dadosJogador);

        setVisible(true);
    }

    private void pesquisa(String chave)
    {
        Object[][] dadosJogador;

        if (tipoTela == SELECIONA_JOGADOR)
        {
            dadosJogador = ctrJogador.queryJogadoresEmCampoByNome(idJogo, chave);
        }
        else if(tipoTela == SELECIONA_JOGADOR_MESMA_SELECAO)
        {
            dadosJogador = ctrJogador.queryOutrosJogadoresEmCampoSelecaoByNome(idJogo, idSelecao, idJogador, chave);
        }
        else
        {
            dadosJogador = ctrJogador.queryReservasSelecaoByNome(idJogo, idSelecao, chave);
        }       
        
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
