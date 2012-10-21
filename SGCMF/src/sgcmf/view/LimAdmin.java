package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 *
 * @author Raryel Costa Souza
 */
public class LimAdmin extends JFrame
{
    private LimGerenciarUsuario limGerenciarUsuario;
    public LimAdmin(){
        setTitle("Usuário Administrador");
        setSize(600, 200);
        setLocationRelativeTo(null);
        //setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(montaPainel());

        limGerenciarUsuario = new LimGerenciarUsuario();
    }

    private JPanel montaPainel()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(1, 1));

        JButton jbGerenciarUsuarios = new JButton("Gerenciar Usuários");
        jbGerenciarUsuarios.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                limGerenciarUsuario.setVisible(true);
            }
        });

        JButton jbRelatorios = new JButton("Relatórios");
        JButton jbTabelaCampeonato = new JButton("Tabela do Campeonato");

        JButton jbLogout = new JButton("Logout");

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jbGerenciarUsuarios));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jbRelatorios));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jbTabelaCampeonato));

        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbLogout), BorderLayout.SOUTH);

        return jpPrincipal;
    }
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run()
            {
                new LimAdmin();
            }
        });
    }
}
