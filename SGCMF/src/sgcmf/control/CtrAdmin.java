package sgcmf.control;

import sgcmf.view.LimAdmin;

public class CtrAdmin
{
    private LimAdmin limAdmin;
    private CtrMain ctrMain;

    public CtrAdmin(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        limAdmin = new LimAdmin(this);
    }

    public void ativaTela()
    {
        limAdmin.setVisible(true);
    }

    public void logout()
    {
        ctrMain.ativaTela();
    }

    public CtrMain getCtrMain()
    {
        return ctrMain;
    }
}
