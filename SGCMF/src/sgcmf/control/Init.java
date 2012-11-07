package sgcmf.control;

import java.awt.EventQueue;

public class Init
{
    public static void main(String[] args)
    {        
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
