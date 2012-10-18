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

    private PanelCadastrarUsuario pcdu;
    private PanelAlterarUsuario pau;
    private PanelRemoverUsuario pru;
    private PanelConsultarUsuario pcnu;

    public LimGerenciarUsuario()
    {
        pcdu = new PanelCadastrarUsuario();
        pau = new PanelAlterarUsuario();
        pru = new PanelRemoverUsuario();
        pcnu = new PanelConsultarUsuario();
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
                    setSize(400, 400);
                    setLocationRelativeTo(null);
                }
                else if (tituloAba.equals("Alterar"))
                {
                    setSize(800,600);
                    setLocationRelativeTo(null);
                }
                else if(tituloAba.equals("Remover")){
                    setSize(800,600);
                    setLocationRelativeTo(null);
                }
                else if(tituloAba.equals("Consultar")){
                    setSize(600,500);
                    setLocationRelativeTo(null);
                }
            }
        });

        jtp.add(pcdu, "Cadastrar");
        jtp.add(pau,"Alterar");
        jtp.add(pru, "Remover");
        jtp.add(pcnu, "Consultar");

        return jtp;
    }

}
