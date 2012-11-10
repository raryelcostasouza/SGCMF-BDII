package sgcmf.view.comiteGestor.ocorrenciaJogo;

import java.awt.BorderLayout;
import java.awt.Button;
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
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.comiteGestor.LimBuscarJogador;
import sgcmf.view.UtilView;

public class LimRegistrarCartao extends JDialog
{
	private LimBuscarJogador limBuscarJogador;
	
	public LimRegistrarCartao(LimBuscarJogador limBuscarJogador)
	{
		this.limBuscarJogador = limBuscarJogador;
		
		setTitle("Registrar Cartão");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		add(montaMainPanel());
		setModal(true);
		setSize(360, 170);
		setLocationRelativeTo(null);
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(3,2));
		JPanel jpAuxJogador = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelJRBCartao = new JPanel(new GridLayout(2,1));
		
		JLabel jlInstanteTempo = new JLabel("Instante de Tempo:");
		UtilView.alinhaLabel(jlInstanteTempo);
		JLabel jlJogador = new JLabel("Jogador:");
		UtilView.alinhaLabel(jlJogador);
		JLabel jlCartao = new JLabel("Cartão:");
		UtilView.alinhaLabel(jlCartao);
		
		JTextField jtfInstanteTempo = new JTextField(10);
		JTextField jtfJogador = new JTextField(10);
		
		JRadioButton jrbCartaoAmarelo = new JRadioButton("Amarelo");
		jrbCartaoAmarelo.setSelected(true);
		JRadioButton jrbCartaoVermelho = new JRadioButton("Vermelho");
				
		JButton jbRegistrarCartao = new JButton("Registrar Cartão");
		JButton jbPesqJogador = new JButton(SGCMFIcons.PESQUISAR);
                UtilView.ajustarTamanhoBotaoPesquisar(jbPesqJogador);
                
		jbPesqJogador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limBuscarJogador.setVisible(true);
			}
		});
		
		ButtonGroup bgCartao = new ButtonGroup();
		bgCartao.add(jrbCartaoAmarelo);
		bgCartao.add(jrbCartaoVermelho);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlInstanteTempo));
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jtfInstanteTempo, FlowLayout.LEFT));
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogador));
		jpAuxJogador.add(jtfJogador);
		jpAuxJogador.add(jbPesqJogador);
		formPanel.add(jpAuxJogador);
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlCartao));
		panelJRBCartao.add(jrbCartaoAmarelo);
		panelJRBCartao.add(jrbCartaoVermelho);
		formPanel.add(panelJRBCartao);
		
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarCartao), BorderLayout.SOUTH);
		
		return mainPanel;
	}
}
