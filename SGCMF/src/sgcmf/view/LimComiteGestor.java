package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sgcmf.util.UtilView;

public class LimComiteGestor extends JFrame
{
	public LimComiteGestor()
	{
		setTitle("Usuário Comitê Gestor");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,700);	
		
		add(montaMainPanel());		
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel gridPanel = new JPanel(new GridLayout(2,3));
		JButton jbConsultarSelecoes = new JButton("Consultar Seleções");
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
		
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		
		JButton jbLogout = new JButton("Logout");
		mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbLogout), BorderLayout.SOUTH);	
		
		return mainPanel;
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
