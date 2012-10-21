/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Raryel Costa Souza
 */
public class LimTecnico extends JFrame
{
    private LimGerenciarJogador limGerenciarJogador;
    public LimTecnico()
    {
        setTitle("Usuário Técnico da Seleção");
        setSize(400, 400);
        setLocationRelativeTo(null);
        //setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
           
        add(montaPainel());
        
        limGerenciarJogador = new LimGerenciarJogador();
    }
    
    private JPanel montaPainel()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(2, 2));
        
        JButton jbGerenciarJogadores = new JButton("Gerenciar Jogadores");
        jbGerenciarJogadores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                limGerenciarJogador.setVisible(true);
            }
        });       
        
        JButton jbRelatorios = new JButton("Relatórios");
        JButton jbGerenciarEscalacao = new JButton("Gerenciar Escalação");
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
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run()
            {
                new LimTecnico();
            }
        });   
    }
}
