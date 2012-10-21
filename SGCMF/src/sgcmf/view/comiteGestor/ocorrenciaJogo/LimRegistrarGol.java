package sgcmf.view.comiteGestor.ocorrenciaJogo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import sgcmf.view.comiteGestor.LimBuscarJogador;
import sgcmf.view.UtilView;

public class LimRegistrarGol extends JDialog
{
	private LimBuscarJogador limBuscarJogador;
	
	public LimRegistrarGol(LimBuscarJogador limBuscarJogador)
	{
		this.limBuscarJogador = limBuscarJogador;
		
		setTitle("Registrar Gol");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		add(montaMainPanel());
		setModal(true);
		setSize(370,275);
		setLocationRelativeTo(null);
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(5, 2));
		JPanel panelJRBTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelJRBModo = new JPanel(new GridLayout(3,1));
		JPanel jpAuxJogadorAutor = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jpAuxJogadorAssist = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel jlInstanteTempo = new JLabel("Instante de Tempo:");
		UtilView.alinhaLabel(jlInstanteTempo);
		JLabel jlJogador = new JLabel("Jogador Autor:");
		UtilView.alinhaLabel(jlJogador);
		JLabel jlJogadorAssist = new JLabel("Jogador Assistente: ");
		UtilView.alinhaLabel(jlJogadorAssist);
		JLabel jlTipo = new JLabel("Tipo:");
		UtilView.alinhaLabel(jlTipo);
		JLabel jlModo = new JLabel("Modo:");
		UtilView.alinhaLabel(jlModo);
		
		JTextField jtfInstanteTempo = new JTextField(10);
		JTextField jtfJogador = new JTextField(10);
		JTextField jtfJogadorAssist = new JTextField(10);
		JRadioButton jrbTipoAFavor = new JRadioButton("A favor");
		jrbTipoAFavor.setSelected(true);
		JRadioButton jrbTipoContra = new JRadioButton("Contra");
		
		JRadioButton jrbModoComum = new JRadioButton("Comum");
		JRadioButton jrbModoFalta = new JRadioButton("Falta");
		JRadioButton jrbModoPenalti = new JRadioButton("Pênalti");
		jrbModoComum.setSelected(true);
		
		JButton jbRegistrarGol = new JButton("Registrar Gol");
		JButton jbPesqJogadorAutor = new JButton("P");
		JButton jbPesqJogadorAssist = new JButton("P");
		
		jbPesqJogadorAutor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limBuscarJogador.setVisible(true);
			}
		});
		jbPesqJogadorAssist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limBuscarJogador.setVisible(true);
			}
		});
		
		ButtonGroup bgTipo = new ButtonGroup();
		bgTipo.add(jrbTipoAFavor);
		bgTipo.add(jrbTipoContra);
		
		ButtonGroup bgModo = new ButtonGroup();
		bgModo.add(jrbModoComum);
		bgModo.add(jrbModoFalta);
		bgModo.add(jrbModoPenalti);		
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlInstanteTempo));
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jtfInstanteTempo, FlowLayout.LEFT));
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogador));
		jpAuxJogadorAutor.add(jtfJogador);
		jpAuxJogadorAutor.add(jbPesqJogadorAutor);
		formPanel.add(jpAuxJogadorAutor);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogadorAssist));
		jpAuxJogadorAssist.add(jtfJogadorAssist);
		jpAuxJogadorAssist.add(jbPesqJogadorAssist);
		formPanel.add(jpAuxJogadorAssist);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlTipo));
		panelJRBTipo.add(jrbTipoAFavor);
		panelJRBTipo.add(jrbTipoContra);
		formPanel.add(panelJRBTipo);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlModo));
		panelJRBModo.add(jrbModoComum);
		panelJRBModo.add(jrbModoFalta);
		panelJRBModo.add(jrbModoPenalti);
		formPanel.add(panelJRBModo);
		
		
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarGol), BorderLayout.SOUTH);
		
		return mainPanel;
	}	
}
