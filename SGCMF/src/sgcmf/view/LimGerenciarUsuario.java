package sgcmf.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Thatiane
 */
public class LimGerenciarUsuario extends JFrame {

    private PanelCadastrarUsuario pcu;
    private PanelAlterarUsuario pau;

    public LimGerenciarUsuario()
    {
        pcu = new PanelCadastrarUsuario();
        pau = new PanelAlterarUsuario();
        setTitle("Gerenciar Usuarios");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(montaPainel());
    }

    private JTabbedPane montaPainel()
    {
        final JTabbedPane jtp = new JTabbedPane();
        jtp.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                String tituloAba = jtp.getTitleAt(jtp.getSelectedIndex());
                if (tituloAba.equals("Cadastrar"))
                {
                    setSize(380, 335);
                    setLocationRelativeTo(null);
                }
                else if (tituloAba.equals("Alterar"))
                {
                    setSize(700,400);
                    setLocationRelativeTo(null);
                }
            }
        });

        jtp.add(pcu, "Cadastrar");
        jtp.add(pau,"Alterar");
        jtp.add(null, "Remover");
        jtp.add(null, "Consultar");

        return jtp;
    }

}
