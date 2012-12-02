/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.tecnico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import sgcmf.control.CtrJogador;
import sgcmf.control.CtrMain;
import sgcmf.control.CtrSelecao;
import sgcmf.control.CtrTecnico;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;
import sgcmf.view.util.UtilView;

/**
 *
 * @author Helio
 */
public class PanelCadastrarJogador extends JPanel
{
    private CtrTecnico ctrTecnico;
    private CtrMain ctrMain;
    private CtrSelecao ctrSelecao;
    private CtrJogador ctrJogador;
    private JTextField jtfNumeroCamisa;
    private JTextField jtfNome;
    private JTextField jtfAltura;
    private JTextField jtfDataNascimento;
    private JComboBox jcbPosicao;
    private JRadioButton jrbSim;
    private String[] items =
    {
        "Goleiro", "Lateral Esquerdo", "Lateral Direito",
        "Atacante", "Volante", "Zagueiro"
    };

    public PanelCadastrarJogador(CtrTecnico ctrTecnico)
    {
        this.ctrTecnico = ctrTecnico;
        ctrMain = ctrTecnico.getCtrMain();
        ctrSelecao = ctrMain.getCtrSelecao();
        ctrJogador = ctrMain.getCtrJogador();
        setLayout(new BorderLayout());

        montaPainel();
    }

    private void montaPainel()
    {
        JPanel jpAux = new JPanel(new GridLayout(6, 1));
        JPanel jpAux3 = new JPanel(new GridLayout(1, 2));

        JLabel jlNumeroCamisa = new JLabel("Número da Camisa:");
        UtilView.alinhaLabel(jlNumeroCamisa);
        JLabel jlNome = new JLabel("Nome:");
        UtilView.alinhaLabel(jlNome);
        JLabel jlDataNascimento = new JLabel("Data Nascimento:");
        UtilView.alinhaLabel(jlDataNascimento);
        JLabel jlAltura = new JLabel("Altura:");
        UtilView.alinhaLabel(jlAltura);
        JLabel jlTitular = new JLabel("Titular:");
        UtilView.alinhaLabel(jlTitular);
        JLabel jlPosicao = new JLabel("Posição:");
        UtilView.alinhaLabel(jlPosicao);

        ButtonGroup bg = new ButtonGroup();
        jrbSim = new JRadioButton("Sim");
        jrbSim.setSelected(true);
        final JRadioButton jrbNao = new JRadioButton("Não");
        bg.add(jrbSim);
        bg.add(jrbNao);


        jtfNumeroCamisa = new JTextField(10);
        jtfNome = new JTextField(10);
        jtfDataNascimento = new JTextField(10);
        jtfAltura = new JTextField(10);

        jcbPosicao = new JComboBox(items);
        jcbPosicao.setEditable(false);
        jcbPosicao.setPreferredSize(new Dimension(132, 20));

        JButton jbCadastrar = new JButton("Cadastrar");
        jbCadastrar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String numCamisa = jtfNumeroCamisa.getText();
                String nome = jtfNome.getText();
                String dataNascimento = jtfDataNascimento.getText();
                String altura = jtfAltura.getText();
                boolean titular = false;
                ResultadoOperacao resultado;
                String posicao = (String) jcbPosicao.getSelectedItem();
                if (jrbSim.isSelected())
                {
                    titular = true;
                }
                resultado = ctrJogador.cadastrarJogador(numCamisa, nome, dataNascimento,
                        altura, titular, posicao, ctrTecnico.getUser());
                if (resultado.getTipo().equals(TipoResultadoOperacao.ERRO))
                {
                    JOptionPane.showMessageDialog(null, resultado.getMsg(), "Erro"
                            + " no Cadastro de Jogador", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, resultado.getMsg(), "Cadastro"
                            + " bem Sucedido", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                }
            }
        });

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlNumeroCamisa));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfNumeroCamisa, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlNome));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfNome, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlDataNascimento));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfDataNascimento, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlAltura));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfAltura, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlTitular));

        jpAux3.add(UtilView.putComponentInFlowLayoutPanel(jrbSim));
        jpAux3.add(UtilView.putComponentInFlowLayoutPanel(jrbNao));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jpAux3, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlPosicao));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jcbPosicao, FlowLayout.LEFT));
        jpAux.setBorder(BorderFactory.createEtchedBorder());

        this.add(jpAux, BorderLayout.CENTER);
        this.add(UtilView.putComponentInFlowLayoutPanel(jbCadastrar), BorderLayout.SOUTH);
    }

    public void limparCampos()
    {
        jtfNumeroCamisa.setText("");
        jtfNome.setText("");
        jtfDataNascimento.setText("");
        jtfAltura.setText("");
        jcbPosicao.setSelectedIndex(0);
        jrbSim.setSelected(true);
    }
}
