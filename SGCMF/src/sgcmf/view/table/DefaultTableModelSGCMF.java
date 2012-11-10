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
    
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
}
