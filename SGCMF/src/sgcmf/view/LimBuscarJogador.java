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

public class LimBuscarJogador extends JFrame
{
	public LimBuscarJogador()
	{
		setTitle("Buscar jogador");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
		
		add(montaMainPanel());
		setSize(500, 400);
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
		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.setBorder(BorderFactory.createTitledBorder("Busca:"));
		
		JLabel jlNomeJogador = new JLabel("Nome do jogador:");
		JTextField jtfSearchBox = new JTextField(15);
		
		northPanel.add(jlNomeJogador);
		northPanel.add(jtfSearchBox);
		
		return northPanel;
	}
	
	private JScrollPane montaCenterPanel()
	{
		String[] nomesColunas = {"Seleção", "Nome", "Número da Camisa", "Posição"};
		
		JTableSGCMF jt = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return jsp;		
	}
	
	private JPanel montaSouthPanel()
	{
		JPanel southPanel = new JPanel();
		
		JButton jbSelecionar = new JButton("Selecionar");
		
		southPanel.add(jbSelecionar);
		
		return southPanel;
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run()
			{
				new LimBuscarJogador();
			}
		});
	}
}
