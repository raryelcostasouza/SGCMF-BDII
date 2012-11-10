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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sgcmf.control.CtrMain;
import sgcmf.control.CtrRelatorio;
import sgcmf.control.CtrSelecao;
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
    private LimSelecionarSelecao limSelecionarSelecao;
    private CtrMain ctrMain;
    private JTextField jtfSelecao;
    private JTextField jtfNomeSelecao;
    private JTextField jtfJogosDisputados;
    private JTextField jtfVitorias;
    private JTextField jtfDerrotas;
    private JTextField jtfEmpates;
    private JTextField jtfAproveitamento;
    private JTextField jtfFaltas;
    private JTextField jtfCartoes;
    private JTextField jtfGolsPro;
    private JTextField jtfGolsContra;
    private JTextField jtfSaldoGols;

    public PanelRelatorioSelecao(CtrRelatorio ctrRelatorio)
    {
        this.ctrRelatorio = ctrRelatorio;
        ctrMain = ctrRelatorio.getCtrMain();
        ctrSelecao = ctrMain.getCtrSelecao();
        limSelecionarSelecao = new LimSelecionarSelecao(ctrSelecao);
        this.setLayout(new BorderLayout());
        add(panelNorte(), BorderLayout.NORTH);
        add(panelCentral(), BorderLayout.CENTER);
    }

    private JPanel panelNorte()
    {
        JPanel jpPrincipal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jlSelecao = new JLabel("Seleção:");
        UtilView.alinhaLabel(jlSelecao);
        jtfSelecao = new JTextField(10);
        jtfSelecao.setEditable(false);
        JButton jbPesquisar = new JButton(SGCMFIcons.PESQUISAR);
        jbPesquisar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ativaTelaSelecionarSelecao();
            }
        });
        UtilView.ajustarTamanhoBotaoPesquisar(jbPesquisar);
        jpPrincipal.add(jlSelecao);
        jpPrincipal.add(jtfSelecao);
        jpPrincipal.add(jbPesquisar);
        jpPrincipal.setBorder(BorderFactory.createTitledBorder("Buscar por:"));
        return jpPrincipal;
    }

    private JPanel panelCentral()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        jpPrincipal.add(panelCentralEsquerda(), BorderLayout.WEST);
        jpPrincipal.add(panelCentralDireita(), BorderLayout.EAST);

        return jpPrincipal;
    }

    private JPanel panelCentralEsquerda()
    {
        JPanel jpPrincipal = new JPanel(new GridLayout(6, 2));

        JLabel jlNomeSelecao = new JLabel("Nome da Seleção:");
        UtilView.alinhaLabel(jlNomeSelecao);
        JLabel jlJogosDisputados = new JLabel("Jogos Disputados:");
        UtilView.alinhaLabel(jlJogosDisputados);
        JLabel jlVitorias = new JLabel("Vitórias:");
        UtilView.alinhaLabel(jlVitorias);
        JLabel jlDerrotas = new JLabel("Derrotas:");
        UtilView.alinhaLabel(jlDerrotas);
        JLabel jlEmpates = new JLabel("Empates:");
        UtilView.alinhaLabel(jlEmpates);
        JLabel jlAproveitamento = new JLabel("Aproveitamento:");
        UtilView.alinhaLabel(jlAproveitamento);

        jtfNomeSelecao = new JTextField(10);
        jtfNomeSelecao.setEditable(false);
        jtfJogosDisputados = new JTextField(10);
        jtfJogosDisputados.setEditable(false);
        jtfVitorias = new JTextField(10);
        jtfVitorias.setEditable(false);
        jtfDerrotas = new JTextField(10);
        jtfDerrotas.setEditable(false);
        jtfEmpates = new JTextField(10);
        jtfEmpates.setEditable(false);
        jtfAproveitamento = new JTextField(10);
        jtfAproveitamento.setEditable(false);

        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlNomeSelecao));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfNomeSelecao, FlowLayout.LEFT));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlJogosDisputados));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfJogosDisputados, FlowLayout.LEFT));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlVitorias));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfVitorias, FlowLayout.LEFT));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlDerrotas));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfDerrotas, FlowLayout.LEFT));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlEmpates));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfEmpates, FlowLayout.LEFT));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlAproveitamento));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfAproveitamento, FlowLayout.LEFT));

        return jpPrincipal;
    }

    private JPanel panelCentralDireita()
    {
        JPanel jpPrincipal = new JPanel(new GridLayout(5, 2));

        JLabel jlFaltas = new JLabel("Faltas:");
        UtilView.alinhaLabel(jlFaltas);
        JLabel jlCartoes = new JLabel("Cartões:");
        UtilView.alinhaLabel(jlCartoes);
        JLabel jlGolsPro = new JLabel("Gols Pró:");
        UtilView.alinhaLabel(jlGolsPro);
        JLabel jlGolsContra = new JLabel("Gols Contra:");
        UtilView.alinhaLabel(jlGolsContra);
        JLabel jlSaldoGols = new JLabel("Saldo Gols:");
        UtilView.alinhaLabel(jlSaldoGols);

        jtfFaltas = new JTextField(10);
        jtfFaltas.setEditable(false);
        jtfCartoes = new JTextField(10);
        jtfCartoes.setEditable(false);
        jtfGolsPro = new JTextField(10);
        jtfGolsPro.setEditable(false);
        jtfGolsContra = new JTextField(10);
        jtfGolsContra.setEditable(false);
        jtfSaldoGols = new JTextField(10);
        jtfSaldoGols.setEditable(false);

        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlFaltas));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfFaltas, FlowLayout.LEFT));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlCartoes));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfCartoes, FlowLayout.LEFT));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlGolsPro));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfGolsPro, FlowLayout.LEFT));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlGolsContra));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfGolsContra, FlowLayout.LEFT));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jlSaldoGols));
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jtfSaldoGols, FlowLayout.LEFT));

        return jpPrincipal;
    }

    @Override
    public void selecaoSelecionada(Short idSelecao)
    {
        jtfSelecao.setText(idSelecao + "");
        preencheTextFields(idSelecao);
    }

    private void ativaTelaSelecionarSelecao()
    {
        limSelecionarSelecao.ativaTela(this);
    }

    private void preencheTextFields(Short idSelecao)
    {
        String nomeSelecao;
        //Recebendo os campos
        nomeSelecao = ctrSelecao.pesquisarNomeSelecao(idSelecao);

        //Atulizando os TextFields
        jtfNomeSelecao.setText(nomeSelecao);

    }

    public void limparTela()
    {
        jtfSelecao.setText("");
        jtfNomeSelecao.setText("");
        jtfJogosDisputados.setText("");
        jtfVitorias.setText("");
        jtfDerrotas.setText("");
        jtfEmpates.setText("");
        jtfAproveitamento.setText("");
        jtfFaltas.setText("");
        jtfCartoes.setText("");
        jtfGolsPro.setText("");
        jtfGolsContra.setText("");
        jtfSaldoGols.setText("");
    }
}
