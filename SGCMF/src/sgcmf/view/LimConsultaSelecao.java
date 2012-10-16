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
import javax.swing.JTable;
import javax.swing.JTextField;
import sgcmf.util.UtilView;
import sgcmf.view.table.JTableSGCMF;

public class LimConsultaSelecao extends JFrame
{
	public LimConsultaSelecao()
	{
		setTitle("Consulta Seleção");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setSize(300,300);
		
		add(montaMainPanel());		
		pack();
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel northWestPanel = new JPanel();
		JPanel northEastPanel= new JPanel();
		String[] nomesColunas = {"País", "Técnico", "Bandeira"};
		
		JTableSGCMF jt = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(jsp, BorderLayout.CENTER);
		
		JRadioButton jrbPais = new JRadioButton("País");
		jrbPais.setSelected(true);
		JRadioButton jrbNomeTecnico = new JRadioButton("Nome Técnico");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbPais);
		bg.add(jrbNomeTecnico);
		
		northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
		northWestPanel.add(jrbPais);
		northWestPanel.add(jrbNomeTecnico);	
		
		northPanel.add(northWestPanel, BorderLayout.WEST);
		
		JLabel jlBusca = new JLabel("Busca:");
		JTextField jtfSearchBox = new JTextField(15);
		northEastPanel.add(jlBusca);
		northEastPanel.add(jtfSearchBox);
		
		northPanel.add(northEastPanel, BorderLayout.EAST);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		return mainPanel;
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
