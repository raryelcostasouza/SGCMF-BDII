package sgcmf.view.table;

import sgcmf.view.table.DefaultTableModelSGCMF;

public class DefaultTableModelC1 extends DefaultTableModelSGCMF
{
    public DefaultTableModelC1(Object[][] data, Object[] columnNames)
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
