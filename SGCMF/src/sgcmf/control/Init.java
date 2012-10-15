/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.control;

import java.awt.EventQueue;

/**
 *
 * @author Raryel Costa Souza
 */
public class Init
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run()
			{
				new CtrMain();
			}
		});
	}
}
