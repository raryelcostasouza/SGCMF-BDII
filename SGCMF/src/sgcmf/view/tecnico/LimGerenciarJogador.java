/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Helio
 */
public class LimGerenciarJogador extends JDialog
{
    private PanelCadastrarJogador pCadastrarJogador;
    private PanelAlterarJogador pAlterarJogador;
    private PanelRemoverJogador pRemoverJogador;
    private PanelConsultarJogador pConsultarJogador;
    
    public LimGerenciarJogador()
    {
        pCadastrarJogador = new PanelCadastrarJogador();
        pAlterarJogador = new PanelAlterarJogador();
        pRemoverJogador = new PanelRemoverJogador();
        pConsultarJogador = new PanelConsultarJogador();
        setTitle("Gerenciar Jogadores");
        setModal(true);
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
                else if (tituloAba.equals("Remover"))
                {
                    setSize(700,400);
                    setLocationRelativeTo(null);
                }
                else if (tituloAba.equals("Consultar"))
                {
                    setSize(700,400);
                    setLocationRelativeTo(null);
                }
            }
        });

        jtp.add(pCadastrarJogador, "Cadastrar");
        jtp.add(pAlterarJogador,"Alterar");
        jtp.add(pRemoverJogador, "Remover");
        jtp.add(pConsultarJogador, "Consultar");
        
        return jtp;
    }
}
