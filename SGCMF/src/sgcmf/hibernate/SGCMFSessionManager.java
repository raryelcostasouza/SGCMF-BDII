package sgcmf.hibernate;

import org.hibernate.Session;

public class SGCMFSessionManager
{
    private static Session openedSession = null;

    public static Session openSession()
    {
        openedSession = SGCMFHibernateUtil.getSessionFactory().openSession();

        return openedSession;
    }

    public static Session getCurrentSession()
    {
        if (openedSession == null)
        {
            System.err.println("Você tem que abrir uma nova sessão antes de fazer operações com o DAO.\n"
                    + "Para fazê-lo use SGCMFSessionManager.openSession().");
        }
        return openedSession;
    }

    public static void closeSession()
    {
        openedSession.close();
        openedSession = null;
    }
}
