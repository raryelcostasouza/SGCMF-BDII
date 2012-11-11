package sgcmf.control;

import java.util.ArrayList;
import sgcmf.model.dao.CartaoDAO;
import sgcmf.model.dao.SubstituicaoDAO;
import sgcmf.model.hibernate.Cartao;
import sgcmf.model.hibernate.Jogador;
import sgcmf.model.hibernate.Substituicao;

public class CtrSubstituicao
{
    private CtrMain ctrMain;

    public CtrSubstituicao(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
    }

    public String[][] querySubstByIdJogo(Short idJogo)
    {
        ArrayList<Substituicao> alSubst;
        String[][] dadosCartao;
        SubstituicaoDAO substDAO;

        substDAO = new SubstituicaoDAO();
        alSubst = substDAO.querySubstByIdJogo(idJogo);
        dadosCartao = arrayList2StringMatrix(alSubst);
        substDAO.fecharSessao();

        return dadosCartao;
    }
    
    public String[][] arrayList2StringMatrix(ArrayList<Substituicao> alSubst)
    {
        String[][] dadosSubst;
        Substituicao s;
        Jogador jEntrou;
        Jogador jSaiu;

        dadosSubst = new String[alSubst.size()][5];
        for (int i = 0; i < alSubst.size(); i++)
        {
            s = alSubst.get(i);
            
            jEntrou = s.getJogadorByIdjogadorentrou();
            jSaiu = s.getJogadorByIdjogadorsaiu();
            
            dadosSubst[i][0] = String.valueOf(s.getIdoc());
            dadosSubst[i][1] = String.valueOf(s.getOcorrencia().getInstantetempo());
            dadosSubst[i][2] = String.valueOf(s.getJogadorByIdjogadorentrou().getSelecao().getPais());
            dadosSubst[i][3] = jSaiu.getNcamisa() + " - " + jSaiu.getNome();
            dadosSubst[i][4] = jEntrou.getNcamisa() + " - " + jEntrou.getNome();
        }

        return dadosSubst;
    }
}
