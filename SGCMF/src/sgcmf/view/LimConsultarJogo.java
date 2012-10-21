package sgcmf.view; 

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.view.table.JTableSGCMF;

public class LimConsultarJogo extends JFrame
{
	protected JPanel mainPanel;
	
	private JPanel northEastPanel;
	private final String nameCardPanelSearchBox = "SEARCH_BOX";
	private final String nameCardPanelComboBox = "COMBO_BOX";
	
	public LimConsultarJogo()
	{
		setTitle("Consulta Jogo");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		add(montaMainPanel());
		pack();
		
		setLocationRelativeTo(null);
	}
	
	private JPanel montaMainPanel()
	{
		mainPanel = new JPanel(new BorderLayout());
		
		mainPanel.add(montaNorthPanel(), BorderLayout.NORTH);
		mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);
		
		return mainPanel;
	}
	
	private JPanel montaNorthPanel()
	{
		JPanel northPanel = new JPanel(new BorderLayout());
		
		northPanel.add(montaNorthEastPanel(), BorderLayout.EAST);
		northPanel.add(montaNorthWestPanel(), BorderLayout.WEST);
				
		return northPanel;
	}
	
	private JPanel montaNorthWestPanel()
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
		
		jrbTipoJogo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cl = (CardLayout)northEastPanel.getLayout();
				cl.show(northEastPanel, nameCardPanelComboBox);
			}
		});
		jrbCidade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				trocarNorthEastPanelParaSearchBox();
			}
		});
		jrbEstadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				trocarNorthEastPanelParaSearchBox();
			}
		});
		jrbSelecao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				trocarNorthEastPanelParaSearchBox();
			}
		});		
		
		northWestPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));
		northWestPanel.add(jrbTipoJogo);
		northWestPanel.add(jrbCidade);	
		northWestPanel.add(jrbEstadio);
		northWestPanel.add(jrbSelecao);
		
		return northWestPanel;
	}
	
	private JPanel montaNorthEastPanel()
	{
		northEastPanel = new JPanel(new CardLayout());
		
		northEastPanel.add(nameCardPanelComboBox, montaNorthEastPanelComboBox());
		northEastPanel.add(nameCardPanelSearchBox, montaNorthEastPanelSearchBox());
				
		return northEastPanel;
	}
	
	private JPanel montaNorthEastPanelSearchBox()
	{
		JPanel northEastPanelSearchBox= new JPanel();
		northEastPanelSearchBox.setBorder(BorderFactory.createTitledBorder("Busca:"));
		
		JTextField jtfSearchBox = new JTextField(15);
		
		northEastPanelSearchBox.add(jtfSearchBox);
		return northEastPanelSearchBox;
	}
	
	private JPanel montaNorthEastPanelComboBox()
	{
		String[] opcoesJCB = {"1ª fase", "Oitavas de Final", "Quartas de Final", "Semifinais", "Final", "Terceiro Lugar"};
		JPanel northEastPanelComboBox = new JPanel();
		northEastPanelComboBox.setBorder(BorderFactory.createTitledBorder("Tipo jogo:"));
		
		JComboBox jcb = new JComboBox(opcoesJCB);
		
		northEastPanelComboBox.add(jcb);
		return northEastPanelComboBox;		
	}
	
	private JScrollPane montaCenterPanel()
	{
		String[] nomesColunas = {"Tipo do Jogo", "Data/Hora", "Cidade", "Estádio", "Seleção I", "Seleção II", "Prorrogacao"};
		
		JTableSGCMF jt = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return jsp;		
	}
	
	
	private void trocarNorthEastPanelParaSearchBox()
	{
		CardLayout cl = (CardLayout)northEastPanel.getLayout();
		cl.show(northEastPanel, nameCardPanelSearchBox);
	}	
}
