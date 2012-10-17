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

public class LimConsultarJogo extends JFrame
{
	public LimConsultarJogo()
	{
		setTitle("Consulta Jogo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		add(montaMainPanel());
		pack();
		
		setLocationRelativeTo(null);
	}
	
	public JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		mainPanel.add(montaNorthPanel(), BorderLayout.NORTH);
		mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);
		
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
		
		JRadioButton jrbTipoJogo = new JRadioButton("Tipo de Jogo");
		jrbTipoJogo.setSelected(true);
		JRadioButton jrbCidade = new JRadioButton("Cidade");
		JRadioButton jrbEstadio = new JRadioButton("Estádio");
		JRadioButton jrbSelecao = new JRadioButton("Seleção");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbTipoJogo);
		bg.add(jrbCidade);
		bg.add(jrbEstadio);
		bg.add(jrbSelecao);
		
		northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
		northWestPanel.add(jrbTipoJogo);
		northWestPanel.add(jrbCidade);	
		northWestPanel.add(jrbEstadio);
		northWestPanel.add(jrbSelecao);
		
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
	
	public JScrollPane montaCenterPanel()
	{
		String[] nomesColunas = {"Tipo do Jogo", "Data/Hora", "Cidade", "Estádio", "Seleção I", "Seleção II", "Prorrogacao"};
		
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
				new LimConsultarJogo();
			}
		});
	}
	
}
