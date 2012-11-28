package sgcmf.view.table;


public class DefaultTableModelJogo extends DefaultTableModelSGCMF
{
    public DefaultTableModelJogo(Object[][] data, Object[] columnNames)
    {
        super(data, columnNames);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if (columnIndex == 5 || columnIndex ==6)
        {
            return getValueAt(0, columnIndex).getClass();
        }
        else
        {
            return super.getColumnClass(columnIndex);
        }        
    }
}
