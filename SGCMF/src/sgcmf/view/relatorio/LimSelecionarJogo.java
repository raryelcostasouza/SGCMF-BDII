/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.relatorio;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import sgcmf.control.CtrJogo;
import sgcmf.view.util.UtilView;
import sgcmf.view.comiteGestor.LimConsultarJogo;

/**
 *
 * @author Helio
 */
public class LimSelecionarJogo extends LimConsultarJogo
{
    private CtrJogo ctrJogo;
    private int linhaSelecionada;
    private PanelRelatorioJogo panelRelatorioJogo;

    public LimSelecionarJogo(CtrJogo ctrJogo)
    {
        super(ctrJogo);
        setTitle("Selecionar Jogo");
        JButton jbSelecionar = new JButton("Selecionar");
        jbSelecionar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                linhaSelecionada = jt.getSelectedRow();
                if (linhaSelecionada != -1)
                {
                    setVisible(false);
                    String strIdJogo;
                    strIdJogo = jt.getValueAt(jt.getSelectedRow(), 0).toString();
                    panelRelatorioJogo.preencherTextFieldJogo(strIdJogo);
                }
            }
        });
        mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbSelecionar), BorderLayout.SOUTH);
    }

    public void ativaTela(PanelRelatorioJogo panelRelatorioJogo)
    {
        this.panelRelatorioJogo = panelRelatorioJogo;
        super.ativaTela();        
    }
}
