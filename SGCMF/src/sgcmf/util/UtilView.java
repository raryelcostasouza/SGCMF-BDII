/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.util;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Raryel Costa Souza
 */
public class UtilView
{
	public static JPanel diminuirComponente(JComponent jc)
	{
		JPanel panel = new JPanel();
		panel.add(jc);
		return panel;
	}
}
