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
    private CtrMain ctrMain;

    public CtrRelatorio(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        limGerenciarRelatorio = new LimGerenciarRelatorio(this);
    }

    public void ativaTela()
    {
        limGerenciarRelatorio.setVisible(true);
    }

    public CtrMain getCtrMain()
    {
        return ctrMain;
    }
}
