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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrJogador;
import sgcmf.control.CtrMain;
import sgcmf.control.CtrSelecao;
import sgcmf.control.CtrTecnico;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;
import sgcmf.view.util.UtilView;
import sgcmf.view.util.DefaultTableModelSGCMF;
import sgcmf.view.util.JLabelTableCellRenderer;
import sgcmf.view.util.JTableSGCMF;
import sgcmf.view.util.ReceiveRowDataSGCMF;

/**
 *
 * @author Helio
 */
public class PanelAlterarJogador extends JPanel implements ReceiveRowDataSGCMF
{
    private JTableSGCMF jt;
    private CtrMain ctrMain;
    private CtrTecnico ctrTecnico;
    private CtrJogador ctrJogador;
    private CtrSelecao ctrSelecao;
    private JRadioButton jrbNome;
    private JRadioButton jrbPosicao;
    private JTextField jtfPesquisar;
    private JTextField jtfNumeroCamisa;
    private JTextField jtfNome;
    private JTextField jtfDataNascimento;
    private JTextField jtfAltura;
    private JComboBox jcbPosicao;
    private JButton jbAlterar;

    public PanelAlterarJogador(CtrTecnico ctrTecnico)
    {
        this.ctrTecnico = ctrTecnico;
        ctrMain = ctrTecnico.getCtrMain();
        ctrSelecao = ctrMain.getCtrSelecao();
        ctrJogador = ctrMain.getCtrJogador();
        setLayout(new BorderLayout());
        montaPainelPrincipal();
    }

    private void montaPainelPrincipal()
    {
        //JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpNorth = montaPainelNorte();
        JScrollPane jpCenter = montaPainelCentral();
        JPanel jpSouth = montaPainelSouth();

        this.add(jpNorth, BorderLayout.NORTH);
        this.add(jpCenter, BorderLayout.CENTER);
        this.add(jpSouth, BorderLayout.SOUTH);

    }

