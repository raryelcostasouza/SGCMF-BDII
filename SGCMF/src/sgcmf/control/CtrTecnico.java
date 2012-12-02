package sgcmf.control;

import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.dao.SelecaoDAO;
import sgcmf.model.hibernate.Usuario;
import sgcmf.view.tecnico.LimTecnico;

public class CtrTecnico
{
    private LimTecnico limTecnico;
    private CtrMain ctrMain;
    private Usuario user;
    private Short idSelecao;

    public CtrTecnico(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        limTecnico = new LimTecnico(this, ctrMain.getCtrJogo());
    }

    public void ativaTela(Usuario u)
    {
        limTecnico.setVisible(true);
        user = u;
        SGCMFSessionManager.abrirSessao();
        idSelecao = SelecaoDAO.getInstance().queryIdSelecaoByIdUsuarioTecnico(u.getId());
        SGCMFSessionManager.fecharSessao();
    }

    public Short getIdSelecao()
    {
        return idSelecao;
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
