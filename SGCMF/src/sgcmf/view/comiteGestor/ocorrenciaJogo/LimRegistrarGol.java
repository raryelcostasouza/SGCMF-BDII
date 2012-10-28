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
import sgcmf.control.CtrOcorrenciaJogo;
import sgcmf.view.comiteGestor.LimBuscarJogador;
import sgcmf.view.UtilView;
import sgcmf.view.tecnico.ISelecionarJogador;

public class LimRegistrarGol extends JDialog implements ISelecionarJogador
{
	private LimBuscarJogador limBuscarJogador;
	private CtrOcorrenciaJogo ctrOcorrenciaJogo;
	
	private JTextField jtfInstanteTempoMin;
	private JTextField jtfInstateTempoSeg;
	private JTextField jtfJogador;
	private JTextField jtfJogadorAssist;
	private ButtonGroup bgTipo;
	private ButtonGroup bgModo;
	
	private boolean selecaoJogadorAutor = false;
	private boolean selecaoJogadorAssistente = false;
	
	private JRadioButton jrbTipoAFavor;
	private JRadioButton jrbTipoContra;	
	private JRadioButton jrbModoComum;
	private JRadioButton jrbModoFalta;
	private JRadioButton jrbModoPenalti;
	
	private Short idJogo;
	
	public LimRegistrarGol(CtrOcorrenciaJogo ctrOcorrenciaJogo, LimBuscarJogador limBuscarJogador)
	{
		this.ctrOcorrenciaJogo = ctrOcorrenciaJogo;
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
		JPanel jpAuxTempo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
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
		
		jtfInstanteTempoMin = new JTextField(4);
		JLabel jlMin = new JLabel("min");
		jtfInstateTempoSeg = new JTextField(4);
		JLabel jlSeg = new JLabel("seg");
		
		jtfJogador = new JTextField(10);
		jtfJogador.setEditable(false);
		
		jtfJogadorAssist = new JTextField(10);
		jtfJogadorAssist.setEditable(false);
		
		jrbTipoAFavor = new JRadioButton("A Favor");
		jrbTipoAFavor.setSelected(true);
		jrbTipoContra = new JRadioButton("Contra");
		
		jrbModoComum = new JRadioButton("Comum");
		jrbModoFalta = new JRadioButton("Falta");
		jrbModoPenalti = new JRadioButton("Penalti");
		jrbModoComum.setSelected(true);
		
		JButton jbRegistrarGol = new JButton("Registrar Gol");
		jbRegistrarGol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				registrarGol();
			}
		});
		
		JButton jbPesqJogadorAutor = new JButton("P");
		JButton jbPesqJogadorAssist = new JButton("P");
		
		jbPesqJogadorAutor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				selecaoJogadorAutor = true;
				ativaTelaBuscarJogador();
			}
		});
		jbPesqJogadorAssist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				selecaoJogadorAssistente = true;
				ativaTelaBuscarJogador();
			}
		});
		
		bgTipo = new ButtonGroup();
		bgTipo.add(jrbTipoAFavor);
		bgTipo.add(jrbTipoContra);
		
		bgModo = new ButtonGroup();
		bgModo.add(jrbModoComum);
		bgModo.add(jrbModoFalta);
		bgModo.add(jrbModoPenalti);		
		
		formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlInstanteTempo));
		jpAuxTempo.add(jtfInstanteTempoMin);
		jpAuxTempo.add(jlMin);
		jpAuxTempo.add(jtfInstateTempoSeg);
		jpAuxTempo.add(jlSeg);
		formPanel.add(jpAuxTempo);
		
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
	
	public void ativaTela(Short idJogo)
	{
		this.idJogo = idJogo;
		setVisible(true);
	}
	
	private void registrarGol()
	{		
		String tipo;
		String modo;
		
		if (jrbTipoAFavor.isSelected())
		{
			tipo = jrbTipoAFavor.getText();
		}
		else
		{
			tipo = jrbTipoContra.getText();
		}
		
		if (jrbModoComum.isSelected())
		{
			modo = jrbModoComum.getText();
		}
		else if (jrbModoFalta.isSelected())
		{
			modo = jrbModoFalta.getText();
		}
		else
		{
			modo = jrbModoPenalti.getText();
		}
		
		ctrOcorrenciaJogo.registraGol(jtfInstanteTempoMin.getText(),
									jtfInstateTempoSeg.getText(),
									idJogo,
									jtfJogador.getText(), 
									jtfJogadorAssist.getText(),
									tipo,
									modo);
	}

	private void ativaTelaBuscarJogador()
	{
		limBuscarJogador.ativaTela(this);
	}
	
	@Override
	public void jogadorSelecionado(Short idJogador)
	{
		if (selecaoJogadorAutor)
		{
			jtfJogador.setText(idJogador+"");
			selecaoJogadorAutor = false;
		}
		else
		{
			jtfJogadorAssist.setText(idJogador+"");
			selecaoJogadorAssistente = false;
		}		
	}
	
}
