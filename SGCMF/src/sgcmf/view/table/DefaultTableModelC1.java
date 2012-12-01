package sgcmf.view.table;

public class DefaultTableModelC1 extends DefaultTableModelSGCMF
{
    private int colunaJLabel = 1;

    public DefaultTableModelC1(Object[][] data, Object[] columnNames)
    {
        super(data, columnNames);
    }

    public DefaultTableModelC1(Object[][] data, Object[] columnNames, int colunaJLabel)
    {
        this(data, columnNames);
        this.colunaJLabel = colunaJLabel;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if (columnIndex == colunaJLabel)
        {
            return getValueAt(0, columnIndex).getClass();
        }
        else
        {
            return super.getColumnClass(columnIndex);
        }
    }
}
