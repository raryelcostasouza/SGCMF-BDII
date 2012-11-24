package sgcmf.control;

import sgcmf.view.tabela.LimTabelaCampeonato;

public class CtrTabelaCampeonato
{
    private CtrMain ctrMain;
    private LimTabelaCampeonato limTabelaCampeonato;

    public CtrTabelaCampeonato(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        this.limTabelaCampeonato = new LimTabelaCampeonato(this);
    }    
    
    public void ativaTela()
    {
        limTabelaCampeonato.setVisible(true);
    }
}
