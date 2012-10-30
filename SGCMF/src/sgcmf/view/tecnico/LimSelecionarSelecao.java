/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import javax.swing.JButton;
import sgcmf.control.CtrSelecao;
import sgcmf.view.UtilView;
import sgcmf.view.comiteGestor.LimConsultaSelecao;

/**
 *
 * @author Helio
 */
public class LimSelecionarSelecao extends LimConsultaSelecao
{
    public LimSelecionarSelecao(CtrSelecao ctrSelecao)
    {
        super(ctrSelecao);
        setTitle("Selecionar Seleção");
        JButton jbSelecionar = new JButton("Selecionar");
        mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbSelecionar),BorderLayout.SOUTH);
    }    
}
