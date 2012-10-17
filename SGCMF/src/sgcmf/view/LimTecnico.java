/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Raryel Costa Souza
 */
public class LimTecnico extends JFrame
{
    public LimTecnico()
    {
        setTitle("Usuário Técnico da Seleção");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        add(montaPainel());
    }
    
    public JPanel montaPainel()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(2, 2));
        JButton jbGerenciarJogadores = new JButton("Gerenciar Jogadores");
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
        new LimTecnico();
    }
}
