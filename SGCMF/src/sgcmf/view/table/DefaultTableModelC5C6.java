package sgcmf.view.table;


public class DefaultTableModelC5C6 extends DefaultTableModelSGCMF
{
    public DefaultTableModelC5C6(Object[][] data, Object[] columnNames)
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
