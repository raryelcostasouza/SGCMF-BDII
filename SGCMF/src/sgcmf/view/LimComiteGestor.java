package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LimComiteGestor extends JFrame
{
	public LimComiteGestor()
	{
		setTitle("Usuário Comitê Gestor");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700,400);	
		
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
	
	public JPanel montaGridPanel()
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
