package sgcmf.view.comiteGestor;

import sgcmf.view.comiteGestor.disputaPenalti.LimGerDispPenaltiSelecionarJogo;
import sgcmf.view.comiteGestor.ocorrenciaJogo.LimGerOcorrSelecionarJogo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import sgcmf.control.CtrComiteGestor;
import sgcmf.view.UtilView;

public class LimComiteGestor extends JDialog
{
	private CtrComiteGestor ctrComiteGestor;
	
	private LimConsultaSelecao limConsultaSelecao;
	private LimConsultarJogo limConsultaJogo;
	private LimGerOcorrSelecionarJogo limGerOcorrSelecionarJogo;
	private LimGerDispPenaltiSelecionarJogo limGerDispPenaltiSelecionarJogo;
	private LimBuscarJogador limBuscarJogador;
	
	public LimComiteGestor(CtrComiteGestor ctrComiteGestor)
	{
		this.ctrComiteGestor = ctrComiteGestor;
				
		limConsultaSelecao = new LimConsultaSelecao();
		limConsultaJogo = new LimConsultarJogo();
		limBuscarJogador = new LimBuscarJogador();
		limGerOcorrSelecionarJogo = new LimGerOcorrSelecionarJogo(limBuscarJogador);
		limGerDispPenaltiSelecionarJogo = new LimGerDispPenaltiSelecionarJogo(limBuscarJogador);

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

		ImageIcon iconLogout = new ImageIcon("img/logout.png");
		JButton jbLogout = new JButton("Logout", iconLogout);
		
		jbLogout.addActionListener(new ActionListener() {

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
		Dimension buttonDimension = new Dimension(180,180);
		JPanel gridPanel = new JPanel(new GridLayout(2, 3));

		ImageIcon iconSelecao = new ImageIcon("img/selecao.png");
		ImageIcon iconJogo = new ImageIcon("img/jogo.png");
		ImageIcon iconOcorrenciaJogo = new ImageIcon("img/ocorrencia-jogo.png");
		ImageIcon iconPenalti = new ImageIcon("img/penalti.png");
		ImageIcon iconRelatorio = new ImageIcon("img/relatorio.png");
		ImageIcon iconTabela = new ImageIcon("img/tabela.png");
		
		JButton jbConsultarSelecoes = new JButton("Consultar Seleções", iconSelecao);
		jbConsultarSelecoes.setVerticalTextPosition(JButton.BOTTOM);
		jbConsultarSelecoes.setHorizontalTextPosition(JButton.CENTER);
		jbConsultarSelecoes.setPreferredSize(buttonDimension);

		JButton jbConsultarJogos = new JButton("Consultar Jogos", iconJogo);
		jbConsultarJogos.setVerticalTextPosition(JButton.BOTTOM);
		jbConsultarJogos.setHorizontalTextPosition(JButton.CENTER);
		jbConsultarJogos.setPreferredSize(buttonDimension);
		
		JButton jbGerOcorrenciaJogo = new JButton("<html>Gerenciar Ocorrências<br><center>de Jogo</center></html>", iconOcorrenciaJogo);
		jbGerOcorrenciaJogo.setVerticalTextPosition(JButton.BOTTOM);
		jbGerOcorrenciaJogo.setHorizontalTextPosition(JButton.CENTER);
		jbGerOcorrenciaJogo.setHorizontalAlignment(SwingConstants.CENTER);
		jbGerOcorrenciaJogo.setPreferredSize(buttonDimension);

		JButton jbGerDisputaPenaltis = new JButton("<html>Gerenciar Disputa<br><center>de Pênaltis</center></html>", iconPenalti);
		jbGerDisputaPenaltis.setVerticalTextPosition(JButton.BOTTOM);
		jbGerDisputaPenaltis.setHorizontalTextPosition(JButton.CENTER);
		jbGerDisputaPenaltis.setPreferredSize(buttonDimension);
		
		JButton jbRelatorios = new JButton("Relatórios", iconRelatorio);
		jbRelatorios.setVerticalTextPosition(JButton.BOTTOM);
		jbRelatorios.setHorizontalTextPosition(JButton.CENTER);
		jbRelatorios.setPreferredSize(buttonDimension);
		
		JButton jbTabelaCampeonato = new JButton("Tabela do Campeonato", iconTabela);
		jbTabelaCampeonato.setVerticalTextPosition(JButton.BOTTOM);
		jbTabelaCampeonato.setHorizontalTextPosition(JButton.CENTER);
		jbTabelaCampeonato.setPreferredSize(buttonDimension);

		jbConsultarSelecoes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				limConsultaSelecao.setVisible(true);
			}
		});
		jbConsultarJogos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				limConsultaJogo.setVisible(true);
			}
		});
		jbGerOcorrenciaJogo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				limGerOcorrSelecionarJogo.setVisible(true);
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
}
