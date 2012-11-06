/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.control;

import sgcmf.view.relatorio.LimGerenciarRelatorio;

/**
 *
 * @author Helio
 */
public class CtrRelatorio
{
    private LimGerenciarRelatorio limGerenciarRelatorio;
    
    public CtrRelatorio()
    {
        limGerenciarRelatorio = new LimGerenciarRelatorio();
    }
    
    public void ativaTela()
    {
        limGerenciarRelatorio.setVisible(true);
    }
}
