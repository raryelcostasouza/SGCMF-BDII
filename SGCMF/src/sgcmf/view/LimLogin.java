package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import sgcmf.control.CtrMain;
import sgcmf.model.other.SGCMFIcons;

public class LimLogin extends JFrame
{
    private CtrMain ctrMain;
    private JTextField jtfLogin;
    private JPasswordField jpSenha;

    public LimLogin(CtrMain ctrMain)
    {
        setIconImage(SGCMFIcons.LOGO.getImage());
        this.ctrMain = ctrMain;

        setTitle("SGCMF | Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(montaPainel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel montaPainel()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(2, 1));

        JLabel jlLogo = new JLabel(SGCMFIcons.LOGO);

        JLabel jlLogin = new JLabel("Login:");
        UtilView.alinhaLabel(jlLogin);
        JLabel jlSenha = new JLabel("Senha:");
        UtilView.alinhaLabel(jlSenha);

        jtfLogin = new JTextField(10);
        jpSenha = new JPasswordField(10);
        JButton jbLogin = new JButton("Entrar", SGCMFIcons.LOGIN);
        jbLogin.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrMain.login(jtfLogin.getText(), jpSenha.getPassword());
            }
        });

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlLogin));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfLogin, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlSenha));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jpSenha, FlowLayout.LEFT));

        jpAux.setBorder(BorderFactory.createEtchedBorder());


        this.add(jpAux, BorderLayout.CENTER);

        jpPrincipal.add(jlLogo, BorderLayout.NORTH);
        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbLogin), BorderLayout.SOUTH);

        return jpPrincipal;
    }
}
