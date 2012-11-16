package sgcmf.control;

import sgcmf.model.hibernate.Usuario;
import sgcmf.view.tecnico.LimTecnico;

public class CtrTecnico
{
    private LimTecnico limTecnico;
    private CtrMain ctrMain;
    private Usuario user;
    
    public CtrTecnico(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        limTecnico = new LimTecnico(this, ctrMain.getCtrJogo());
    }

    public void ativaTela(Usuario u)
    {
        limTecnico.setVisible(true);
        user = u;
    }

    public void logout()
    {
        ctrMain.ativaTela();
    }

    public CtrMain getCtrMain()
    {
        return ctrMain;
    }

    public Usuario getUser()
    {
        return user;
    }
}
