package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.Jogo;
import sgcmf.model.Selecao;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.JogoDAO;
import sgcmf.model.dao.SelecaoDAO;
import sgcmf.view.comiteGestor.LimComiteGestor;

public class CtrComiteGestor
{
    private LimComiteGestor limComiteGestor;
    private CtrMain ctrMain;

    public CtrComiteGestor(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        limComiteGestor = new LimComiteGestor(this, ctrMain.getCtrJogo(), ctrMain.getCtrSelecao());
    }

    public void ativaTela()
    {
        limComiteGestor.setVisible(true);
    }

    public void logout()
    {
        ctrMain.ativaTela();
    }
	
	
	
	
}
