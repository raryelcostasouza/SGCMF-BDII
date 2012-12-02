/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.view.table;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Helio
 */
public class DefaultTableModelSGCMF extends DefaultTableModel
{
    public DefaultTableModelSGCMF(Object[][] data, Object[] columnNames)
    {
        super(data, columnNames);
    }
    
    @Override
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return getValueAt(0, columnIndex).getClass();
    }
}
