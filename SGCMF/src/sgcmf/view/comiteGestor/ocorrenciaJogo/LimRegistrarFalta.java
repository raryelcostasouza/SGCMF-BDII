package sgcmf.view.comiteGestor.ocorrenciaJogo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import sgcmf.control.CtrOcorrenciaJogo;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.model.other.TipoResultadoOperacao;
import sgcmf.view.comiteGestor.LimBuscarJogador;
import sgcmf.view.UtilView;
import sgcmf.view.tecnico.ISelecionarJogador;

public class LimRegistrarFalta extends JDialog implements ISelecionarJogador
{
    private LimGerenciarOcorrenciasJogo limGerenciarOcorrencias;
    private LimBuscarJogador limBuscarJogador;
    private CtrOcorrenciaJogo ctrOcorrenciaJogo;
    private JTextField jtfInstanteTempoMin;
    private JTextField jtfInstanteTempoSeg;
    private JTextField jtfJogador;
    private JRadioButton jrbCartaoNenhum;
    private JRadioButton jrbCartaoAmarelo;
    private JRadioButton jrbCartaoVermelho;
    private JRadioButton jrbTipoComum;
    private JRadioButton jrbTipoPenalti;
    private Short idJogo;

    public LimRegistrarFalta(CtrOcorrenciaJogo ctrOcorrenciaJogo, LimBuscarJogador limBuscarJogador, LimGerenciarOcorrenciasJogo limGerenciarOcorrencias)
    {
        this.ctrOcorrenciaJogo = ctrOcorrenciaJogo;
        this.limBuscarJogador = limBuscarJogador;
        this.limGerenciarOcorrencias = limGerenciarOcorrencias;

        setTitle("Registrar Falta");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        add(montaMainPanel());
        setModal(true);
        setSize(370, 230);
        setLocationRelativeTo(null);
    }

    private JPanel montaMainPanel()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JPanel jpAuxJogador = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelJRBCartao = new JPanel(new GridLayout(3, 1));
        JPanel panelJRBTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpAuxTempo = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel jlInstanteTempo = new JLabel("Instante de Tempo:");
        UtilView.alinhaLabel(jlInstanteTempo);
        JLabel jlJogador = new JLabel("Jogador:");
        UtilView.alinhaLabel(jlJogador);
        JLabel jlCartao = new JLabel("Cartão:");
        UtilView.alinhaLabel(jlCartao);
        JLabel jlTipo = new JLabel("Tipo:");
        UtilView.alinhaLabel(jlTipo);

        jtfInstanteTempoMin = new JTextField(4);
        JLabel jlMin = new JLabel("min");

        jtfInstanteTempoSeg = new JTextField(4);
        JLabel jlSeg = new JLabel("seg");

        jtfJogador = new JTextField(10);
        jtfJogador.setEditable(false);

        jrbCartaoNenhum = new JRadioButton("Nenhum");
        jrbCartaoNenhum.setSelected(true);
        jrbCartaoAmarelo = new JRadioButton("Amarelo");
        jrbCartaoVermelho = new JRadioButton("Vermelho");

        jrbTipoComum = new JRadioButton("Falta comum");
        jrbTipoComum.setSelected(true);
        jrbTipoPenalti = new JRadioButton("Pênalti");

        JButton jbRegistrarFalta = new JButton("Registrar Falta");
        jbRegistrarFalta.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //registrarFalta();
            }
        });


        JButton jbPesqJogador = new JButton(SGCMFIcons.PESQUISAR);
        UtilView.ajustarTamanhoBotaoPesquisar(jbPesqJogador);

        jbPesqJogador.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ativaTelaBuscarJogador();
            }
        });

        ButtonGroup bgCartao = new ButtonGroup();
        bgCartao.add(jrbCartaoNenhum);
        bgCartao.add(jrbCartaoAmarelo);
        bgCartao.add(jrbCartaoVermelho);

        ButtonGroup bgTipo = new ButtonGroup();
        bgTipo.add(jrbTipoComum);
        bgTipo.add(jrbTipoPenalti);

        formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlInstanteTempo));
        jpAuxTempo.add(jtfInstanteTempoMin);
        jpAuxTempo.add(jlMin);
        jpAuxTempo.add(jtfInstanteTempoSeg);
        jpAuxTempo.add(jlSeg);
        formPanel.add(jpAuxTempo);

        formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogador));
        jpAuxJogador.add(jtfJogador);
        jpAuxJogador.add(jbPesqJogador);
        formPanel.add(jpAuxJogador);

        formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlTipo));
        panelJRBTipo.add(jrbTipoComum);
        panelJRBTipo.add(jrbTipoPenalti);
        formPanel.add(panelJRBTipo);

        formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlCartao));
        panelJRBCartao.add(jrbCartaoNenhum);
        panelJRBCartao.add(jrbCartaoAmarelo);
        panelJRBCartao.add(jrbCartaoVermelho);
        formPanel.add(panelJRBCartao);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarFalta), BorderLayout.SOUTH);
        return mainPanel;
    }

    public void ativaTela(Short idJogo)
    {
        this.idJogo = idJogo;
        setVisible(true);
    }

    private void ativaTelaBuscarJogador()
    {
        limBuscarJogador.ativaTela(this);
    }

    private void registrarFalta()
    {
        String tipo;
        String cartao;
        ResultadoOperacao result;

        if (jrbTipoComum.isSelected())
        {
            tipo = jrbTipoComum.getText();
        }
        else
        {
            tipo = jrbTipoPenalti.getText();
        }

        if (jrbCartaoNenhum.isSelected())
        {
            cartao = jrbTipoComum.getText();
        }
        else if (jrbCartaoAmarelo.isSelected())
        {
            cartao = jrbCartaoAmarelo.getText();
        }
        else
        {
            cartao = jrbCartaoVermelho.getText();
        }

        result = ctrOcorrenciaJogo.registrarFalta(jtfInstanteTempoMin.getText(),
                                                  jtfInstanteTempoSeg.getText(),
                                                  idJogo,
                                                  jtfJogador.getText(), tipo, cartao);

        if (result.getTipo() == TipoResultadoOperacao.EXITO)
        {
            JOptionPane.showMessageDialog(this, result.getMsg(), "Exito!", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            limGerenciarOcorrencias.preencheTabelaFalta();
        }
        else
        {
            JOptionPane.showMessageDialog(this, result.getMsg(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void jogadorSelecionado(Short idJogador)
    {
        jtfJogador.setText(idJogador + "");
    }
}
