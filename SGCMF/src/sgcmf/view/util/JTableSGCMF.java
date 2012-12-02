package sgcmf.view.util;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JTableSGCMF extends JTable
{
    private String[] nomeColunas;

    public JTableSGCMF(Object[][] dados, Object[] nomeColunas)
    {
        super(new DefaultTableModelSGCMF(dados, nomeColunas));
        this.nomeColunas = (String[]) nomeColunas;
        //Permite a seleção apenas de uma linha.
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Não permite trocar as colunas da tabela de posição.
        getTableHeader().setReorderingAllowed(false);
    }

    public JTableSGCMF(Object[][] dados, Object[] nomeColunas, final ReceiveRowDataSGCMF janela)
    {
        this(dados, nomeColunas);
        this.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                String[] dadosLinha = new String[dataModel.getColumnCount()];
                //If usado para pegar um único evento.
                if (e.getValueIsAdjusting())
                {
                    return;
                }
                dataModel.getColumnCount();
                //If eh usado para verificar se nao existe nenhuma linha selecionada antes de fazer o listener
                if (getSelectedRowCount() == 1)
                {
                    for (int i = 0; i < dataModel.getColumnCount(); i++)
                    {
                        dadosLinha[i] = getValueAt(getSelectedRow(), i).toString();
                    }
                    janela.receiveRowData(dadosLinha);
                }
            }
        });
    }

    public void preencheTabela(Object[][] dados)
    {
        DefaultTableModelSGCMF dm = (DefaultTableModelSGCMF) getModel();
        dm.setDataVector(dados, nomeColunas);
    }
}
