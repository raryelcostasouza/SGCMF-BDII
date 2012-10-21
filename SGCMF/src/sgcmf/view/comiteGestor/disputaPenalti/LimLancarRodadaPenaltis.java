package sgcmf.view.comiteGestor.disputaPenalti;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import sgcmf.view.UtilView;

public class LimLancarRodadaPenaltis extends JFrame
{
	public LimLancarRodadaPenaltis()
	{
		setTitle("Lançar Resultado de Rodada de Pênaltis");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		add(montaMainPanel());
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);
		mainPanel.add(montaSouthPanel(), BorderLayout.SOUTH);
		
		return mainPanel;
	}
	
	private JPanel montaCenterPanel()
	{
		JPanel centerPanel = new JPanel(new GridLayout(2,1));
		
		centerPanel.add(montaCobrancaPanel("Cobrança I"));		
		centerPanel.add(montaCobrancaPanel("Cobrança II"));
		
		return centerPanel;
		
	}
	
	private JPanel montaCobrancaPanel(String strTitle)
	{
		JPanel cobrancaIPanel = new JPanel(new GridLayout(2,2));
		cobrancaIPanel.setBorder(BorderFactory.createTitledBorder(strTitle));
		
		JPanel panelJRBResultado = new JPanel(new GridLayout(2,1));
		JPanel jpAuxJogador = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel jlJogador = new JLabel("Jogador:");
		UtilView.alinhaLabel(jlJogador);
		JLabel jlResultado = new JLabel("Resultado:");
		UtilView.alinhaLabel(jlResultado);
		
		JTextField jtfJogador = new JTextField(10);
		JRadioButton jrbGolNaoMarcado = new JRadioButton("Gol Não Marcado");
		jrbGolNaoMarcado.setSelected(true);
		JRadioButton jrbGolMarcado = new JRadioButton("Gol Marcado");
		
		JButton jbPesqJogador = new JButton("P");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbGolNaoMarcado);
		bg.add(jrbGolMarcado);
		
		cobrancaIPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogador));
		jpAuxJogador.add(jtfJogador);
		jpAuxJogador.add(jbPesqJogador);
		cobrancaIPanel.add(jpAuxJogador);
		
		cobrancaIPanel.add(UtilView.putComponentInFlowLayoutPanel(jlResultado));
		panelJRBResultado.add(jrbGolNaoMarcado);
		panelJRBResultado.add(jrbGolMarcado);
		cobrancaIPanel.add(panelJRBResultado);	
		
		return cobrancaIPanel;
	}
	
	private JPanel montaSouthPanel()
	{
		JPanel southPanel = new JPanel();
		
		JButton jbRegistrar = new JButton("Registrar");
		southPanel.add(jbRegistrar);
		
		return southPanel;
	}
}
