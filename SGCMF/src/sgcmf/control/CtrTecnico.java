package sgcmf.control;

import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.hibernate.Jogador;
import sgcmf.model.hibernate.Selecao;
import sgcmf.view.tecnico.LimTecnico;

public class CtrTecnico
{
    private LimTecnico limTecnico;
    private CtrMain ctrMain;
    
    public CtrTecnico(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        limTecnico = new LimTecnico(this, ctrMain.getCtrJogo());
    }

    public void ativaTela()
    {
        limTecnico.setVisible(true);
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
