package sgcmf.view.tabela;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Helio
 */
public class RodandoPainel
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run()
            {
                JFrame jf = new JFrame();
                jf.setVisible(true);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setLocationRelativeTo(null);
                
                jf.add(new PrimeiraFase());
                jf.setSize(800,400);
                jf.setLocationRelativeTo(null);
            }
        });
    }
}
