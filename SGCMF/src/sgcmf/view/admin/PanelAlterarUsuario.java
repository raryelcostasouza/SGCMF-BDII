package sgcmf.view.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrUsuario;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;
import sgcmf.view.UtilView;
import sgcmf.view.table.JTableSGCMF;

/**
 *
 * @author Thatiane
 */
public class PanelAlterarUsuario extends JPanel {


    JButton jbAlterar = new JButton("Alterar");
    private JComboBox jcbPerfil;
    private String[] items =
        {
            "Administrador", "Tecnico da selecao", "Membro Comite",
            "Entusiasta"
        };

    JTextField jtfLogin = new JTextField(10);
    JTextField jtfSenha = new JTextField(10);
    JTextField jtfNome = new JTextField(10);
    JTextField jtfEmail = new JTextField(10);
    JTextField jtfCPF = new JTextField(10);
    CtrUsuario ctrUsuario = new CtrUsuario();

    JRadioButton jrbNome = new JRadioButton("Nome");
    JRadioButton jrbPerfil = new JRadioButton("Perfil");
    JRadioButton jrbLogin = new JRadioButton("Login");
    JRadioButton jrbEmail = new JRadioButton("Email");
    private JTableSGCMF jt;
    JTextField jtfPesquisar = new JTextField(15);

    public PanelAlterarUsuario()
    {
        setLayout(new BorderLayout());
        montaPainelPrincipal();
    }

    private void montaPainelPrincipal()
    {
        JPanel jpNorth = montaPainelNorte();
        JScrollPane jpCenter = montaPainelCentral();
        JPanel jpSouth = montaPainelSouth();

        this.add(jpNorth,BorderLayout.NORTH);
        this.add(jpCenter,BorderLayout.CENTER);
        this.add(jpSouth,BorderLayout.SOUTH);

    }

    private JPanel montaPainelNorte()
    {
        JPanel jpNorth = new JPanel(new BorderLayout());
        JPanel jpEsquerda = new JPanel();
        JPanel jpDireita = new JPanel();

        

        jtfPesquisar = new JTextField(15);
        jtfPesquisar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pesquisar(jtfPesquisar.getText());
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbNome);
        bg.add(jrbLogin);
        bg.add(jrbPerfil);
        bg.add(jrbEmail);

        jpEsquerda.add(jrbNome);
        jpEsquerda.add(jrbLogin);
        jpEsquerda.add(jrbPerfil);
        jpEsquerda.add(jrbEmail);
        jpEsquerda.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));

        jpDireita.add(UtilView.putComponentInFlowLayoutPanel(jtfPesquisar));
        jpDireita.setBorder(BorderFactory.createTitledBorder("Buscar:"));

        jpNorth.add(jpEsquerda, BorderLayout.WEST);
        jpNorth.add(jpDireita, BorderLayout.EAST);

        return jpNorth;
    }

    private JScrollPane montaPainelCentral()
    {
        String[] nomeColunas =
        {
            "Login", "Senha", "Perfil", "Nome", "E-mail", "CPF"
        };
        jt = new JTableSGCMF(null, nomeColunas);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    private JPanel montaPainelSouth()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(3, 2));

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


        jrbNome.setSelected(true);

        jbAlterar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ResultadoOperacao resultado;

                String perfil = (String) jcbPerfil.getSelectedItem();
                
                resultado = ctrUsuario.alterarUsuario(jtfCPF.getText(),jtfNome.getText(),
                        jtfEmail.getText(),jtfLogin.getText(),jtfSenha.getText(), perfil);


                if (resultado.getTipo().equals(TipoResultadoOperacao.ERRO))
                {
                    JOptionPane.showMessageDialog(null, resultado.getMsg(), "Erro"
                            + " na alteração de Usuario", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, resultado.getMsg(), "Alteração"
                            + " bem Sucedida", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                }
            }
        });


        jcbPerfil = new JComboBox(items);
        jcbPerfil.setEditable(false);
        jcbPerfil.setPreferredSize(new Dimension(132, 20));

        
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

        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbAlterar), BorderLayout.SOUTH);

        return jpPrincipal;
    }

    public void travarBotoes()
    {
        jbAlterar.setEnabled(false);
    }

    public void limparCampos()
    {
        jtfCPF.setText("");
        jtfNome.setText("");
        jtfEmail.setText("");
        jtfLogin.setText("");
        jtfSenha.setText("");
    }

     private void pesquisar(String chavePesquisa)
    {
        String[][] dadosUsuarios;
        if (jrbNome.isSelected())
        {
    //        dadosUsuarios = ctrUsuario.queryAllDataJogadorByNomeAndByUser(chavePesquisa, ctrTecnico.getUser());
        }
        else
        {
   //         dadosUsuarios = ctrUsuario.queryAllDataJogadorByPosicaoAndByUser(chavePesquisa, ctrTecnico.getUser());

        }
        //limparParteCampos();
        travarBotoes();
     //   jt.preencheTabela(dadosUsuarios);
    }
}
