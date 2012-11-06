/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
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
import sgcmf.view.UtilView;

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

        setTitle("Usuário Técnico da Seleção");
        add(montaPainel());
        pack();
        setLocationRelativeTo(null);
        setVisible(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


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
        JPanel jpAux = new JPanel(new GridLayout(1, 3));

        JButton jbGerenciarJogadores = new JButton("Gerenciar Jogadores", SGCMFIcons.JOGADOR);
        jbGerenciarJogadores.setVerticalTextPosition(JButton.BOTTOM);
        jbGerenciarJogadores.setHorizontalTextPosition(JButton.CENTER);

        jbGerenciarJogadores.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limGerenciarJogador.setVisible(true);
            }
        });

        JButton jbRelatorios = new JButton("Relatórios", SGCMFIcons.RELATORIO);
        jbRelatorios.setVerticalTextPosition(JButton.BOTTOM);
        jbRelatorios.setHorizontalTextPosition(JButton.CENTER);
        jbRelatorios.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrRelatorio.ativaTela();
            }
        });
        JButton jbTabelaCampeonato = new JButton("Tabela do Campeonato", SGCMFIcons.TABELA);
        jbTabelaCampeonato.setVerticalTextPosition(JButton.BOTTOM);
        jbTabelaCampeonato.setHorizontalTextPosition(JButton.CENTER);

        JButton jbLogout = new JButton("Logout", SGCMFIcons.LOGOUT);
        jbLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                acaoLogout();
            }
        });

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jbGerenciarJogadores));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jbRelatorios));
        
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
