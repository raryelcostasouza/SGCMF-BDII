/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view;

/**
 *
 * @author Helio
 */
public class ShowSplash extends Thread
{
    private Splash splash;

    public ShowSplash()
    {
    }

    @Override
    public void run()
    {
        showSplash();
        while (splash.isVisible())
        {
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
        }
    }

    private void showSplash()
    {
        splash = Splash.getInstance();
        splash.showSplash(5000);
    }
}
