/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    public LimSelecionarSelecao(CtrSelecao ctrSelecao, final PanelCadastrarJogador pcj)
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
                    pcj.selecaoSelecionada(idSelecao);
                }

            }
        });
        mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbSelecionar), BorderLayout.SOUTH);
    }
    
    public LimSelecionarSelecao(CtrSelecao ctrSelecao, final PanelAlterarJogador paj)
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
                    paj.selecaoSelecionada(idSelecao);
                }

            }
        });
        mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbSelecionar), BorderLayout.SOUTH);
    }
}
