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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sgcmf.control.CtrJogador;
import sgcmf.control.CtrMain;
import sgcmf.control.CtrTecnico;
import sgcmf.model.other.SGCMFIcons;
import sgcmf.view.UtilView;
import sgcmf.view.table.JTableSGCMF;
import sgcmf.view.table.ReceiveRowDataSGCMF;

/**
 *
 * @author Helio
 */
public class PanelAlterarJogador extends JPanel implements ReceiveRowDataSGCMF
{
    private JTableSGCMF jt;
    private CtrMain ctrMain;
    private CtrJogador ctrJogador;
    private JRadioButton jrbNome;
    private JRadioButton jrbPosicao;
    private JTextField jtfPesquisar;
    private JTextField jtfNumeroCamisa;
    private JTextField jtfNome;
    private JTextField jtfDataNascimento;
    private JTextField jtfAltura;
    private JTextField jtfSelecao;
    private JComboBox jcbPosicao;

    public PanelAlterarJogador(CtrTecnico ctrTecnico)
    {
        ctrMain = ctrTecnico.getCtrMain();
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

        JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jsp;
    }

    private JPanel montaPainelSouth()
    {
        JPanel jpPrincipal = new JPanel(new BorderLayout());
        JPanel jpAux = new JPanel(new GridLayout(3, 2));
        JPanel jpAux2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        JLabel jlSelecao = new JLabel("Seleção:");
        UtilView.alinhaLabel(jlSelecao);

        jtfNumeroCamisa = new JTextField(10);
        jtfNome = new JTextField(10);
        jtfDataNascimento = new JTextField(10);
        jtfAltura = new JTextField(10);
        jcbPosicao = new JComboBox(posicao);
        jcbPosicao.setPreferredSize(new Dimension(132, 20));
        jtfSelecao = new JTextField(10);
        jtfSelecao.setEditable(false);


        JButton jbPesquisar = new JButton(SGCMFIcons.PESQUISAR);
        UtilView.ajustarTamanhoBotaoPesquisar(jbPesquisar);
        JButton jbAlterar = new JButton("Alterar");
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
        jpAux.add(UtilView.putComponentInFlowLayoutPanel(jlSelecao));
        jpAux2.add(jtfSelecao);
        jpAux2.add(jbPesquisar);
        jpAux.add(jpAux2);
        jpAux.setBorder(BorderFactory.createEtchedBorder());

        jpPrincipal.add(jpAux, BorderLayout.CENTER);
        jpPrincipal.add(UtilView.putComponentInFlowLayoutPanel(jbAlterar), BorderLayout.SOUTH);

        return jpPrincipal;
    }

    //Daqui pra baixo é identico ao consultarJogador, tem que arrumar.
    public void limparCampos()
    {
        jrbNome.setSelected(true);
        jtfPesquisar.setText("");
    }

    public void ativaTela()
    {
        String[][] dadosJogador;
        dadosJogador = ctrJogador.queryAllDataJogadorTodos();
        jt.preencheTabela(dadosJogador);
    }

    private void pesquisar(String chavePesquisa)
    {
        String[][] dadosJogador;
        if (jrbNome.isSelected())
        {
            dadosJogador = ctrJogador.queryAllDataJogadorByNome(chavePesquisa);
        }
        else
        {
            dadosJogador = ctrJogador.queryAllDataJogadorByPosicao(chavePesquisa);
        }
        jt.preencheTabela(dadosJogador);
    }

    @Override
    public void receiveRowData(String[] dados)
    {
        Short idSelecao;
        jtfNumeroCamisa.setText(dados[1]);
        jtfNome.setText(dados[2]);
        jtfDataNascimento.setText(dados[3]);
        jtfAltura.setText(dados[4]);
        jcbPosicao.setSelectedItem((String) dados[5]);
        idSelecao = ctrMain.getCtrSelecao().capturarIdSelecao(dados[6]);
        jtfSelecao.setText(idSelecao + "");
    }
}
