package sgcmf.util;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class UtilView
{
	public static JPanel putComponentInFlowLayoutPanel(JComponent jc)
	{
		JPanel panel = new JPanel();
		panel.add(jc);
		
		return panel;
	}
}
