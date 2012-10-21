package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import sgcmf.view.table.JTableSGCMF;

public class LimGerenciarOcorrenciasJogo extends JFrame
{
	private LimRegistrarGol limRegistrarGol;
	private LimRegistrarFalta limRegistrarFalta;
	private LimRegistrarCartao limRegistrarCartao;
	private LimRegistrarSubstituicao limRegistrarSubstituicao;
	private LimRegistrarRoubadaBola limRegistrarRoubadaBola;
	
	private final String nameCardPanelGol = "GOL";
	private final String nameCardPanelFalta = "FALTA";
	private final String nameCardPanelCartao = "CARTAO";
	private final String nameCardPanelSubst = "SUBST";
	private final String nameCardPanelRoubadaBola = "ROUBADA";
	
	private JPanel centerPanel;
	
	public LimGerenciarOcorrenciasJogo(LimBuscarJogador limBuscarJogador)
	{
		limRegistrarGol = new LimRegistrarGol(limBuscarJogador);
		limRegistrarFalta = new LimRegistrarFalta(limBuscarJogador);
		limRegistrarCartao = new LimRegistrarCartao(limBuscarJogador);
		limRegistrarSubstituicao = new LimRegistrarSubstituicao(limBuscarJogador);
		limRegistrarRoubadaBola = new LimRegistrarRoubadaBola(limBuscarJogador);
		
		setTitle("Gerenciar Ocorrências para o Jogo Selecionado");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		add(montaMainPanel());
		
		setSize(600,500);
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
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel rotuloJogoPanel = new JPanel();
		rotuloJogoPanel.setBorder(BorderFactory.createTitledBorder("Informações do jogo selecionado:"));
		
		JLabel jlInfoJogo = new JLabel("Data/Hora | Tipo do Jogo | SeleçãoI x SeleçãoII", JLabel.CENTER);
		rotuloJogoPanel.add(jlInfoJogo);		
		
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setBorder(BorderFactory.createTitledBorder("Ver ocorrências do tipo:"));
		
		ButtonGroup bg = new ButtonGroup();
		JRadioButton jrbGol = new JRadioButton("Gol");
		jrbGol.setSelected(true);
		JRadioButton jrbFalta = new JRadioButton("Falta");
		JRadioButton jrbCartao = new JRadioButton("Cartão");
		JRadioButton jrbSubst = new JRadioButton("Substituição");
		JRadioButton jrbRoubadaBola = new JRadioButton("Roubada de bola");
		
		bg.add(jrbGol);
		bg.add(jrbFalta);
		bg.add(jrbCartao);
		bg.add(jrbSubst);
		bg.add(jrbRoubadaBola);
		
		jrbGol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cl = (CardLayout) centerPanel.getLayout();
				cl.show(centerPanel, nameCardPanelGol);
			}
		});
		
		jrbFalta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cl = (CardLayout) centerPanel.getLayout();
				cl.show(centerPanel, nameCardPanelFalta);
			}
		});
		
		jrbCartao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cl = (CardLayout) centerPanel.getLayout();
				cl.show(centerPanel, nameCardPanelCartao);
			}
		});
		
		jrbSubst.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cl = (CardLayout) centerPanel.getLayout();
				cl.show(centerPanel, nameCardPanelSubst);
			}
		});
		
		jrbRoubadaBola.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cl = (CardLayout) centerPanel.getLayout();
				cl.show(centerPanel, nameCardPanelRoubadaBola);
			}
		});
		
		radioButtonPanel.add(jrbGol);
		radioButtonPanel.add(jrbFalta);
		radioButtonPanel.add(jrbCartao);
		radioButtonPanel.add(jrbSubst);
		radioButtonPanel.add(jrbRoubadaBola);
		
		
		northPanel.add(rotuloJogoPanel, BorderLayout.NORTH);
		northPanel.add(radioButtonPanel, BorderLayout.CENTER);
		
		return northPanel;
	}
	
	private JPanel montaCenterPanel()
	{
		centerPanel = new JPanel(new CardLayout());		
		
		centerPanel.add(nameCardPanelGol, montaCenterPanelGol());
		centerPanel.add(nameCardPanelFalta, montaCenterPanelFalta());
		centerPanel.add(nameCardPanelCartao, montaCenterPanelCartao());
		centerPanel.add(nameCardPanelSubst, montaCenterPanelSubst());
		centerPanel.add(nameCardPanelRoubadaBola, montaCenterPanelRoubadaBola());
		
		return centerPanel;
	}
	
	private JPanel montaCenterPanelGol()
	{
		JPanel centerPanelGol = new JPanel(new BorderLayout());
		
		String[] nomesColunas = {"Instante de Tempo", "Jogador Autor", "Jogador Assistência", "Tipo Gol", "Modo"};
		JTableSGCMF jtGol = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jtGol,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		centerPanelGol.add(jsp,BorderLayout.CENTER);
		
		JButton jbRegistrarGol = new JButton("Registrar Novo Gol");
		jbRegistrarGol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limRegistrarGol.setVisible(true);
			}
		});
		centerPanelGol.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarGol), BorderLayout.SOUTH);
			
		return centerPanelGol;
	}
	
	private JPanel montaCenterPanelFalta()
	{
		JPanel centerPanelFalta = new JPanel(new BorderLayout());
		
		String[] nomesColunas = {"Instante de Tempo", "Jogador Autor", "Cartão", "Tipo"};
		JTableSGCMF jtFalta = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jtFalta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		centerPanelFalta.add(jsp,BorderLayout.CENTER);
		
		JButton jbRegistrarFalta = new JButton("Registrar Nova Falta");
		jbRegistrarFalta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limRegistrarFalta.setVisible(true);
			}
		});
		centerPanelFalta.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarFalta), BorderLayout.SOUTH);
			
		return centerPanelFalta;
	}
	
	private JPanel montaCenterPanelCartao()
	{
		JPanel centerPanelCartao = new JPanel(new BorderLayout());
		
		String[] nomesColunas = {"Instante de Tempo", "Jogador", "Cor"};
		JTableSGCMF jtCartao = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jtCartao,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		centerPanelCartao.add(jsp,BorderLayout.CENTER);
		
		JButton jbRegistrarCartao = new JButton("Registrar Novo Cartão");
		jbRegistrarCartao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limRegistrarCartao.setVisible(true);
			}
		});
		centerPanelCartao.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarCartao), BorderLayout.SOUTH);
			
		return centerPanelCartao;
	}
	
	private JPanel montaCenterPanelSubst()
	{
		JPanel centerPanelSubst = new JPanel(new BorderLayout());
		
		String[] nomesColunas = {"Instante de Tempo", "Jogador Entrou", "Jogador Saiu", "Motivo"};
		JTableSGCMF jtSubst = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jtSubst,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		centerPanelSubst.add(jsp,BorderLayout.CENTER);
		
		JButton jbRegistrarSubst = new JButton("Registrar Nova Substituição");
		jbRegistrarSubst.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limRegistrarSubstituicao.setVisible(true);
			}
		});
		centerPanelSubst.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarSubst), BorderLayout.SOUTH);
			
		return centerPanelSubst;
	}
	
	private JPanel montaCenterPanelRoubadaBola()
	{
		JPanel centerPanelRoubadaBola = new JPanel(new BorderLayout());
		
		String[] nomesColunas = {"Instante de Tempo", "Jogador Ladrão"};
		JTableSGCMF jtRoubadaBola = new JTableSGCMF(null, nomesColunas);	
		JScrollPane jsp = new JScrollPane(jtRoubadaBola,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		centerPanelRoubadaBola.add(jsp,BorderLayout.CENTER);
		
		JButton jbRegistrarRoubadaBola = new JButton("Registrar Nova Roubada de Bola");
		jbRegistrarRoubadaBola.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				limRegistrarRoubadaBola.setVisible(true);
			}
		});
		centerPanelRoubadaBola.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarRoubadaBola), BorderLayout.SOUTH);
			
		return centerPanelRoubadaBola;
	}
	
	private JPanel montaSouthPanel()
	{
		JPanel southPanel = new JPanel();
		
		JButton jbRemoverOcorrencia = new JButton("Remover Ocorrência");
		southPanel.add(jbRemoverOcorrencia);
		
		return southPanel;
	}
}
