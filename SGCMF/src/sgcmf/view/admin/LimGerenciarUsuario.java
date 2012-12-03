package sgcmf.view.admin;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sgcmf.control.CtrAdmin;
import sgcmf.model.other.SGCMFIcons;

/**
 *
 * @author Thatiane
 */
public class LimGerenciarUsuario extends JFrame {

    private PanelCadastrarUsuario pcdu;
    private PanelAlterarUsuario pau;
    private PanelRemoverUsuario pru;
    private PanelConsultarUsuario pcnu;

    public LimGerenciarUsuario(CtrAdmin ctrAdmin)
    {
        setIconImage(SGCMFIcons.USUARIO.getImage());
        pcdu = new PanelCadastrarUsuario(ctrAdmin);
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
                    setSize(1000,600);
                    setLocationRelativeTo(null);
                    pau.recarregaTodosusuarios();
                    pau.limparTodosCampos();
                }
                else if(tituloAba.equals("Remover"))
                {
                    setSize(1000,600);
                    setLocationRelativeTo(null);
                    pru.recarregaTodosusuarios();
                    pru.limparTodosCampos();
                }
                else if(tituloAba.equals("Consultar")){
                    setSize(1000,500);
                    setLocationRelativeTo(null);
                    pcnu.recarregaTodosusuarios();

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
