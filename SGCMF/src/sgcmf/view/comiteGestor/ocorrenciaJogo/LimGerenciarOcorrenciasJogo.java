package sgcmf.view.comiteGestor.ocorrenciaJogo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import sgcmf.control.CtrComiteGestor;
import sgcmf.control.CtrOcorrenciaJogo;
import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;
import sgcmf.view.UtilView;
import sgcmf.view.comiteGestor.LimBuscarJogador;
import sgcmf.view.table.JTableSGCMF;

public class LimGerenciarOcorrenciasJogo extends JDialog
{
    private CtrComiteGestor ctrComiteGestor;
    private LimRegistrarGol limRegistrarGol;
    private LimRegistrarFalta limRegistrarFalta;
    private LimRegistrarCartao limRegistrarCartao;
    private LimRegistrarSubstituicao limRegistrarSubstituicao;
    private final String nameCardPanelGol = "GOL";
    private final String nameCardPanelFalta = "FALTA";
    private final String nameCardPanelCartao = "CARTAO";
    private final String nameCardPanelSubst = "SUBST";
    private JTableSGCMF jtGol;
    private JTableSGCMF jtFalta;
    private JTableSGCMF jtCartao;
    private JTableSGCMF jtSubst;
    private JRadioButton jrbGol;
    private JRadioButton jrbFalta;
    private JRadioButton jrbCartao;
    private JRadioButton jrbSubst;
    private JLabel jlInfoJogo;
    private JPanel centerPanel;
    private Short idJogo;

    public LimGerenciarOcorrenciasJogo(CtrComiteGestor ctrComiteGestor, LimBuscarJogador limBuscarJogador)
    {
        this.ctrComiteGestor = ctrComiteGestor;

        limRegistrarGol = new LimRegistrarGol(ctrComiteGestor, limBuscarJogador, this);
        limRegistrarFalta = new LimRegistrarFalta(ctrComiteGestor.getCtrFalta(), limBuscarJogador, this);
        limRegistrarCartao = new LimRegistrarCartao(ctrComiteGestor.getCtrCartao(), limBuscarJogador, this);
        limRegistrarSubstituicao = new LimRegistrarSubstituicao(ctrComiteGestor, limBuscarJogador, this);

        setTitle("Gerenciar Ocorrências para o Jogo Selecionado");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        add(montaMainPanel());

        setModal(true);
        setSize(600, 500);
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

        mainPanel.add(montaNorthPanel(), BorderLayout.NORTH);
        mainPanel.add(montaCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(montaSouthPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel montaNorthPanel()
    {
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel rotuloJogoPanel = new JPanel();
        rotuloJogoPanel.setBorder(BorderFactory.createTitledBorder("Informações do jogo selecionado:"));

        jlInfoJogo = new JLabel("", JLabel.CENTER);
        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        jlInfoJogo.setForeground(Color.blue.darker());
        jlInfoJogo.setFont(f);
        
        rotuloJogoPanel.add(jlInfoJogo);

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setBorder(BorderFactory.createTitledBorder("Ver ocorrências do tipo:"));

        ButtonGroup bg = new ButtonGroup();
        jrbGol = new JRadioButton("Gol");
        jrbGol.setSelected(true);
        jrbFalta = new JRadioButton("Falta");
        jrbCartao = new JRadioButton("Cartão");
        jrbSubst = new JRadioButton("Substituição");

        bg.add(jrbGol);
        bg.add(jrbFalta);
        bg.add(jrbCartao);
        bg.add(jrbSubst);

        jrbGol.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) centerPanel.getLayout();
                cl.show(centerPanel, nameCardPanelGol);
            }
        });

