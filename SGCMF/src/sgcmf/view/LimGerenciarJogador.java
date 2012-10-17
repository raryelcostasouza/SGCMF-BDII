/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view;

import sgcmf.view.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Helio
 */
public class LimGerenciarJogador extends JFrame
{
    public LimGerenciarJogador()
    {
        setTitle("Gerenciar Jogadores");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(montaPainel());
    }
    
    public JPanel montaPainel()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(6, 1));
        JPanel jpAux2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpAux3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                
        JLabel jlNumeroCamisa = new JLabel("Número da Camisa:");
        UtilView.alinhaLabel(jlNumeroCamisa);
        JLabel jlNome = new JLabel("Nome:");
        UtilView.alinhaLabel(jlNome);
        JLabel jlDataNascimento = new JLabel("Data Nascimento:");
        UtilView.alinhaLabel(jlDataNascimento);
        JLabel jlAltura = new JLabel("Altura:");
        UtilView.alinhaLabel(jlAltura);
        JLabel jlPosicao = new JLabel("Posição:");
        UtilView.alinhaLabel(jlPosicao);
        JLabel jlSelecao = new JLabel("Seleção:");
        UtilView.alinhaLabel(jlSelecao);
        
        JTextField jtfNumeroCamisa = new JTextField(10);
        JTextField jtfNome = new JTextField(10);
        JTextField jtfDataNascimento = new JTextField(10);
        JTextField jtfAltura = new JTextField(10);
        JTextField jtfPosicao = new JTextField(10);
        JTextField jtfSelecao = new JTextField(10);
        
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbPesquisar = new JButton("P");
        
        jpAux3.add(jlNumeroCamisa);
        jpAux.add(jpAux3);
        
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfNumeroCamisa));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlNome));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfNome));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlDataNascimento));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfDataNascimento));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlAltura));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfAltura));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlPosicao));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfPosicao));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlSelecao));
        jpAux2.add(UtilView.putComponentInFlowLayoutPanel(jtfSelecao));
        jpAux2.add(UtilView.putComponentInFlowLayoutPanel(jbPesquisar));
        jpAux.add(jpAux2);
        
        
        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbCadastrar), BorderLayout.SOUTH);
        
        return jpPrincipal;
    }
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run()
            {
                new LimGerenciarJogador();
            }
        });
    }
}
