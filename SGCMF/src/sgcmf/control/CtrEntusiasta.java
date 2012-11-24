package sgcmf.control;

import sgcmf.view.LimEntusiasta;

public class CtrEntusiasta
{
    private CtrMain ctrMain;
    private LimEntusiasta limEntusiasta;
    
    public CtrEntusiasta(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        limEntusiasta = new LimEntusiasta(this);
    }

    public void logout()
    {
        ctrMain.ativaTela();
    }
    
    public void ativaTela()
    {
        limEntusiasta.setVisible(true);
    }
    
    public CtrRelatorio getCtrRelatorio()
    {
        return ctrMain.getCtrRelatorio();
    }
}
