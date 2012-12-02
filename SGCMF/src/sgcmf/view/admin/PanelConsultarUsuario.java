package sgcmf.view.admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrUsuario;
import sgcmf.view.util.UtilView;
import sgcmf.view.util.DefaultTableModelSGCMF;
import sgcmf.view.util.JTableSGCMF;

/**
 *
 * @author Thatiane
 */
public class PanelConsultarUsuario extends JPanel {

    CtrUsuario ctrUsuario = new CtrUsuario();
    JTableSGCMF jt;
    JTextField jtfPesquisar;
    JRadioButton jrbNome;
    JRadioButton jrbPerfil;
    JRadioButton jrbLogin;
    JRadioButton jrbEmail;

    public PanelConsultarUsuario()
    {
        setLayout(new BorderLayout());
        montaPainelPrincipal();
    }

    private void montaPainelPrincipal()
    {
        //JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpNorth = montaPainelNorte();
        JScrollPane jpCenter = montaPainelCentral();
        recarregaTodosusuarios();
        this.add(jpNorth,BorderLayout.NORTH);
        this.add(jpCenter,BorderLayout.CENTER);

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
        
        jt = new JTableSGCMF(null, nomeColunas);
        jt.setModel(new DefaultTableModelSGCMF(null, nomeColunas));
        
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    private void recarregaTodosusuarios()
    {
        Object[][] dadosUsuarios;

        dadosUsuarios = ctrUsuario.queryUsuarioTodos();
        jt.preencheTabela(dadosUsuarios);
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

}
