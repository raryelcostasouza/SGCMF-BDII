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
import sgcmf.view.table.DefaultTableModelSGCMF;
import sgcmf.view.table.JLabelTableCellRenderer;
import sgcmf.view.table.JTableSGCMF;
import sgcmf.view.table.ReceiveRowDataSGCMF;

/**
 *
 * @author Thatiane
 */
public class PanelRemoverUsuario extends JPanel implements ReceiveRowDataSGCMF
{

    CtrUsuario ctrUsuario = new CtrUsuario();
    JTableSGCMF jt;
    JTextField jtfPesquisar;
    JRadioButton jrbNome;
    JRadioButton jrbPerfil;
    JRadioButton jrbLogin;
    JRadioButton jrbEmail;

    

    JTextField jtfLogin = new JTextField(10);
    JTextField jtfSenha = new JTextField(10);
    JTextField jtfNome = new JTextField(10);
    JTextField jtfEmail = new JTextField(10);
    JTextField jtfCPF = new JTextField(10);
    JTextField jtfPerfil = new JTextField(10);
    JButton jbRemover;

     public PanelRemoverUsuario()
    {
        setLayout(new BorderLayout());
        montaPainelPrincipal();
    }

    private void montaPainelPrincipal()
    {
        //JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpNorth = montaPainelNorte();
        JScrollPane jpCenter = montaPainelCentral();
        JPanel jpSouth = montaPainelSouth();
        recarregaTodosusuarios();
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
                pesquisa(jtfPesquisar.getText());
            }
        });

        jrbNome = new JRadioButton("Nome");
        jrbNome.setSelected(true);
        jrbPerfil = new JRadioButton("Perfil");
        jrbLogin = new JRadioButton("Login");
        jrbEmail = new JRadioButton("Email");


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
            "ID", "CPF", "E-mail", "Nome", "Login", "Senha", "Perfil"
        };

        jt = new JTableSGCMF(null, nomeColunas, this);
        jt.setModel(new DefaultTableModelSGCMF(null, nomeColunas));
        jt.setDefaultRenderer(JLabel.class, new JLabelTableCellRenderer());
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

        //travarBotao();
        jbRemover = new JButton("Remover");
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlLogin));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfLogin, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlSenha));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfSenha, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlPerfil));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfPerfil, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlNome));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfNome, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlEmail));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfEmail, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlCPF));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfCPF, FlowLayout.LEFT));
        jpAux.setBorder(BorderFactory.createEtchedBorder());

        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbRemover), BorderLayout.SOUTH);

        jbRemover.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ResultadoOperacao resultado;
                CtrUsuario ctrUsuario = new CtrUsuario();
                 
                resultado = ctrUsuario.removerUsuario(jt.getValueAt(jt.getSelectedRow(), 4).toString());

                if (resultado.getTipo().equals(TipoResultadoOperacao.ERRO))
                {
                    JOptionPane.showMessageDialog(null, resultado.getMsg(), "Erro"
                            + " na remoção de Usuario", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, resultado.getMsg(), "Remoção"
                            + " bem Sucedida", JOptionPane.INFORMATION_MESSAGE);
                    limparTodosCampos();
                    recarregaTodosusuarios();
                }
            }
        });


        return jpPrincipal;
    }

    private void pesquisa(String chavePesquisa)
    {
        Object[][] dadosUsuarios = null;

        if (jrbNome.isSelected())
        {
            dadosUsuarios = ctrUsuario.queryUsuarioByNomeUsuario(chavePesquisa);
        }
        else if (jrbPerfil.isSelected())
        {
            dadosUsuarios = ctrUsuario.queryUsuarioByPerfilUsuario(chavePesquisa);
        }
        else if (jrbLogin.isSelected())
        {
            dadosUsuarios = ctrUsuario.queryUsuarioByLoginUsuario(chavePesquisa);
        }
        else
        {
            dadosUsuarios = ctrUsuario.queryUsuarioByEmailUsuario(chavePesquisa);
        }

        jt.preencheTabela(dadosUsuarios);
    }

    @Override
    public void receiveRowData(String[] dados)
    {
        jtfLogin.setText(dados[4]);
        jtfNome.setText(dados[3]);
        jtfEmail.setText(dados[2]);
        jtfSenha.setText(dados[5]);
        jtfCPF.setText(dados[1]);
        jtfPerfil.setText(dados[6]);
    }

    public void limparTodosCampos()
    {
        jtfLogin.setText("");
        jtfPesquisar.setText("");
        jtfNome.setText("");
        jtfEmail.setText("");
        jtfSenha.setText("");
        jtfCPF.setText("");
    }

    private void recarregaTodosusuarios()
    {
        Object[][] dadosUsuarios;

        dadosUsuarios = ctrUsuario.queryUsuarioTodos();
        jt.preencheTabela(dadosUsuarios);
    }
}