        jrbFalta.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) centerPanel.getLayout();
                cl.show(centerPanel, nameCardPanelFalta);
            }
        });

        jrbCartao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) centerPanel.getLayout();
                cl.show(centerPanel, nameCardPanelCartao);
            }
        });

        jrbSubst.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout) centerPanel.getLayout();
                cl.show(centerPanel, nameCardPanelSubst);
            }
        });

        radioButtonPanel.add(jrbGol);
        radioButtonPanel.add(jrbFalta);
        radioButtonPanel.add(jrbCartao);
        radioButtonPanel.add(jrbSubst);

        northPanel.add(rotuloJogoPanel, BorderLayout.NORTH);
        northPanel.add(radioButtonPanel, BorderLayout.CENTER);

        return northPanel;
    }

    private JPanel montaCenterPanel()
    {
        centerPanel = new JPanel(new CardLayout());

        centerPanel.add(nameCardPanelGol, montaCenterPanelGol());
        centerPanel.add(nameCardPanelFalta, montaCenterPanelFalta());
        centerPanel.add(nameCardPanelCartao, montaCenterPanelCartao());
        centerPanel.add(nameCardPanelSubst, montaCenterPanelSubst());

        return centerPanel;
    }

    private JPanel montaCenterPanelGol()
    {
        JPanel centerPanelGol = new JPanel(new BorderLayout());

        String[] nomesColunas =
        {
            "ID", "Instante de Tempo", "Seleção", "Jogador Autor", "Jogador Assistência", "Tipo Gol", "Modo"
        };
        jtGol = new JTableSGCMF(null, nomesColunas);
        JScrollPane jsp = new JScrollPane(jtGol, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerPanelGol.add(jsp, BorderLayout.CENTER);

        JButton jbRegistrarGol = new JButton("Registrar Novo Gol");
        jbRegistrarGol.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limRegistrarGol.setVisible(true);
            }
        });
        centerPanelGol.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarGol), BorderLayout.SOUTH);

        return centerPanelGol;
    }

    private JPanel montaCenterPanelFalta()
    {
        JPanel centerPanelFalta = new JPanel(new BorderLayout());

        String[] nomesColunas =
        {
            "ID", "Instante de Tempo", "Seleção",  "Jogador Autor", "Cartão", "Tipo"
        };
        jtFalta = new JTableSGCMF(null, nomesColunas);
        JScrollPane jsp = new JScrollPane(jtFalta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerPanelFalta.add(jsp, BorderLayout.CENTER);

        JButton jbRegistrarFalta = new JButton("Registrar Nova Falta");
        jbRegistrarFalta.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limRegistrarFalta.setVisible(true);
            }
        });
        centerPanelFalta.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarFalta), BorderLayout.SOUTH);

        return centerPanelFalta;
    }

    private JPanel montaCenterPanelCartao()
    {
        JPanel centerPanelCartao = new JPanel(new BorderLayout());

        String[] nomesColunas =
        {
            "ID", "Instante de Tempo", "Seleção" ,"Jogador", "Cor"
        };
        jtCartao = new JTableSGCMF(null, nomesColunas);
        JScrollPane jsp = new JScrollPane(jtCartao, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerPanelCartao.add(jsp, BorderLayout.CENTER);

        JButton jbRegistrarCartao = new JButton("Registrar Novo Cartão");
        jbRegistrarCartao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limRegistrarCartao.setVisible(true);
            }
        });
        centerPanelCartao.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarCartao), BorderLayout.SOUTH);

        return centerPanelCartao;
    }

    private JPanel montaCenterPanelSubst()
    {
        JPanel centerPanelSubst = new JPanel(new BorderLayout());

        String[] nomesColunas =
        {
            "ID", "Instante de Tempo", "Seleção", "Jogador Saiu", "Jogador Entrou", "Motivo"
        };
        jtSubst = new JTableSGCMF(null, nomesColunas);
        JScrollPane jsp = new JScrollPane(jtSubst, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerPanelSubst.add(jsp, BorderLayout.CENTER);

        JButton jbRegistrarSubst = new JButton("Registrar Nova Substituição");
        jbRegistrarSubst.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                limRegistrarSubstituicao.setVisible(true);
            }
        });
        centerPanelSubst.add(UtilView.putComponentInFlowLayoutPanel(jbRegistrarSubst), BorderLayout.SOUTH);

        return centerPanelSubst;
    }

    private JPanel montaSouthPanel()
    {
        JPanel southPanel = new JPanel();

        JButton jbRemoverOcorrencia = new JButton("Remover Ocorrência");
        jbRemoverOcorrencia.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                removerOcorrencia();
            }
        });
        southPanel.add(jbRemoverOcorrencia);

        return southPanel;
    }

    public void ativaTela(Short idJogo)
    {
        this.idJogo = idJogo;
        jlInfoJogo.setText(ctrComiteGestor.getCtrJogo().queryInfoJogoById(idJogo));
        preencheTabelaGol();
        preencheTabelaFalta();
        preencheTabelaCartao();
        preencheTabelaSubstituicao();
        
        setVisible(true);
    }

    public void preencheTabelaGol()
    {
        String[][] dadosGol;

        dadosGol = ctrComiteGestor.getCtrGol().queryGolByIdJogo(idJogo);
        jtGol.preencheTabela(dadosGol);
    }

    public void preencheTabelaFalta()
    {
        String[][] dadosFalta;

        dadosFalta = ctrComiteGestor.getCtrFalta().queryFaltaByIdJogo(idJogo);
        jtFalta.preencheTabela(dadosFalta);
    }
    
    public void preencheTabelaCartao()
    {
        String [][] dadosCartao;
        
        dadosCartao = ctrComiteGestor.getCtrCartao().queryCartaoByIdJogo(idJogo);
        jtCartao.preencheTabela(dadosCartao);
    }
    
    public void preencheTabelaSubstituicao()
    {
        String[][] dadosSubst;
        
        dadosSubst = ctrComiteGestor.getCtrSubstituicao().querySubstByIdJogo(idJogo);
        jtSubst.preencheTabela(dadosSubst);
    }

    private void resetCamposInterface()
    {
        jlInfoJogo.setText("");
        jrbGol.setSelected(true);

        CardLayout cl = (CardLayout) centerPanel.getLayout();
        cl.show(centerPanel, nameCardPanelGol);
    }

    private void removerOcorrencia()
    {
        Short idOc;
        int linhaSelecionada;
        ResultadoOperacao result = null;

        if (jrbGol.isSelected())
        {
            linhaSelecionada = jtGol.getSelectedRow();
            if (linhaSelecionada != -1)
            {
                idOc = Short.parseShort((String) jtGol.getValueAt(linhaSelecionada, 0));
                result = ctrComiteGestor.getCtrGol().removeGol(idOc);
                preencheTabelaGol();
            }
        }
        else if (jrbFalta.isSelected())
        {
            linhaSelecionada = jtFalta.getSelectedRow();
            if (linhaSelecionada != -1)
            {
                idOc = Short.parseShort((String) jtFalta.getValueAt(linhaSelecionada, 0));
                result = ctrComiteGestor.getCtrFalta().removeFalta(idOc);
                preencheTabelaFalta();
                preencheTabelaCartao();
            }
        }
        else if (jrbCartao.isSelected())
        {
            linhaSelecionada = jtCartao.getSelectedRow();
            if (linhaSelecionada != -1)
            {
                idOc = Short.parseShort((String) jtCartao.getValueAt(linhaSelecionada, 0));
                result = ctrComiteGestor.getCtrCartao().removerCartao(idOc);
                
                preencheTabelaFalta();
                preencheTabelaCartao();
            }
        }
        else
        {
            linhaSelecionada = jtSubst.getSelectedRow();
            if (linhaSelecionada != -1)
            {
                idOc = Short.parseShort((String) jtSubst.getValueAt(linhaSelecionada, 0));
                result = ctrComiteGestor.getCtrSubstituicao().removeSubstituicao(idOc);
                
                preencheTabelaSubstituicao();
            }
        }

        if (result.getTipo() == TipoResultadoOperacao.EXITO)
        {
            JOptionPane.showMessageDialog(this, result.getMsg(), "Êxito!", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else
        {
            JOptionPane.showMessageDialog(this, result.getMsg(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public Short getIdJogo()
    {
        return idJogo;
    }   
}
