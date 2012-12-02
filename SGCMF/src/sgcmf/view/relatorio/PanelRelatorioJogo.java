/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.relatorio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import sgcmf.control.CtrJogo;
import sgcmf.control.CtrRelatorio;
import sgcmf.model.hibernate.Jogo;
import sgcmf.model.other.ModelRelatorioJogo;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.util.UtilView;

/**
 *
 * @author Helio
 */
public class PanelRelatorioJogo extends JPanel
{
    private LimSelecionarJogo limSelecionarJogo;
    private JTextField jtfJogo;
    private CtrJogo ctrJogo;
    private JButton jbRelatorio;

    public PanelRelatorioJogo(CtrRelatorio ctrRelatorio)
    {
        this.setLayout(new BorderLayout());
        jbRelatorio = new JButton("Relatório");
        jbRelatorio.setEnabled(false);
        jbRelatorio.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrJogo.geraRelatorioJogo(Short.parseShort(jtfJogo.getText()));
            }
        });
        add(panelNorte(), BorderLayout.NORTH);
        add(UtilView.putComponentInFlowLayoutPanel(jbRelatorio));
        ctrJogo = ctrRelatorio.getCtrMain().getCtrJogo();
        limSelecionarJogo = new LimSelecionarJogo(ctrJogo);

    }

    private JPanel panelNorte()
    {
        JPanel jpPrincipal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jlJogo = new JLabel("Jogo:");
        UtilView.alinhaLabel(jlJogo);
        jtfJogo = new JTextField(10);
        jtfJogo.setEditable(false);
        JButton jbPesquisar = new JButton(SGCMFIcons.PESQUISAR);
        UtilView.ajustarTamanhoBotaoPesquisar(jbPesquisar);
        jbPesquisar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ativaTelaSelecionarJogo();
                verificaTextFieldEmBranco();
            }
        });
        jpPrincipal.add(jlJogo);
        jpPrincipal.add(jtfJogo);
        jpPrincipal.add(jbPesquisar);
        jpPrincipal.setBorder(BorderFactory.createTitledBorder("Buscar por:"));

        return jpPrincipal;
    }

    private void ativaTelaSelecionarJogo()
    {
        limSelecionarJogo.ativaTela(this);
    }

    private void verificaTextFieldEmBranco()
    {
        if (jtfJogo.getText().equals(""))
        {
            jbRelatorio.setEnabled(false);
        }
        else
        {
            jbRelatorio.setEnabled(true);
        }
    }

    public void preencherTextFieldJogo(String strIdJogo)
    {
        Short idJogo;
        jtfJogo.setText(strIdJogo);
        idJogo = Short.parseShort(strIdJogo);
    }
    
    public void limparTela()
    {
        jtfJogo.setText("");
        jbRelatorio.setEnabled(false);
    }
}
