package sgcmf.view.comiteGestor.ocorrenciaJogo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import sgcmf.control.CtrFalta;
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
    private CtrFalta ctrFalta;
    private JTextField jtfInstanteTempoMin;
    private JTextField jtfInstanteTempoSeg;
    private JTextField jtfJogador;
    private JRadioButton jrbCartaoNenhum;
    private JRadioButton jrbCartaoAmarelo;
    private JRadioButton jrbCartaoVermelho;
    private JRadioButton jrbTipoComum;
    private JRadioButton jrbTipoPenalti;
    private ButtonGroup bgCartao;
    private ButtonGroup bgTipo;

    public LimRegistrarFalta(CtrFalta ctrFalta, LimBuscarJogador limBuscarJogador, LimGerenciarOcorrenciasJogo limGerenciarOcorrencias)
    {
        this.ctrFalta = ctrFalta;
        this.limBuscarJogador = limBuscarJogador;
        this.limGerenciarOcorrencias = limGerenciarOcorrencias;

        setTitle("Registrar Falta");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        add(montaMainPanel());
        setModal(true);
        setSize(370, 230);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                resetCamposInterface();
            }
        });
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
        jrbCartaoNenhum.setActionCommand("Nenhum");
        jrbCartaoNenhum.setSelected(true);
        
        jrbCartaoAmarelo = new JRadioButton("Amarelo");
        jrbCartaoAmarelo.setActionCommand("Amarelo");
        
        jrbCartaoVermelho = new JRadioButton("Vermelho");
        jrbCartaoVermelho.setActionCommand("Vermelho");
        

        jrbTipoComum = new JRadioButton("Falta Comum");
        jrbTipoComum.setActionCommand("Falta Comum");     
        jrbTipoComum.setSelected(true);
        
        jrbTipoPenalti = new JRadioButton("Pênalti");
        jrbTipoPenalti.setActionCommand("Penalti");

        JButton jbRegistrarFalta = new JButton("Registrar Falta");
        jbRegistrarFalta.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                registrarFalta();
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

        bgCartao = new ButtonGroup();
        bgCartao.add(jrbCartaoNenhum);
        bgCartao.add(jrbCartaoAmarelo);
        bgCartao.add(jrbCartaoVermelho);

        bgTipo = new ButtonGroup();
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

    private void ativaTelaBuscarJogador()
    {
        limBuscarJogador.ativaTelaSelecionaJogador(this, limGerenciarOcorrencias.getIdJogo());
    }

    private void registrarFalta()
    {
        String tipo;
        String cartao;
        ResultadoOperacao result;

        tipo = bgTipo.getSelection().getActionCommand();
        cartao = bgCartao.getSelection().getActionCommand();

        result = ctrFalta.registrarFalta(jtfInstanteTempoMin.getText(),
                                                  jtfInstanteTempoSeg.getText(),
                                                  limGerenciarOcorrencias.getIdJogo(),
                                                  jtfJogador.getText(), tipo, cartao);

        if (result.getTipo() == TipoResultadoOperacao.EXITO)
        {
            JOptionPane.showMessageDialog(this, result.getMsg(), "Êxito!", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            resetCamposInterface();
            limGerenciarOcorrencias.preencheTabelaFalta();
        }
        else
        {
            JOptionPane.showMessageDialog(this, result.getMsg(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetCamposInterface()
    {
        jtfInstanteTempoMin.setText("");
        jtfInstanteTempoSeg.setText("");
        jtfJogador.setText("");
        jrbTipoComum.setSelected(true);
        jrbCartaoNenhum.setSelected(true);
    }

    @Override
    public void jogadorSelecionado(Short idJogador)
    {
        jtfJogador.setText(idJogador + "");
    }
}
