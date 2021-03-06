/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sgcmf.control.CtrJogo;
import sgcmf.control.CtrRelatorio;
import sgcmf.control.CtrTecnico;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.util.UtilView;

/**
 *
 * @author Raryel Costa Souza
 */
public class LimTecnico extends JFrame
{
    private LimGerenciarJogador limGerenciarJogador;
    private CtrTecnico ctrTecnico;
    private CtrRelatorio ctrRelatorio;
    
    public LimTecnico(CtrTecnico ctrTecnico, CtrJogo ctrJogo)
    {
        this.ctrTecnico = ctrTecnico;
        ctrRelatorio = ctrTecnico.getCtrMain().getCtrRelatorio();
        setIconImage(SGCMFIcons.LOGO.getImage());
        limGerenciarJogador = new LimGerenciarJogador(ctrTecnico);
        
        setTitle("SGCMF | Usuário Técnico da Seleção");
        add(montaPainel());
        pack();
        setLocationRelativeTo(null);
        setVisible(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                acaoLogout();
                ctrRelatorio.desativaTela();
            }
        });
    }
    
    private JPanel montaPainel()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        
        jpPrincipal.add(montaNorthPanel(), BorderLayout.NORTH);
        jpPrincipal.add(montaCenterPanel(), BorderLayout.CENTER);
        
        JButton jbLogout = new JButton("Logout", SGCMFIcons.LOGOUT);
        jbLogout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                acaoLogout();
            }
        });
        
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbLogout), BorderLayout.SOUTH);
        
        return jpPrincipal;
    }
    
    private JPanel montaNorthPanel()
    {
        JPanel gridPanel = new JPanel(new GridLayout(1, 3));
        
        JButton jbGerenciarJogadores = new JButton("Gerenciar Jogadores", SGCMFIcons.JOGADOR);
        UtilView.configuraJButton(jbGerenciarJogadores);
        
        jbGerenciarJogadores.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limGerenciarJogador.setVisible(true);
            }
        });
        
        JButton jbConsultarSelecao = new JButton("Consultar Seleções", SGCMFIcons.SELECAO);
        UtilView.configuraJButton(jbConsultarSelecao);
        
        jbConsultarSelecao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrTecnico.getCtrMain().getCtrSelecao().ativaLimConsultaSelecao();
            }
        });
        
        
        JButton jbConsultarJogos = new JButton("Consultar Jogos", SGCMFIcons.JOGO);
        UtilView.configuraJButton(jbConsultarJogos);
        
        jbConsultarJogos.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrTecnico.getCtrMain().getCtrJogo().ativaLimConsultarJogo();
            }
        });
        
        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbGerenciarJogadores));
        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbConsultarSelecao));
        gridPanel.add(UtilView.putComponentInFlowLayoutPanel(jbConsultarJogos));
        
        return gridPanel;
    }
    
    private JPanel montaCenterPanel()
    {
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton jbRelatorios = new JButton("Relatórios", SGCMFIcons.RELATORIO);
        UtilView.configuraJButton(jbRelatorios);
        
        jbRelatorios.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrRelatorio.ativaTela();
            }
        });
        JButton jbTabelaCampeonato = new JButton("Tabela do Campeonato", SGCMFIcons.TABELA);
        UtilView.configuraJButton(jbTabelaCampeonato);
        
        jbTabelaCampeonato.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrTecnico.getCtrMain().getCtrTabelaCampeonato().ativaTela();
            }
        });
        
        centerPanel.add(jbRelatorios);
        centerPanel.add(jbTabelaCampeonato);
        
        return centerPanel;
    }
    
    private void acaoLogout()
    {
        int op;
        
        op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja fazer logout do sistema?", "Confirmação de Logout", JOptionPane.YES_NO_OPTION);
        
        if (op == JOptionPane.OK_OPTION)
        {
            setVisible(false);
            ctrTecnico.logout();
        }
    }
}
