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
import sgcmf.view.UtilView;

/**
 *
 * @author Helio
 */
public class PanelRelatorioJogo extends JPanel
{
    private LimSelecionarJogo limSelecionarJogo;
    private JTextField jtfJogo;
    private JTextField jtfGolsI = new JTextField(10);
    private JTextField jtfAmarelosI = new JTextField(10);
    private JTextField jtfVermelhosI = new JTextField(10);
    private JTextField jtfFaltasI = new JTextField(10);
    private JTextField jtfPenaltiI = new JTextField(10);
    private JTextField jtfSubstituicoesI = new JTextField(10);
    private CtrJogo ctrJogo;

    public PanelRelatorioJogo(CtrRelatorio ctrRelatorio)
    {
        this.setLayout(new BorderLayout());
        add(panelNorte(), BorderLayout.NORTH);
        add(panelCentral(), BorderLayout.CENTER);
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
            }
        });
        jpPrincipal.add(jlJogo);
        jpPrincipal.add(jtfJogo);
        jpPrincipal.add(jbPesquisar);
        jpPrincipal.setBorder(BorderFactory.createTitledBorder("Buscar por:"));

        return jpPrincipal;
    }

    public JPanel panelCentral()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JSeparator js = new JSeparator(JSeparator.VERTICAL);
        jpPrincipal.add(panelCentralEsquerda(), BorderLayout.WEST);
        jpPrincipal.add(js, BorderLayout.CENTER);
        jpPrincipal.add(panelCentralDireita(), BorderLayout.EAST);

        return jpPrincipal;
    }

    private JPanel panelCentralEsquerda()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(6, 2));

        JLabel jlSelecaoI = new JLabel("Seleção I");
        UtilView.alinhaLabel(jlSelecaoI);
        JLabel jlGolsI = new JLabel("Gols:");
        UtilView.alinhaLabel(jlGolsI);
        JLabel jlAmarelosI = new JLabel("Cartões Amarelos:");
        UtilView.alinhaLabel(jlAmarelosI);
        JLabel jlVermelhosI = new JLabel("Cartões Vermelhos:");
        UtilView.alinhaLabel(jlVermelhosI);
        JLabel jlFaltasI = new JLabel("Faltas:");
        UtilView.alinhaLabel(jlFaltasI);
        JLabel jlPenaltiI = new JLabel("Pênaltis:");
        UtilView.alinhaLabel(jlPenaltiI);
        JLabel jlSubstituicoesI = new JLabel("Substituições:");
        UtilView.alinhaLabel(jlSubstituicoesI);

        jtfGolsI = new JTextField(10);
        jtfGolsI.setEditable(false);
        jtfAmarelosI = new JTextField(10);
        jtfAmarelosI.setEditable(false);
        jtfVermelhosI = new JTextField(10);
        jtfVermelhosI.setEditable(false);
        jtfFaltasI = new JTextField(10);
        jtfFaltasI.setEditable(false);
        jtfPenaltiI = new JTextField(10);
        jtfPenaltiI.setEditable(false);
        jtfSubstituicoesI = new JTextField(10);
        jtfSubstituicoesI.setEditable(false);

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlGolsI));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfGolsI, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlAmarelosI));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfAmarelosI, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlVermelhosI));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfVermelhosI, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlFaltasI));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfFaltasI, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlPenaltiI));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfPenaltiI, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlSubstituicoesI));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfSubstituicoesI, FlowLayout.LEFT));

        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlSelecaoI), BorderLayout.NORTH);

        return jpPrincipal;
    }

    private JPanel panelCentralDireita()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(6, 2));

        JLabel jlSelecaoII = new JLabel("Seleção II");
        UtilView.alinhaLabel(jlSelecaoII);
        JLabel jlGolsII = new JLabel("Gols:");
        UtilView.alinhaLabel(jlGolsII);
        JLabel jlAmarelosII = new JLabel("Cartões Amarelos:");
        UtilView.alinhaLabel(jlAmarelosII);
        JLabel jlVermelhosII = new JLabel("Cartões Vermelhos:");
        UtilView.alinhaLabel(jlVermelhosII);
        JLabel jlFaltasII = new JLabel("Faltas:");
        UtilView.alinhaLabel(jlFaltasII);
        JLabel jlPenaltiII = new JLabel("Pênaltis:");
        UtilView.alinhaLabel(jlPenaltiII);
        JLabel jlSubstituicoesII = new JLabel("Substituições:");
        UtilView.alinhaLabel(jlSubstituicoesII);

        JTextField jtfGolsII = new JTextField(10);
        jtfGolsII.setEditable(false);
        JTextField jtfAmarelosII = new JTextField(10);
        jtfAmarelosII.setEditable(false);
        JTextField jtfVermelhosII = new JTextField(10);
        jtfVermelhosII.setEditable(false);
        JTextField jtfFaltasII = new JTextField(10);
        jtfFaltasII.setEditable(false);
        JTextField jtfPenaltiII = new JTextField(10);
        jtfPenaltiII.setEditable(false);
        JTextField jtfSubstituicoesII = new JTextField(10);
        jtfSubstituicoesII.setEditable(false);

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlGolsII));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfGolsII, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlAmarelosII));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfAmarelosII, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlVermelhosII));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfVermelhosII, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlFaltasII));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfFaltasII, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlPenaltiII));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfPenaltiII, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlSubstituicoesII));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfSubstituicoesII, FlowLayout.LEFT));

        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlSelecaoII), BorderLayout.NORTH);

        return jpPrincipal;
    }

    private void ativaTelaSelecionarJogo()
    {
        limSelecionarJogo.ativaTela(this);
    }

    public void preencherTextFieldJogo(String strIdJogo)
    {
        Short idJogo;
        jtfJogo.setText(strIdJogo);
        idJogo = Short.parseShort(strIdJogo);
        preencheDadosTextFields(idJogo);
    }

    public void preencheDadosTextFields(Short idJogo)
    {
        ModelRelatorioJogo mrj = ctrJogo.geraRelatorioJogo(idJogo);
        jtfGolsI.setText(mrj.getGols() + "");
        jtfAmarelosI.setText(mrj.getCartoesAmarelos() + "");
        jtfVermelhosI.setText(mrj.getCartoesVermelhos() + "");
        jtfFaltasI.setText(mrj.getFaltas() + "");

    }
}
