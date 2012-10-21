package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LimComiteGestor extends JFrame
{
	private LimConsultaSelecao limConsultaSelecao;
	private LimConsultarJogo limConsultaJogo;
	private LimGerOcorrSelecionarJogo limGerOcorrSelecionarJogo;
	private LimBuscarJogador limBuscarJogador;
	
	public LimComiteGestor()
	{
		limConsultaSelecao = new LimConsultaSelecao();
		limConsultaJogo = new LimConsultarJogo();		
		limBuscarJogador = new LimBuscarJogador();
		limGerOcorrSelecionarJogo = new LimGerOcorrSelecionarJogo(limBuscarJogador);
		
		setTitle("Usuário Comitê Gestor");
		setSize(700,400);	
		setLocationRelativeTo(null);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(montaMainPanel());
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		mainPanel.add(montaGridPanel(), BorderLayout.CENTER);
		
		JButton jbLogout = new JButton("Logout");
		mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbLogout), BorderLayout.SOUTH);	
		
		return mainPanel;
	}
	
	private JPanel montaGridPanel()
	{
		JPanel gridPanel = new JPanel(new GridLayout(2,3));
		
		ImageIcon imgIcon = new ImageIcon("img/error.png");
		JButton jbConsultarSelecoes = new JButton("Consultar Seleções", imgIcon);
		jbConsultarSelecoes.setVerticalTextPosition(JButton.BOTTOM);
		jbConsultarSelecoes.setHorizontalTextPosition(JButton.CENTER);
		
		JButton jbConsultarJogos = new JButton("Consultar Jogos");
		JButton jbGerOcorrenciaJogo = new JButton("Gerenciar Ocorrências de Jogo");
		
		JButton jbGerDisputaPenaltis = new JButton("Gerenciar Disputa de Pênaltis");
		JButton jbRelatorios = new JButton("Relatórios");
		JButton jbTabelaCampeonato = new JButton("Tabela do Campeonato");
		
		jbConsultarSelecoes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limConsultaSelecao.setVisible(true);
			}
		});
		jbConsultarJogos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limConsultaJogo.setVisible(true);
			}
		});		
		jbGerOcorrenciaJogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limGerOcorrSelecionarJogo.setVisible(true);
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
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				new LimComiteGestor();
			}
		});
	}
}
