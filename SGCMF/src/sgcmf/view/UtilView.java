package sgcmf.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UtilView
{
    public static JPanel putComponentInFlowLayoutPanel(JComponent jc)
    {
        JPanel panel = new JPanel();
        
		panel.add(jc);
        return panel;
    }
	
	public static JPanel putComponentInFlowLayoutPanel(JComponent jc, int flowLayoutAlign)
	{
		JPanel panel = new JPanel(new FlowLayout(flowLayoutAlign));
        
		panel.add(jc);
        return panel;
	}
    
    public static void alinhaLabel (JLabel jl)
    {
        jl.setPreferredSize(new Dimension(130, 20));
        jl.setHorizontalAlignment(JLabel.LEFT);
    }
}
