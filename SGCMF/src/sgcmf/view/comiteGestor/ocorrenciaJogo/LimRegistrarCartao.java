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
import sgcmf.control.CtrCartao;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.model.other.TipoResultadoOperacao;
import sgcmf.view.UtilView;
import sgcmf.view.comiteGestor.LimBuscarJogador;
import sgcmf.view.tecnico.ISelecionarJogador;

public class LimRegistrarCartao extends JDialog implements ISelecionarJogador
{
    private LimBuscarJogador limBuscarJogador;
    private LimGerenciarOcorrenciasJogo limGerenciarOcorrencias;
    private CtrCartao ctrCartao;
    
    private JTextField jtfJogador;
    private JTextField jtfInstanteTempoMin;
    private JTextField jtfInstateTempoSeg;
    private JRadioButton jrbCartaoAmarelo;
    private JRadioButton jrbCartaoVermelho;
    private ButtonGroup bgCartao;

    public LimRegistrarCartao(CtrCartao ctrCartao, LimBuscarJogador limBuscarJogador, LimGerenciarOcorrenciasJogo limGerenciarOcorrencias)
    {
        this.limBuscarJogador = limBuscarJogador;
        this.limGerenciarOcorrencias = limGerenciarOcorrencias;
        this.ctrCartao = ctrCartao;
        
        setTitle("Registrar Cartão");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(montaMainPanel());
        setModal(true);
        setSize(360, 170);
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
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        JPanel jpAuxJogador = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelJRBCartao = new JPanel(new GridLayout(2, 1));
        JPanel jpAuxTempo = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel jlInstanteTempo = new JLabel("Instante de Tempo:");
        UtilView.alinhaLabel(jlInstanteTempo);
        JLabel jlJogador = new JLabel("Jogador:");
        UtilView.alinhaLabel(jlJogador);
        JLabel jlCartao = new JLabel("Cartão:");
        UtilView.alinhaLabel(jlCartao);

        jtfInstanteTempoMin = new JTextField(4);
        JLabel jlMin = new JLabel("min");

        jtfInstateTempoSeg = new JTextField(4);
        JLabel jlSeg = new JLabel("seg");

        jtfJogador = new JTextField(10);
        jtfJogador.setEditable(false);

        jrbCartaoAmarelo = new JRadioButton("Amarelo");
        jrbCartaoAmarelo.setActionCommand("Amarelo");
        jrbCartaoAmarelo.setSelected(true);
        
        jrbCartaoVermelho = new JRadioButton("Vermelho");
        jrbCartaoVermelho.setActionCommand("Vermelho");

        JButton jbRegistrarCartao = new JButton("Registrar Cartão");
        jbRegistrarCartao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                registrarCartao();
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
        bgCartao.add(jrbCartaoAmarelo);
        bgCartao.add(jrbCartaoVermelho);

        formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlInstanteTempo));
        jpAuxTempo.add(jtfInstanteTempoMin);
        jpAuxTempo.add(jlMin);
        jpAuxTempo.add(jtfInstateTempoSeg);
        jpAuxTempo.add(jlSeg);
        formPanel.add(jpAuxTempo);

        formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlJogador));
        jpAuxJogador.add(jtfJogador);
        jpAuxJogador.add(jbPesqJogador);
        formPanel.add(jpAuxJogador);

        formPanel.add(UtilView.putComponentInFlowLayoutPanel(jlCartao));
        panelJRBCartao.add(jrbCartaoAmarelo);
        panelJRBCartao.add(jrbCartaoVermelho);
        formPanel.add(panelJRBCartao);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarCartao), BorderLayout.SOUTH);

        return mainPanel;
    }

    private void ativaTelaBuscarJogador()
    {
        limBuscarJogador.ativaTela(this);
    }

    @Override
    public void jogadorSelecionado(Short idJogador)
    {
       jtfJogador.setText(idJogador+""); 
    }
    
    private void resetCamposInterface()
    {
        jtfInstanteTempoMin.setText("");
        jtfInstateTempoSeg.setText("");
        jtfJogador.setText("");
        jrbCartaoAmarelo.setSelected(true);                
    }
    
    private void registrarCartao()
    {
        String cor;
        ResultadoOperacao result;
        
        cor = bgCartao.getSelection().getActionCommand();
        
        result = ctrCartao.registraCartao(jtfInstanteTempoMin.getText(),
                                          jtfInstateTempoSeg.getText(),
                                          limGerenciarOcorrencias.getIdJogo(),
                                          jtfJogador.getText(),
                                          cor);
        
        if (result.getTipo() == TipoResultadoOperacao.EXITO)
        {
            JOptionPane.showMessageDialog(this, result.getMsg(), "Exito!", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            resetCamposInterface();
            limGerenciarOcorrencias.preencheTabelaCartao();
        }
        else
        {
            JOptionPane.showMessageDialog(this, result.getMsg(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
