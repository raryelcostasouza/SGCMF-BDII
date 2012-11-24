package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sgcmf.control.CtrEntusiasta;
import sgcmf.model.other.SGCMFIcons;

public class LimEntusiasta extends JFrame
{
    private CtrEntusiasta ctrEntusiasta;
    private final Dimension buttonDimension = new Dimension(180, 180);

    public LimEntusiasta(CtrEntusiasta ctrEntusiasta)
    {
        this.ctrEntusiasta = ctrEntusiasta;
        
        setTitle("SGCMF | Usuário Entusiasta");
        setIconImage(SGCMFIcons.LOGO.getImage());
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        add(montaMainPanel());
        pack();
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                acaoLogout();
            }
        });
    }

    private JPanel montaMainPanel()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(montaSouthPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel montaCenterPanel()
    {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));

        JButton jbRelatorio = new JButton("Relatórios", SGCMFIcons.RELATORIO);
        jbRelatorio.setVerticalTextPosition(JButton.BOTTOM);
        jbRelatorio.setHorizontalTextPosition(JButton.CENTER);
        jbRelatorio.setPreferredSize(buttonDimension);
        
        jbRelatorio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrEntusiasta.getCtrRelatorio().ativaTela();
            }
        });
        
        JButton jbTabelaCampeonato = new JButton("Tabela do Campeonato", SGCMFIcons.TABELA);
        jbTabelaCampeonato.setVerticalTextPosition(JButton.BOTTOM);
        jbTabelaCampeonato.setHorizontalTextPosition(JButton.CENTER);
        jbTabelaCampeonato.setPreferredSize(buttonDimension);
        jbTabelaCampeonato.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrEntusiasta.getCtrTabelaCampeonato().ativaTela();
            }
        });
        
        centerPanel.add(UtilView.putComponentInFlowLayoutPanel(jbRelatorio));
        centerPanel.add(UtilView.putComponentInFlowLayoutPanel(jbTabelaCampeonato));
        
        return centerPanel;
    }

    private JPanel montaSouthPanel()
    {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton jbLogout = new JButton("Logout", SGCMFIcons.LOGOUT);
        jbLogout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                acaoLogout();
            }
        });                
        
        southPanel.add(jbLogout);
        
        return southPanel;
    }

    private void acaoLogout()
    {
        int op;

        op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja fazer logout do sistema?", "Confirmação de Logout", JOptionPane.YES_NO_OPTION);

        if (op == JOptionPane.OK_OPTION)
        {
            setVisible(false);
            ctrEntusiasta.logout();
        }
    }
}
