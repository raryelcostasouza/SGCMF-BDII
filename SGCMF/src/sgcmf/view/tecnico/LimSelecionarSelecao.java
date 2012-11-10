/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int linhaSelecionada;
    private ISelecionarSelecao iss;

    public LimSelecionarSelecao(CtrSelecao ctrSelecao)
    {
        super(ctrSelecao);
        setTitle("Selecionar Seleção");
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
                    String strIdSelecao;
                    Short idSelecao;
                    strIdSelecao = jt.getValueAt(jt.getSelectedRow(), 0).toString();
                    idSelecao = Short.parseShort(strIdSelecao);
                    iss.selecaoSelecionada(idSelecao);
                }

            }
        });
        mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbSelecionar), BorderLayout.SOUTH);
    }

    public void ativaTela(ISelecionarSelecao iss)
    {
        this.iss = iss;
        super.ativaTela();
    }
}
