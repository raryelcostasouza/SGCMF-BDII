package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LimComiteGestor extends JFrame
{
	public LimComiteGestor()
	{
		setTitle("Usuário Comitê Gestor");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200,200);	
		
		add(montaMainPanel());		
	}
	
	private JPanel montaMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel gridPanel = new JPanel(new GridLayout(2,3));
		
		JButton jbLogout = new JButton("Logout");
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		mainPanel.add(jbLogout, BorderLayout.SOUTH);	
		
		return mainPanel;
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run()
			{
				new LimComiteGestor();
			}
		});
	}
}
