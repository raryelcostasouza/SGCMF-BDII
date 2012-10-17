/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Helio
 */
public class LimGerenciarJogador extends JFrame
{
    private PanelCadastrarJogador pcj;
    private PanelAlterarJogador paj;
    
    public LimGerenciarJogador()
    {
        pcj = new PanelCadastrarJogador();
        paj = new PanelAlterarJogador();
        setTitle("Gerenciar Jogadores");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(montaPainel());
    }

    private JTabbedPane montaPainel()
    {
        final JTabbedPane jtp = new JTabbedPane();
        jtp.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                String tituloAba = jtp.getTitleAt(jtp.getSelectedIndex());
                if (tituloAba.equals("Cadastrar"))
                {
                    setSize(380, 335);
                    setLocationRelativeTo(null);
                }
                else if (tituloAba.equals("Alterar"))
                {
                    setSize(700,400);
                    setLocationRelativeTo(null);
                }
            }
        });

        jtp.add(pcj, "Cadastrar");
        jtp.add(paj,"Alterar");
        jtp.add(null, "Remover");
        jtp.add(null, "Consultar");
        
        return jtp;
    }
}
