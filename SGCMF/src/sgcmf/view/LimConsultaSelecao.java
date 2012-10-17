package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.view.table.JTableSGCMF;

public class LimConsultaSelecao extends JFrame
{
	public LimConsultaSelecao()
	{
		setTitle("Consulta Seleção");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		add(montaMainPanel());		
		pack();
		
		setLocationRelativeTo(null);
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);
		mainPanel.add(montaNorthPanel(), BorderLayout.NORTH);
		
		return mainPanel;
	}
	
	public JPanel montaNorthPanel()
	{
		JPanel northPanel = new JPanel(new BorderLayout());
		
		northPanel.add(montaNorthEastPanel(), BorderLayout.EAST);
		northPanel.add(montaNorthWestPanel(), BorderLayout.WEST);
				
		return northPanel;
		
	}
	
	public JPanel montaNorthWestPanel()
	{
		JPanel northWestPanel = new JPanel();
		
		JRadioButton jrbPais = new JRadioButton("País");
		jrbPais.setSelected(true);
		JRadioButton jrbNomeTecnico = new JRadioButton("Nome Técnico");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbPais);
		bg.add(jrbNomeTecnico);
		
		northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
		northWestPanel.add(jrbPais);
		northWestPanel.add(jrbNomeTecnico);	
		
		return northWestPanel;
	}
	
	public JPanel montaNorthEastPanel()
	{
		JPanel northEastPanel= new JPanel();
		northEastPanel.setBorder(BorderFactory.createTitledBorder("Busca:"));
		
		JTextField jtfSearchBox = new JTextField(15);
		
		northEastPanel.add(jtfSearchBox);
		
		return northEastPanel;
	}
	
	private JScrollPane montaCenterPanel()
	{
		String[] nomesColunas = {"País", "Técnico", "Bandeira"};
		
		JTableSGCMF jt = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return jsp;		
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run()
			{
				new LimConsultaSelecao();
			}
		});
		
	}
}
