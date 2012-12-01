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
        JPanel jpAuxI = new JPanel(new BorderLayout());
        JPanel jpAuxII = new JPanel(new GridLayout(2, 2));

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
                jtfLogin.setText("");
                jpSenha.setText("");
            }
        });

        jpAuxII.add(UtilView.putComponentInFlowLayoutPanel(jlLogin));
        jpAuxII.add(UtilView.putComponentInFlowLayoutPanel(jtfLogin, FlowLayout.LEFT));
        jpAuxII.add(UtilView.putComponentInFlowLayoutPanel(jlSenha));
        jpAuxII.add(UtilView.putComponentInFlowLayoutPanel(jpSenha, FlowLayout.LEFT));
        jpAuxI.add(jpAuxII, BorderLayout.CENTER);
        jpAuxI.add(UtilView.putComponentInFlowLayoutPanel(jbLogin),BorderLayout.SOUTH);
        jpAuxI.setBorder(BorderFactory.createEtchedBorder());
        
        jpPrincipal.add(jlLogo, BorderLayout.WEST);
        jpPrincipal.add(jpAuxI, BorderLayout.CENTER);

        return jpPrincipal;
    }
}
