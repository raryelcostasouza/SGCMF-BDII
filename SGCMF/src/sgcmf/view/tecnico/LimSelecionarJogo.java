/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import javax.swing.JButton;
import sgcmf.control.CtrJogo;
import sgcmf.view.UtilView;
import sgcmf.view.comiteGestor.LimConsultarJogo;

/**
 *
 * @author Helio
 */
public class LimSelecionarJogo extends LimConsultarJogo
{
    public LimSelecionarJogo(CtrJogo ctrJogo)
    {
        super(ctrJogo);
        setTitle("Selecionar Jogo");
        JButton jbSelecionar = new JButton("Selecionar");
        mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbSelecionar), BorderLayout.SOUTH);
    }
}
