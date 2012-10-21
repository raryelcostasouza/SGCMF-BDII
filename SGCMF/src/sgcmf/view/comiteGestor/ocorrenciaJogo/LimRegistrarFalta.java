package sgcmf.view.comiteGestor.ocorrenciaJogo;

import java.awt.BorderLayout;
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
import sgcmf.view.LimBuscarJogador;
import sgcmf.view.UtilView;

public class LimRegistrarFalta extends JDialog
{
	private LimBuscarJogador limBuscarJogador;
	
	public LimRegistrarFalta(LimBuscarJogador limBuscarJogador)
	{
		this.limBuscarJogador = limBuscarJogador;
		
		setTitle("Registrar Falta");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		add(montaMainPanel());
		setModal(true);
		setSize(370,230);
		setLocationRelativeTo(null);
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(4,2));
		JPanel jpAuxJogador = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelJRBCartao = new JPanel(new GridLayout(3,1));
		JPanel panelJRBTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel jlInstanteTempo = new JLabel("Instante de Tempo:");
		UtilView.alinhaLabel(jlInstanteTempo);
		JLabel jlJogador = new JLabel("Jogador:");
		UtilView.alinhaLabel(jlJogador);
		JLabel jlCartao = new JLabel("Cartão:");
		UtilView.alinhaLabel(jlCartao);
		JLabel jlTipo = new JLabel("Tipo:");
		UtilView.alinhaLabel(jlTipo);
		
		JTextField jtfInstanteTempo = new JTextField(10);
		JTextField jtfJogador = new JTextField(10);
		
		JRadioButton jrbCartaoNenhum = new JRadioButton("Nenhum");
		jrbCartaoNenhum.setSelected(true);
		JRadioButton jrbCartaoAmarelo = new JRadioButton("Amarelo");
		JRadioButton jrbCartaoVermelho = new JRadioButton("Vermelho");
		
		JRadioButton jrbTipoComum = new JRadioButton("Falta comum");
		jrbTipoComum.setSelected(true);
		JRadioButton jrbTipoPenalti = new JRadioButton("Pênalti");
		
		JButton jbRegistrarFalta = new JButton("Registrar Falta");
		JButton jbPesqJogador = new JButton("P");
		jbPesqJogador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limBuscarJogador.setVisible(true);
			}
		});
		
		ButtonGroup bgCartao = new ButtonGroup();
		bgCartao.add(jrbCartaoNenhum);
		bgCartao.add(jrbCartaoAmarelo);
		bgCartao.add(jrbCartaoVermelho);
		
		ButtonGroup bgTipo = new ButtonGroup();
		bgTipo.add(jrbTipoComum);
		bgTipo.add(jrbTipoPenalti);		
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlInstanteTempo));
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jtfInstanteTempo, FlowLayout.LEFT));
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogador));
		jpAuxJogador.add(jtfJogador);
		jpAuxJogador.add(jbPesqJogador);
		formPanel.add(jpAuxJogador);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlTipo));
		panelJRBTipo.add(jrbTipoComum);
		panelJRBTipo.add(jrbTipoPenalti);
		formPanel.add(panelJRBTipo);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlCartao));
		panelJRBCartao.add(jrbCartaoNenhum);
		panelJRBCartao.add(jrbCartaoAmarelo);
		panelJRBCartao.add(jrbCartaoVermelho);
		formPanel.add(panelJRBCartao);		
		
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarFalta), BorderLayout.SOUTH);
		return mainPanel;
	}
}
