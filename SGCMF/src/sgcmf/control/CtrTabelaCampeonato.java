package sgcmf.control;

import sgcmf.view.tabela.LimTabelaCampeonato;

public class CtrTabelaCampeonato
{
    private CtrMain ctrMain;
    private LimTabelaCampeonato limTabelaCampeonato;
    private boolean precisaAtualizarTabela = true;

    public CtrTabelaCampeonato(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        this.limTabelaCampeonato = new LimTabelaCampeonato(this);
    }    
    
    public void ativaTela()
    {
        limTabelaCampeonato.ativaTela();
    }
    
    public CtrJogo getCtrJogo()
    {
        return ctrMain.getCtrJogo();
    }
    
    public boolean getPrecisaAtualizarTabela()
    {
        return precisaAtualizarTabela;
    }

    public void setPrecisaAtualizarTabela(boolean boolPrecisaAtualizar)
    {
        this.precisaAtualizarTabela = boolPrecisaAtualizar;
    }
}
