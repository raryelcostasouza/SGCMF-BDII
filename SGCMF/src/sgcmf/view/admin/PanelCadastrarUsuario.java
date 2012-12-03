package sgcmf.view.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sgcmf.control.CtrAdmin;
import sgcmf.control.CtrUsuario;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;
import sgcmf.view.util.UtilView;

/**
 *
 * @author Thatiane
 */
public class PanelCadastrarUsuario extends JPanel {

    private CtrUsuario ctrUsuario = new CtrUsuario();
    JTextField jtfLogin = new JTextField(10);
    JTextField jtfSenha = new JTextField(10);
    JTextField jtfNome = new JTextField(10);
    JTextField jtfEmail = new JTextField(10);
    JTextField jtfCPF = new JTextField(10);
    private JComboBox jcbPerfil;
    private String[] items =
    {
        "Administrador", "Membro Comite",
        "Entusiasta"
    };

    
   
     public PanelCadastrarUsuario(CtrAdmin ctrAdmin)
    {
        setLayout(new BorderLayout());

        montaPainel();
    }

    private void montaPainel()
    {
        JPanel jpAux = new JPanel(new GridLayout(6, 1));

        JLabel jlLogin = new JLabel("Login:");
        UtilView.alinhaLabel(jlLogin);
        JLabel jlSenha = new JLabel("Senha:");
        UtilView.alinhaLabel(jlSenha);
        JLabel jlPerfil = new JLabel("Perfil:");
        UtilView.alinhaLabel(jlPerfil);
        JLabel jlNome = new JLabel("Nome:");
        UtilView.alinhaLabel(jlNome);
        JLabel jlEmail = new JLabel("E-mail:");
        UtilView.alinhaLabel(jlEmail);
        JLabel jlCPF = new JLabel("CPF:");
        UtilView.alinhaLabel(jlCPF);

        jcbPerfil = new JComboBox(items);
        jcbPerfil.setEditable(false);
        jcbPerfil.setPreferredSize(new Dimension(132, 20));

        JButton jbCadastrar = new JButton("Cadastrar");

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlLogin));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfLogin, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlSenha));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfSenha, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlPerfil));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jcbPerfil, FlowLayout.LEFT));
        jpAux.setBorder(BorderFactory.createEtchedBorder());
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlNome));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfNome, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlEmail));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfEmail, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlCPF));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfCPF, FlowLayout.LEFT));
        jpAux.setBorder(BorderFactory.createEtchedBorder());

        this.add(jpAux, BorderLayout.CENTER);
        this.add(UtilView.putComponentInFlowLayoutPanel(jbCadastrar), BorderLayout.SOUTH);

        jbCadastrar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ResultadoOperacao resultado;

                String perfil = (String) jcbPerfil.getSelectedItem();

                if(jtfLogin.getText().equals("") || jtfSenha.getText().equals("") || jtfEmail.getText().equals("")
                        || jtfNome.getText().equals("") || jtfCPF.getText().equals("") )
                {
                 JOptionPane.showMessageDialog(null, "Campos em branco, preenche corretamente", "Erro"
                            + " no Cadastro de Usuario", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    resultado = ctrUsuario.cadastrarUsuario(jtfCPF.getText(),jtfNome.getText(),
                            jtfEmail.getText(),jtfLogin.getText(),jtfSenha.getText(), perfil);

                    if (resultado.getTipo().equals(TipoResultadoOperacao.ERRO))
                    {
                        JOptionPane.showMessageDialog(null, resultado.getMsg(), "Erro"
                                + " no Cadastro de Usuario", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, resultado.getMsg(), "Cadastro"
                                + " bem Sucedido", JOptionPane.INFORMATION_MESSAGE);
                        limparCampos();
                    }
                }
            }
        });
    }

    public void limparCampos()
    {
        jtfCPF.setText("");
        jtfNome.setText("");
        jtfEmail.setText("");
        jtfLogin.setText("");
        jtfSenha.setText("");
    }

}
