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
import javax.swing.JTextField;
import sgcmf.control.CtrCartao;
import sgcmf.control.CtrFalta;
import sgcmf.control.CtrGol;
import sgcmf.control.CtrJogo;
import sgcmf.control.CtrMain;
import sgcmf.control.CtrRelatorio;
import sgcmf.control.CtrSelecao;
import sgcmf.model.other.AproveitamentoSelecao;
import sgcmf.model.other.ResultadoGolsSelecao;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.UtilView;
import sgcmf.view.tecnico.ISelecionarSelecao;
import sgcmf.view.tecnico.LimSelecionarSelecao;

/**
 *
 * @author Helio
 */
public class PanelRelatorioSelecao extends JPanel implements ISelecionarSelecao
{
    private CtrRelatorio ctrRelatorio;
    private CtrSelecao ctrSelecao;
    private CtrJogo ctrJogo;
    private CtrGol ctrGol;
    private CtrCartao ctrCartao;
    private CtrFalta ctrFalta;
    private JButton jbRelatorio;
    private LimSelecionarSelecao limSelecionarSelecao;
    private CtrMain ctrMain;
    private JTextField jtfSelecaoI;
    private JTextField jtfSelecaoII;
    private boolean clicadoI = false;
    
    public PanelRelatorioSelecao(CtrRelatorio ctrRelatorio)
    {
        this.ctrRelatorio = ctrRelatorio;
        ctrMain = ctrRelatorio.getCtrMain();
        ctrSelecao = ctrMain.getCtrSelecao();
        ctrJogo = ctrMain.getCtrJogo();
        ctrGol = ctrMain.getCtrGol();
        ctrFalta = ctrMain.getCtrFalta();
        ctrCartao = ctrMain.getCtrCartao();
        limSelecionarSelecao = new LimSelecionarSelecao(ctrSelecao);
        this.setLayout(new BorderLayout());
        add(panelNorte(), BorderLayout.NORTH);
        add(panelCentral(), BorderLayout.SOUTH);
    }
    
    private JPanel panelNorte()
    {
        JPanel jpPrincipal = new JPanel(new GridLayout(2, 1));
        JPanel jpAuxI = new JPanel(new GridLayout(1, 3));
        JPanel jpAuxII = new JPanel(new GridLayout(1, 3));
        JLabel jlSelecaoI = new JLabel("Seleção I:");
        UtilView.alinhaLabel(jlSelecaoI);
        JLabel jlSelecaoII = new JLabel("Seleção II:");
        UtilView.alinhaLabel(jlSelecaoII);
        
        jtfSelecaoI = new JTextField(10);
        jtfSelecaoI.setEditable(false);
        jtfSelecaoII = new JTextField(10);
        jtfSelecaoII.setEditable(false);
        
        JButton jbPesquisarI = new JButton(SGCMFIcons.PESQUISAR);
        jbPesquisarI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clicadoI = true;
                ativaTelaSelecionarSelecao();
                verificaTextFieldsPreenchidos();
            }
        });
        
        JButton jbPesquisarII = new JButton(SGCMFIcons.PESQUISAR);
        jbPesquisarII.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ativaTelaSelecionarSelecao();
                verificaTextFieldsPreenchidos();
            }
        });
        UtilView.ajustarTamanhoBotaoPesquisar(jbPesquisarI);
        UtilView.ajustarTamanhoBotaoPesquisar(jbPesquisarII);
        jpAuxI.add(UtilView.putComponentInFlowLayoutPanel(jlSelecaoI));
        jpAuxI.add(UtilView.putComponentInFlowLayoutPanel(jtfSelecaoI));
        jpAuxI.add(UtilView.putComponentInFlowLayoutPanel(jbPesquisarI));
        jpAuxII.add(UtilView.putComponentInFlowLayoutPanel(jlSelecaoII));
        jpAuxII.add(UtilView.putComponentInFlowLayoutPanel(jtfSelecaoII));
        jpAuxII.add(UtilView.putComponentInFlowLayoutPanel(jbPesquisarII));
        jpPrincipal.add(jpAuxI);
        jpPrincipal.add(jpAuxII);
        
        jpPrincipal.setBorder(BorderFactory.createTitledBorder("Buscar por:"));
        return jpPrincipal;
    }
    
    private JPanel panelCentral()
    {
        JPanel jpPrincipal = new JPanel();
        jbRelatorio = new JButton("Gerar Relatório");
        jbRelatorio.setEnabled(false);
        jbRelatorio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctrRelatorio.gerarRelatorio(jtfSelecaoI.getText(), jtfSelecaoII.getText());
            }
        });
        
        jpPrincipal.add(jbRelatorio);
        return jpPrincipal;
    }
    
    @Override
    public void selecaoSelecionada(Short idSelecao)
    {
        if (clicadoI)
        {
            jtfSelecaoI.setText(idSelecao + "");
            clicadoI = false;
        }
        else
        {
            jtfSelecaoII.setText(idSelecao + "");
        }
    }
    
    private void ativaTelaSelecionarSelecao()
    {
        limSelecionarSelecao.ativaTela(this);
    }
    
    public void limparTela()
    {
        jbRelatorio.setEnabled(false);
        jtfSelecaoI.setText("");
        jtfSelecaoII.setText("");
    }
    
    private void verificaTextFieldsPreenchidos()
    {
        if ((jtfSelecaoI.getText().equals("")) || (jtfSelecaoII.getText().equals("")))
        {
            jbRelatorio.setEnabled(false);
        }
        else
        {
            jbRelatorio.setEnabled(true);
        }
    }
}
