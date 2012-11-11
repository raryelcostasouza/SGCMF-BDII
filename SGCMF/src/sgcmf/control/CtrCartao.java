package sgcmf.control;

import sgcmf.model.other.ResultadoOperacao;
import sgcmf.model.other.TipoResultadoOperacao;

public class CtrCartao
{
    private CtrMain ctrMain;
    
    public CtrCartao(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
    }    
    
    public ResultadoOperacao registraCartao()
    {
       ResultadoOperacao result;
       
       result = new ResultadoOperacao("", TipoResultadoOperacao.ERRO);
       
       return result;
    }
}
