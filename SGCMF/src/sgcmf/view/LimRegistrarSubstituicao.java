package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LimRegistrarSubstituicao extends JFrame
{
	private LimBuscarJogador limBuscarJogador;
	
	public LimRegistrarSubstituicao(LimBuscarJogador limBuscarJogador)
	{
		this.limBuscarJogador = limBuscarJogador;
		
		setTitle("Registrar Substituição");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		add(montaMainPanel());
		setSize(360, 220);
		setLocationRelativeTo(null);
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(4,2));
		JPanel jpAuxJogadorSaiu = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jpAuxJogadorEntrou = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelJRBMotivo = new JPanel(new GridLayout(2,1));
		
		JLabel jlInstanteTempo = new JLabel("Instante de Tempo:");
		UtilView.alinhaLabel(jlInstanteTempo);
		JLabel jlJogadorSaiu = new JLabel("Jogador Saiu:");
		UtilView.alinhaLabel(jlJogadorSaiu);
		JLabel jlJogadorEntrou = new JLabel("Jogador Entrou:");
		UtilView.alinhaLabel(jlJogadorEntrou);
		JLabel jlMotivo = new JLabel("Motivo:");
		UtilView.alinhaLabel(jlMotivo);
		
		JTextField jtfInstanteTempo = new JTextField(10);
		JTextField jtfJogadorSaiu = new JTextField(10);
		JTextField jtfJogadorEntrou = new JTextField(10);
		
		JRadioButton jrbMotivoEstrategica = new JRadioButton("Estratégica");
		JRadioButton jrbMotivoContusao = new JRadioButton("Por contusão");
		jrbMotivoEstrategica.setSelected(true);
		
		JButton jbRegistrarSubst = new JButton("Registrar Substituição");
		JButton jbPesqJogSaiu = new JButton("P");
		JButton jbPesqJogEntrou = new JButton("P");
		
		jbPesqJogSaiu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limBuscarJogador.setVisible(true);
			}
		});
		
		jbPesqJogEntrou.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limBuscarJogador.setVisible(true);
			}
		});
		
		ButtonGroup bgMotivo = new ButtonGroup();
		bgMotivo.add(jrbMotivoEstrategica);
		bgMotivo.add(jrbMotivoContusao);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlInstanteTempo));
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jtfInstanteTempo, FlowLayout.LEFT));
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogadorSaiu));
		jpAuxJogadorSaiu.add(jtfJogadorSaiu);
		jpAuxJogadorSaiu.add(jbPesqJogSaiu);
		formPanel.add(jpAuxJogadorSaiu);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogadorEntrou));
		jpAuxJogadorEntrou.add(jtfJogadorEntrou);
		jpAuxJogadorEntrou.add(jbPesqJogEntrou);
		formPanel.add(jpAuxJogadorEntrou);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlMotivo));
		panelJRBMotivo.add(jrbMotivoEstrategica);
		panelJRBMotivo.add(jrbMotivoContusao);
		formPanel.add(panelJRBMotivo);
		
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarSubst), BorderLayout.SOUTH);
		
		return mainPanel;
	}
}
