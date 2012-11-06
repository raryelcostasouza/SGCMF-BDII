/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

/**
 *
 * @author Helio
 */
public class Splash
{
    private JWindow jw;
    private JProgressBar jpb;
    private static Splash splash;

    private Splash()
    {
        ImageIcon ii = new ImageIcon("img/tatu.png");
        JLabel jl = new JLabel(ii);
        jl.setOpaque(true);
        jl.setBackground(Color.white);

        jpb = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        jpb.setValue(0);
        jpb.setPreferredSize(new Dimension(jpb.getWidth(), 12));
        jpb.setStringPainted(true);

        jw = new JWindow();
        jw.setLayout(new BorderLayout());
        jw.add(jl, BorderLayout.CENTER);
        jw.add(jpb, BorderLayout.SOUTH);
        jw.pack();
        jw.setLocationRelativeTo(null);
    }

    public static Splash getInstance()
    {
        if (splash == null)
        {
            splash = new Splash();
        }
        return splash;
    }

    public boolean isVisible()
    {
        return jw.isVisible();
    }

    public void showSplash(int tempo)
    {
        jw.setVisible(true);
        int inc = (int) tempo / 100;
        while (jpb.getValue() < 100)
        {
            try
            {
                Thread.sleep(inc);
                jpb.setValue(jpb.getValue() + 1);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
        }
        jw.setVisible(false);
        jw.dispose();
    }
}
