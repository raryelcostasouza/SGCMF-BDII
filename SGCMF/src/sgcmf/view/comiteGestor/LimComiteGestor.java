package sgcmf.view.comiteGestor;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import sgcmf.control.CtrGol;
import sgcmf.control.CtrJogador;
import sgcmf.control.CtrJogo;
import sgcmf.control.CtrOcorrenciaJogo;
import sgcmf.control.CtrRelatorio;
import sgcmf.control.CtrSelecao;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.UtilView;
import sgcmf.view.comiteGestor.disputaPenalti.LimGerDispPenaltiSelecionarJogo;
import sgcmf.view.comiteGestor.ocorrenciaJogo.LimGerOcorrSelecionarJogo;

public class LimComiteGestor extends JFrame
{
    private CtrComiteGestor ctrComiteGestor;
    private LimConsultaSelecao limConsultaSelecao;
    private LimConsultarJogo limConsultaJogo;
    private LimGerOcorrSelecionarJogo limGerOcorrSelecionarJogo;
    private LimGerDispPenaltiSelecionarJogo limGerDispPenaltiSelecionarJogo;
    private LimBuscarJogador limBuscarJogador;
    private CtrRelatorio ctrRelatorio;

    public LimComiteGestor(CtrComiteGestor ctrComiteGestor)
    {
        this.ctrComiteGestor = ctrComiteGestor;
        ctrRelatorio = ctrComiteGestor.getCtrMain().getCtrRelatorio();
        setIconImage(SGCMFIcons.LOGO.getImage());

        limConsultaSelecao = new LimConsultaSelecao(ctrComiteGestor.getCtrSelecao());
        limConsultaJogo = new LimConsultarJogo(ctrComiteGestor.getCtrJogo());
        limBuscarJogador = new LimBuscarJogador(ctrComiteGestor.getCtrJogador());
        limGerOcorrSelecionarJogo = new LimGerOcorrSelecionarJogo(ctrComiteGestor, limBuscarJogador);
        limGerDispPenaltiSelecionarJogo = new LimGerDispPenaltiSelecionarJogo(ctrComiteGestor, limBuscarJogador);

        setTitle("SGCMF | Usuário Comitê Gestor");
        setSize(570, 480);
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

        mainPanel.add(montaGridPanel(), BorderLayout.CENTER);

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

    private JPanel montaGridPanel()
    {
        Dimension buttonDimension = new Dimension(180, 180);
        JPanel gridPanel = new JPanel(new GridLayout(2, 3));

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

        JButton jbGerDisputaPenaltis = new JButton("<html>Gerenciar Disputa<br><center>de Pênaltis</center></html>", SGCMFIcons.PENALTI);
        jbGerDisputaPenaltis.setVerticalTextPosition(JButton.BOTTOM);
        jbGerDisputaPenaltis.setHorizontalTextPosition(JButton.CENTER);
        jbGerDisputaPenaltis.setPreferredSize(buttonDimension);

        JButton jbRelatorios = new JButton("Relatórios", SGCMFIcons.RELATORIO);
        jbRelatorios.setVerticalTextPosition(JButton.BOTTOM);
        jbRelatorios.setHorizontalTextPosition(JButton.CENTER);
        jbRelatorios.setPreferredSize(buttonDimension);
        jbRelatorios.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrRelatorio.ativaTela();
            }
        });

        JButton jbTabelaCampeonato = new JButton("Tabela do Campeonato", SGCMFIcons.TABELA);
        jbTabelaCampeonato.setVerticalTextPosition(JButton.BOTTOM);
        jbTabelaCampeonato.setHorizontalTextPosition(JButton.CENTER);
        jbTabelaCampeonato.setPreferredSize(buttonDimension);

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
        jbGerDisputaPenaltis.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limGerDispPenaltiSelecionarJogo.setVisible(true);
            }
        });

        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbConsultarSelecoes));
        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbConsultarJogos));
        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbGerOcorrenciaJogo));

        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbGerDisputaPenaltis));
        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbRelatorios));
        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbTabelaCampeonato));

        return gridPanel;
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
