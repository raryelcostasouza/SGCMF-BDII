package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.view.table.JTableSGCMF;

public class LimGerenciarOcorrencia extends JFrame
{
	public LimGerenciarOcorrencia()
	{
		setTitle("Gerenciar Ocorrências de Jogo: Selecione um jogo");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		add(montaMainPanel());
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
		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		northPanel.setBorder(BorderFactory.createTitledBorder("Busca"));
		
		JLabel jl = new JLabel("Nome da seleção:");
		JTextField jtfSearchBox = new JTextField(15);
		
		northPanel.add(jl);
		northPanel.add(jtfSearchBox);
		
		return northPanel;
	}			
	
	private JScrollPane montaCenterPanel()
	{
		String[] nomesColunas = {"Tipo do Jogo", "Data/Hora", "Cidade", "Estádio", "Seleção I", "Seleção II", "Prorrogacao"};
		
		JTableSGCMF jt = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return jsp;		
	}
	
	private JPanel montaSouthPanel()
	{
		JPanel southPanel = new JPanel();
		
		JButton jbGerenciarOcorrencias = new JButton("Gerenciar Ocorrências para o Jogo Selecionado");
		southPanel.add(jbGerenciarOcorrencias);
		
		return southPanel;
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				new LimGerenciarOcorrencia();
			}
		});
	}
}
