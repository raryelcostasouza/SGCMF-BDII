package sgcmf.view.comiteGestor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import sgcmf.control.CtrComiteGestor;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.UtilView;
import sgcmf.view.comiteGestor.ocorrenciaJogo.LimGerOcorrSelecionarJogo;

public class LimComiteGestor extends JFrame
{
    private CtrComiteGestor ctrComiteGestor;
    private LimConsultaSelecao limConsultaSelecao;
    private LimConsultarJogo limConsultaJogo;
    private LimGerOcorrSelecionarJogo limGerOcorrSelecionarJogo;
    private LimBuscarJogador limBuscarJogador;
    private final Dimension buttonDimension = new Dimension(180, 180);

    public LimComiteGestor(CtrComiteGestor ctrComiteGestor)
    {
        this.ctrComiteGestor = ctrComiteGestor;
        
        setIconImage(SGCMFIcons.LOGO.getImage());

        limConsultaSelecao = new LimConsultaSelecao(ctrComiteGestor.getCtrSelecao());
        limConsultaJogo = new LimConsultarJogo(ctrComiteGestor.getCtrJogo());
        limBuscarJogador = new LimBuscarJogador(ctrComiteGestor.getCtrJogador());
        limGerOcorrSelecionarJogo = new LimGerOcorrSelecionarJogo(ctrComiteGestor, limBuscarJogador);

        setTitle("SGCMF | Usuário Comitê Gestor");
        setSize(570, 480);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        add(montaMainPanel());

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                acaoLogout();
            }
        });
    }

    private JPanel montaMainPanel()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(montaNorthPanel(), BorderLayout.NORTH);
        mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);

        JButton jbLogout = new JButton("Logout", SGCMFIcons.LOGOUT);

        jbLogout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                acaoLogout();
            }
        });

        mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbLogout), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel montaNorthPanel()
    {
        
        JPanel gridPanel = new JPanel(new GridLayout(1, 3));

        JButton jbConsultarSelecoes = new JButton("Consultar Seleções", SGCMFIcons.SELECAO);
        jbConsultarSelecoes.setVerticalTextPosition(JButton.BOTTOM);
        jbConsultarSelecoes.setHorizontalTextPosition(JButton.CENTER);
        jbConsultarSelecoes.setPreferredSize(buttonDimension);

        JButton jbConsultarJogos = new JButton("Consultar Jogos", SGCMFIcons.JOGO);
        jbConsultarJogos.setVerticalTextPosition(JButton.BOTTOM);
        jbConsultarJogos.setHorizontalTextPosition(JButton.CENTER);
        jbConsultarJogos.setPreferredSize(buttonDimension);

        JButton jbGerOcorrenciaJogo = new JButton("<html>Gerenciar Ocorrências<br><center>de Jogo</center></html>", SGCMFIcons.OCORRENCIA_JOGO);
        jbGerOcorrenciaJogo.setVerticalTextPosition(JButton.BOTTOM);
        jbGerOcorrenciaJogo.setHorizontalTextPosition(JButton.CENTER);
        jbGerOcorrenciaJogo.setHorizontalAlignment(SwingConstants.CENTER);
        jbGerOcorrenciaJogo.setPreferredSize(buttonDimension);

        jbConsultarSelecoes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limConsultaSelecao.ativaTela();
            }
        });
        jbConsultarJogos.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limConsultaJogo.ativaTela();
            }
        });
        jbGerOcorrenciaJogo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limGerOcorrSelecionarJogo.ativaTela();
            }
        });

        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbConsultarSelecoes));
        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbConsultarJogos));
        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbGerOcorrenciaJogo));

        return gridPanel;
    }

    private JPanel montaCenterPanel()
    {
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton jbRelatorios = new JButton("Relatórios", SGCMFIcons.RELATORIO);
        jbRelatorios.setVerticalTextPosition(JButton.BOTTOM);
        jbRelatorios.setHorizontalTextPosition(JButton.CENTER);
        jbRelatorios.setPreferredSize(buttonDimension);
        jbRelatorios.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrComiteGestor.getCtrRelatorio().ativaTela();
            }
        });

        JButton jbTabelaCampeonato = new JButton("Tabela do Campeonato", SGCMFIcons.TABELA);
        jbTabelaCampeonato.setVerticalTextPosition(JButton.BOTTOM);
        jbTabelaCampeonato.setHorizontalTextPosition(JButton.CENTER);
        jbTabelaCampeonato.setPreferredSize(buttonDimension);
        jbTabelaCampeonato.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrComiteGestor.getCtrTabelaCampeonato().ativaTela();
            }
        });

        centerPanel.add(jbRelatorios);
        centerPanel.add(jbTabelaCampeonato);

        return centerPanel;
    }

    private void acaoLogout()
    {
        int op;

        op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja fazer logout do sistema?", "Confirmação de Logout", JOptionPane.YES_NO_OPTION);

        if (op == JOptionPane.OK_OPTION)
        {
            setVisible(false);
            ctrComiteGestor.logout();
        }
    }

    public CtrComiteGestor getCtrComiteGestor()
    {
        return ctrComiteGestor;
    }
}
