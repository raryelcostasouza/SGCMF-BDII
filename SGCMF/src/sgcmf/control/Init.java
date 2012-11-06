package sgcmf.control;

import java.awt.EventQueue;
import sgcmf.view.ShowSplash;

public class Init
{
    public static void main(String[] args)
    {
        new ShowSplash();
        
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                
                new CtrMain();
            }
        });
    }
}
