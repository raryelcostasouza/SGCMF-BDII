package sgcmf.control;

import sgcmf.view.comiteGestor.LimComiteGestor;

public class CtrComiteGestor
{
    private LimComiteGestor limComiteGestor;
    private CtrMain ctrMain;

    public CtrComiteGestor(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        limComiteGestor = new LimComiteGestor(this);
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

    public CtrJogador getCtrJogador()
    {
        return ctrMain.getCtrJogador();
    }

    public CtrJogo getCtrJogo()
    {
        return ctrMain.getCtrJogo();
    }

    public CtrGol getCtrGol()
    {
        return ctrMain.getCtrGol();
    }

    public CtrSelecao getCtrSelecao()
    {
        return ctrMain.getCtrSelecao();
    }

    public CtrOcorrenciaJogo getCtrOcorrenciaJogo()
    {
        return ctrMain.getCtrOcorrenciaJogo();
    }

    public CtrFalta getCtrFalta()
    {
        return ctrMain.getCtrFalta();
    }

    public CtrCartao getCtrCartao()
    {
        return ctrMain.getCtrCartao();
    }

    public CtrSubstituicao getCtrSubstituicao()
    {
        return ctrMain.getCtrSubstituicao();
    }
    
    public CtrRelatorio getCtrRelatorio()
    {
        return ctrMain.getCtrRelatorio();
    }
}
