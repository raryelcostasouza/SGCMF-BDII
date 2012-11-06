/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view;

/**
 *
 * @author Helio
 */
public class ShowSplash
{
    private Splash splash;

    public ShowSplash()
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
