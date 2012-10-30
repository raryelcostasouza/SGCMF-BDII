package sgcmf.view.comiteGestor.ocorrenciaJogo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import sgcmf.control.CtrJogo;
import sgcmf.control.CtrOcorrenciaJogo;
import sgcmf.view.comiteGestor.LimBuscarJogador;
import sgcmf.view.comiteGestor.LimComiteGestor;
import sgcmf.view.comiteGestor.LimConsultarJogo;

public class LimGerOcorrSelecionarJogo extends LimConsultarJogo
{
	private LimGerenciarOcorrenciasJogo limGerenciarOcorrenciasJogo;
	
	public LimGerOcorrSelecionarJogo(CtrJogo ctrJogo, CtrOcorrenciaJogo ctrOcorrenciaJogo, LimBuscarJogador limBuscarJogador)
	{
		super(ctrJogo);
		
		setTitle("Gerenciar Ocorrências de Jogo: Selecione um Jogo");
		
		limGerenciarOcorrenciasJogo = new LimGerenciarOcorrenciasJogo(ctrOcorrenciaJogo, ctrJogo, limBuscarJogador);
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
				Short idJogo;
				if (jt.getSelectedRow() != -1)
				{
					idJogo = Short.parseShort((String) jt.getValueAt(jt.getSelectedRow(), 0));
					limGerenciarOcorrenciasJogo.ativaTela(idJogo);
				}			
			}
		});
		
		southPanel.add(jbGerenciarOcorrencias);
		
		return southPanel;
	}
}
