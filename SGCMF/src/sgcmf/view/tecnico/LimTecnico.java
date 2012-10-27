/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sgcmf.control.CtrJogo;
import sgcmf.control.CtrTecnico;
import sgcmf.view.UtilView;

/**
 *
 * @author Raryel Costa Souza
 */
public class LimTecnico extends JFrame
{
    private LimGerenciarJogador limGerenciarJogador;
    private LimGerenciarEscalacao limGerenciarEscalacao;
    private CtrTecnico ctrTecnico;

    public LimTecnico(CtrTecnico ctrTecnico, CtrJogo ctrJogo)
    {
        this.ctrTecnico = ctrTecnico;
        limGerenciarJogador = new LimGerenciarJogador();
        limGerenciarEscalacao = new LimGerenciarEscalacao(ctrJogo);

        setTitle("Usuário Técnico da Seleção");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(montaPainel());
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                acaoLogout();
            }
        });
    }

    private JPanel montaPainel()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(2, 2));

        JButton jbGerenciarJogadores = new JButton("Gerenciar Jogadores");
        jbGerenciarJogadores.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limGerenciarJogador.setVisible(true);
            }
        });

        JButton jbRelatorios = new JButton("Relatórios");
        JButton jbGerenciarEscalacao = new JButton("Gerenciar Escalação");
        jbGerenciarEscalacao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limGerenciarEscalacao.setVisible(true);
            }
        });
        JButton jbTabelaCampeonato = new JButton("Tabela do Campeonato");

        JButton jbLogout = new JButton("Logout");

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jbGerenciarJogadores));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jbRelatorios));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jbGerenciarEscalacao));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jbTabelaCampeonato));

        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbLogout), BorderLayout.SOUTH);

        return jpPrincipal;
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
