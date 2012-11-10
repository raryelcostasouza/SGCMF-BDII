package sgcmf.control;

import sgcmf.view.comiteGestor.LimComiteGestor;

public class CtrComiteGestor
{
    private LimComiteGestor limComiteGestor;
    private CtrMain ctrMain;

    public CtrComiteGestor(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        limComiteGestor = new LimComiteGestor(this, ctrMain.getCtrJogo(), ctrMain.getCtrGol(), ctrMain.getCtrSelecao(),
                ctrMain.getCtrOcorrenciaJogo(), ctrMain.getCtrJogador());
    }

    public void ativaTela()
    {
        limComiteGestor.setVisible(true);
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
