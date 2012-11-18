package sgcmf.view.table.selecao;

import sgcmf.view.table.DefaultTableModelSGCMF;

public class DefaultTableModelSelecao extends DefaultTableModelSGCMF
{
    public DefaultTableModelSelecao(Object[][] data, Object[] columnNames)
    {
        super(data, columnNames);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if (columnIndex == 1)
        {
            return getValueAt(0, columnIndex).getClass();
        }
        else
        {
            return super.getColumnClass(columnIndex);
        }        
    }
}
