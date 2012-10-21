package sgcmf.view.comiteGestor.ocorrenciaJogo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sgcmf.view.LimBuscarJogador;
import sgcmf.view.UtilView;

public class LimRegistrarRoubadaBola extends JFrame
{
	private LimBuscarJogador limBuscarJogador;
	
	public LimRegistrarRoubadaBola(LimBuscarJogador limBuscarJogador)
	{
		this.limBuscarJogador = limBuscarJogador;
		
		setTitle("Registrar Roubada de Bola");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		add(montaMainPanel());
		setSize(360, 140);
		setLocationRelativeTo(null);
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(2,2));
		JPanel jpAuxJogador = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel jlInstanteTempo = new JLabel("Instante de Tempo:");
		UtilView.alinhaLabel(jlInstanteTempo);
		JLabel jlJogador = new JLabel("Jogador Ladrão:");
		UtilView.alinhaLabel(jlJogador);
		
		JTextField jtfInstanteTempo = new JTextField(10);
		JTextField jtfJogador = new JTextField(10);
		
		JButton jbRegistrarRoubadaBola = new JButton("Registrar Roubada de Bola");
		JButton jbPesqJogador = new JButton("P");
		jbPesqJogador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limBuscarJogador.setVisible(true);
			}
		});
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlInstanteTempo));
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jtfInstanteTempo, FlowLayout.LEFT));
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogador));
		jpAuxJogador.add(jtfJogador);		
		jpAuxJogador.add(jbPesqJogador);
		formPanel.add(jpAuxJogador);
		
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarRoubadaBola), BorderLayout.SOUTH);
		
		return mainPanel;	
	}
}
