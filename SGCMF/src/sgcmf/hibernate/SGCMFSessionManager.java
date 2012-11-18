package sgcmf.hibernate;

import org.hibernate.Session;

public class SGCMFSessionManager
{
    private static Session openedSession = null;

    public static Session abrirSessao()
    {
        openedSession = SGCMFHibernateUtil.getSessionFactory().openSession();

        return openedSession;
    }

    public static Session getCurrentSession()
    {
        if (openedSession == null)
        {
            System.err.println("Você tem que abrir uma nova sessão antes de fazer operações com o DAO.\n"
                    + "Para fazê-lo use SGCMFSessionManager.abrirSessao().");
        }
        return openedSession;
    }

    public static void fecharSessao()
    {
        openedSession.close();
        openedSession = null;
    }
}
