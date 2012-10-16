package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import sgcmf.util.UtilView;

public class LimConsultaSelecao extends JFrame
{
	public LimConsultaSelecao()
	{
		setTitle("Consulta Seleção");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(300,300);
		
		add(montaMainPanel());		
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel northWestPanel = new JPanel();
		
		JTable jt = new JTable(3, 3);	
		mainPanel.add(jt, BorderLayout.CENTER);
		
		JRadioButton jrbPais = new JRadioButton("País");
		JRadioButton jrbNomeTecnico = new JRadioButton("Nome Técnico");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbPais);
		bg.add(jrbNomeTecnico);
		
		northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
		northWestPanel.add(jrbPais);
		northWestPanel.add(jrbNomeTecnico);	
		
		northPanel.add(northWestPanel, BorderLayout.WEST);
		
		JTextField jtfSearchBox = new JTextField(10);
		northPanel.add(UtilView.putComponentInFlowLayoutPanel(jtfSearchBox), BorderLayout.EAST);
		
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
