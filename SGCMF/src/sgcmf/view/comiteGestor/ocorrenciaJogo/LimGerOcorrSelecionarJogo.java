package sgcmf.view.comiteGestor.ocorrenciaJogo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import sgcmf.control.CtrJogo;
import sgcmf.view.comiteGestor.LimBuscarJogador;
import sgcmf.view.comiteGestor.LimComiteGestor;
import sgcmf.view.comiteGestor.LimConsultarJogo;

public class LimGerOcorrSelecionarJogo extends LimConsultarJogo
{
	private LimGerenciarOcorrenciasJogo limGerenciarOcorrenciasJogo;
	
	public LimGerOcorrSelecionarJogo(CtrJogo ctrJogo, LimBuscarJogador limBuscarJogador)
	{
		super(ctrJogo);
		
		setTitle("Gerenciar Ocorrências de Jogo: Selecione um Jogo");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		limGerenciarOcorrenciasJogo = new LimGerenciarOcorrenciasJogo(limBuscarJogador);
		mainPanel.add(montaSouthPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel montaSouthPanel()
	{
		JPanel southPanel = new JPanel();
		
		JButton jbGerenciarOcorrencias = new JButton("Gerenciar Ocorrências para o Jogo Selecionado");
		jbGerenciarOcorrencias.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limGerenciarOcorrenciasJogo.setVisible(true);
			}
		});
		
		southPanel.add(jbGerenciarOcorrencias);
		
		return southPanel;
	}
}
