package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LimGerDispPenaltiSelecionarJogo extends LimConsultarJogo
{
	private LimGerDisputaPenalti limGerDisputaPenalti;
	
	public LimGerDispPenaltiSelecionarJogo()
	{
		super();
		setTitle("Gerenciar Disputa de Pênaltis: Selecione um Jogo");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		limGerDisputaPenalti = new LimGerDisputaPenalti();
		
		mainPanel.add(montaSouthPanel(), BorderLayout.SOUTH);
	}
	
	
	private JPanel montaSouthPanel()
	{
		JPanel southPanel = new JPanel();
		
		JButton jbGerenciarOcorrencias = new JButton("Gerenciar Disputa de Pênaltis para o Jogo Selecionado");
		jbGerenciarOcorrencias.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limGerDisputaPenalti.setVisible(true);
			}
		});
		
		southPanel.add(jbGerenciarOcorrencias);
		
		return southPanel;
	}
}