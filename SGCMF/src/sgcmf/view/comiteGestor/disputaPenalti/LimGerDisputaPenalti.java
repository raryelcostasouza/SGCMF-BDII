package sgcmf.view.comiteGestor.disputaPenalti;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import sgcmf.view.comiteGestor.LimBuscarJogador;
import sgcmf.view.table.JTableSGCMF;

public class LimGerDisputaPenalti extends JDialog
{
	private LimLancarRodadaPenaltis limLancarRodadaPenalti;
	
	public LimGerDisputaPenalti(LimBuscarJogador limBuscarJogador)
	{
		limLancarRodadaPenalti = new LimLancarRodadaPenaltis(limBuscarJogador);
		
		setTitle("Gerenciar Disputa de Pênaltis para o Jogo Selecionado");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		add(montaMainPanel());
		setModal(true);
		pack();		
		setLocationRelativeTo(null);
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		mainPanel.add(montaNorthPanel(), BorderLayout.NORTH);
		mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);
		mainPanel.add(montaSouthPanel(), BorderLayout.SOUTH);
		
		return mainPanel;
	}
	
	private JPanel montaNorthPanel()
	{
		JPanel northPanel = new JPanel();
		northPanel.setBorder(BorderFactory.createTitledBorder("Informações do jogo selecionado:"));
		
		JLabel jlInfoJogo = new JLabel("Data/Hora | Tipo do Jogo | SeleçãoI x SeleçãoII", JLabel.CENTER);
		
		northPanel.add(jlInfoJogo);
		
		return northPanel;
	}
	
	private JScrollPane montaCenterPanel()
	{
		String[] nomesColunas = {"ID", "Nº Rodada", "Cobrança I", "Cobrança II"};
		
		JTableSGCMF jt = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return jsp;	
	}
	
	private JPanel montaSouthPanel()
	{
		JPanel southPanel = new JPanel();
		
		JButton jbLancar = new JButton("Lançar Novo Resultado de Rodada");
		JButton jbRemover = new JButton("Remover Lançamento");
		
		jbLancar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limLancarRodadaPenalti.setVisible(true);
			}
		});
		
		southPanel.add(jbLancar);
		southPanel.add(jbRemover);
		
		return southPanel;
	}
}