    private JPanel montaPainelNorte()
    {
        JPanel jpNorth = new JPanel(new BorderLayout());
        JPanel jpEsquerda = new JPanel();
        JPanel jpDireita = new JPanel();

        jtfPesquisar = new JTextField(15);
        jtfPesquisar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pesquisar(jtfPesquisar.getText());
            }
        });
        jrbNome = new JRadioButton("Nome");
        jrbNome.setSelected(true);
        jrbPosicao = new JRadioButton("Posição");

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbNome);
        bg.add(jrbPosicao);

        jpEsquerda.add(jrbNome);
        jpEsquerda.add(jrbPosicao);
        jpEsquerda.setBorder(BorderFactory.createTitledBorder("Pesquisar por:"));

        jpDireita.add(UtilView.putComponentInFlowLayoutPanel(jtfPesquisar));
        jpDireita.setBorder(BorderFactory.createTitledBorder("Buscar:"));

        jpNorth.add(jpEsquerda, BorderLayout.WEST);
        jpNorth.add(jpDireita, BorderLayout.EAST);

        return jpNorth;
    }

    private JScrollPane montaPainelCentral()
    {
        String[] nomeColunas =
        {
            "ID", "Número Camisa", "Nome", "Data Nascimento", "Altura", "Posição", "Seleção", "Titular"
        };
        jt = new JTableSGCMF(null, nomeColunas, this);
        jt.setModel(new DefaultTableModelSGCMF(null, nomeColunas));
        jt.setDefaultRenderer(JLabel.class, new JLabelTableCellRenderer());
        jt.setRowHeight(32);
        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    private JPanel montaPainelSouth()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(3, 2));
        String[] posicao =
        {
            "Goleiro", "Lateral Esquerdo", "Lateral Direito", "Atacante", "Volante", "Zagueiro"
        };

        JLabel jlNumeroCamisa = new JLabel("Número da Camisa:");
        UtilView.alinhaLabel(jlNumeroCamisa);
        JLabel jlNome = new JLabel("Nome:");
        UtilView.alinhaLabel(jlNome);
        JLabel jlDataNascimento = new JLabel("Data Nascimento:");
        UtilView.alinhaLabel(jlDataNascimento);
        JLabel jlAltura = new JLabel("Altura:");
        UtilView.alinhaLabel(jlAltura);
        JLabel jlPosicao = new JLabel("Posição:");
        UtilView.alinhaLabel(jlPosicao);

        jtfNumeroCamisa = new JTextField(10);
        jtfNome = new JTextField(10);
        jtfDataNascimento = new JTextField(10);
        jtfAltura = new JTextField(10);
        jcbPosicao = new JComboBox(posicao);
        jcbPosicao.setPreferredSize(new Dimension(132, 20));
        jbAlterar = new JButton("Alterar");
        jbAlterar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String numCamisaNovo = jtfNumeroCamisa.getText();
                String numCamisaAtual = jtfNumeroCamisa.getText();
                String nome = jtfNome.getText();
                String dtaNascimento = jtfDataNascimento.getText();
                String altura = jtfAltura.getText();
                String posicao = (String) jcbPosicao.getSelectedItem();
                ResultadoOperacao result;
                String idJogador = jt.getValueAt(jt.getSelectedRow(), 0).toString();
                result = ctrJogador.alterarJogador(idJogador, numCamisaNovo, numCamisaAtual, nome, dtaNascimento,
                        altura, posicao, ctrTecnico.getUser());

                if (result.getTipo().equals(TipoResultadoOperacao.ERRO))
                {
                    JOptionPane.showMessageDialog(null, result.getMsg(), "Erro"
                            + " na Alteração do Jogador", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, result.getMsg(), "Alteração"
                            + " bem Sucedida", JOptionPane.INFORMATION_MESSAGE);
                    limparTodosCampos();
                    ativaTela();
                }
            }
        });

        travarBotoes();

        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlNumeroCamisa));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfNumeroCamisa, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlAltura));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfAltura, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlNome));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfNome, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlPosicao));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jcbPosicao, FlowLayout.LEFT));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlDataNascimento));
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jtfDataNascimento, FlowLayout.LEFT));
        jpAux.setBorder(BorderFactory.createEtchedBorder());

        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbAlterar), BorderLayout.SOUTH);

        return jpPrincipal;
    }
    
    //Daqui pra baixo é identico ao consultarJogador, tem que arrumar.
    public void limparTodosCampos()
    {
        travarBotoes();
        jrbNome.setSelected(true);
        jcbPosicao.setSelectedIndex(0);
        jtfPesquisar.setText("");
        jtfDataNascimento.setText("");
        jtfNumeroCamisa.setText("");
        jtfNome.setText("");
        jtfAltura.setText("");
    }

    public void limparParteCampos()
    {
        jcbPosicao.setSelectedIndex(0);
        jtfDataNascimento.setText("");
        jtfNumeroCamisa.setText("");
        jtfNome.setText("");
        jtfAltura.setText("");
    }

    public void ativaTela()
    {
        Object[][] dadosJogador;
        dadosJogador = ctrJogador.queryAllDataJogadorTecnico(ctrTecnico.getUser());
        jt.preencheTabela(dadosJogador);
    }

    private void pesquisar(String chavePesquisa)
    {
        Object[][] dadosJogadores;
        if (jrbNome.isSelected())
        {
            dadosJogadores = ctrJogador.queryAllDataJogadorByNomeAndByUser(chavePesquisa, ctrTecnico.getUser());
        }
        else
        {
            dadosJogadores = ctrJogador.queryAllDataJogadorByPosicaoAndByUser(chavePesquisa, ctrTecnico.getUser());

        }
        limparParteCampos();
        travarBotoes();
        jt.preencheTabela(dadosJogadores);
    }

    @Override
    public void receiveRowData(String[] dados)
    {
        String s;
        jbAlterar.setEnabled(true);
        jtfNumeroCamisa.setText(dados[1]);
        jtfNome.setText(dados[2]);
        //Transformando a String de data para o padrao do Java com "/"
        s = dados[3].replace(dados[3].charAt(4), '/');
        jtfDataNascimento.setText(s);
        jtfAltura.setText(dados[4]);
        jcbPosicao.setSelectedItem((String) dados[5]);
    }

    public void travarBotoes()
    {
        jbAlterar.setEnabled(false);
    }
}
