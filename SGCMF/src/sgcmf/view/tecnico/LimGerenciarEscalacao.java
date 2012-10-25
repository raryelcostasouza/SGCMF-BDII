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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sgcmf.view.UtilView;
import sgcmf.view.comiteGestor.LimConsultarJogo;

/**
 *
 * @author Helio
 */
public class LimGerenciarEscalacao extends JFrame
{
    private LimConsultarJogo limConsultarJogo;
    public LimGerenciarEscalacao()
    {
        limConsultarJogo = new LimConsultarJogo();
        setVisible(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);        
        setTitle("Gerenciar Escalação");
        add(montaPainel());
    }
    private JPanel montaPainel()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpNorte = new JPanel();
        JPanel jpCentro = new JPanel(new GridLayout(6, 2));
        JLabel jlJogo = new JLabel("Jogo:");
        JTextField jtfJogo = new JTextField(15);
        JButton jbPesquisar = new JButton("P");
        jbPesquisar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                limConsultarJogo.setVisible(true);
            }
        });
        JButton jbEnviarEscalacao = new JButton("Enviar Escalação");
        
        JCheckBox jcbJogador1 = new JCheckBox("Jogador 1/Goleiro");
        JCheckBox jcbJogador2 = new JCheckBox("Jogador 2/Zagueiro");
        JCheckBox jcbJogador3 = new JCheckBox("Jogador 3/Zagueiro");
        JCheckBox jcbJogador4 = new JCheckBox("Jogador 4/Meio-Campo");
        JCheckBox jcbJogador5 = new JCheckBox("Jogador 5/Atacante");
        JCheckBox jcbJogador6 = new JCheckBox("Jogador 6/Atacante");
        JCheckBox jcbJogador7 = new JCheckBox("Jogador 7/Atacante");
        JCheckBox jcbJogador8 = new JCheckBox("Jogador 8/Zagueiro");
        JCheckBox jcbJogador9 = new JCheckBox("Jogador 9/Atacante");
        JCheckBox jcbJogador10 = new JCheckBox("Jogador 10/Atacante");
        JCheckBox jcbJogador11 = new JCheckBox("Jogador 11/Atacante");
        JCheckBox jcbJogador12 = new JCheckBox("Jogador 12/Atacante");
        
        jpNorte.add(jlJogo);
        jpNorte.add(jtfJogo);
        jpNorte.add(jbPesquisar);
        jpNorte.setBorder(BorderFactory.createTitledBorder("Buscar:"));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador1, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador2, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador3, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador4, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador5, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador6, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador7, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador8, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador9, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador10, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador11, FlowLayout.LEFT));
        jpCentro.add(UtilView.putComponentInFlowLayoutPanel(jcbJogador12, FlowLayout.LEFT));
        jpCentro.setBorder(BorderFactory.createTitledBorder("Selecione os Titulares:"));
        
        jpPrincipal.add(jpNorte, BorderLayout.NORTH);
        jpPrincipal.add(jpCentro, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbEnviarEscalacao), BorderLayout.SOUTH);
       
        return jpPrincipal;
    }
}
