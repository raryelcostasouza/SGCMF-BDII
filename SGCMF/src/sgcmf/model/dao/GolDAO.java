package sgcmf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import sgcmf.hibernate.SGCMFSessionManager;
import sgcmf.model.hibernate.Gol;
import sgcmf.model.hibernate.Ocorrencia;

public class GolDAO
{
    private static GolDAO instance;

    private GolDAO()
    {
        
    }
    
    public static GolDAO getInstance()
    {
        if (instance == null)
        {
            instance = new GolDAO();
        }
        return instance;
    }

    public void salvar(Gol entidade)
    {
        SGCMFSessionManager.getCurrentSession().save(entidade);
    }

    public Gol carregar(Gol entidade, Serializable id)
    {
        SGCMFSessionManager.getCurrentSession().load(entidade, id);
        return entidade;
    }

    public void apagar(Gol entidade)
    {
        SGCMFSessionManager.getCurrentSession().delete(entidade);
    }

    public ArrayList<Gol> queryGolByIdJogo(Short idJogo)
    {
        String hql;

        hql = "from Gol g "
                + "where g.ocorrencia.jogo.id = " + idJogo + " order by g.ocorrencia.instantetempo";

        return (ArrayList<Gol>) SGCMFSessionManager.getCurrentSession().createQuery(hql).list();
    }
    
    public int queryNumGolsJogoSelecao(Short idJogo, Short idSelecao, Short idSelecaoRival)
    {
        String hql;
        
        hql = "select count(g.idoc) "
                + "from Gol g "
                + "where g.ocorrencia.jogo.id = " + idJogo + " "
                //gols a favor da selecaoI
                + "and ((g.tipo = 'A Favor' and g.jogadorByIdjogadorautor.selecao.id = " + idSelecao+") "
                //+ gols contra da selecao II
                + "or (g.tipo = 'Contra' and g.jogadorByIdjogadorautor.selecao.id = "+ idSelecaoRival  +")"
                + ")";
        return Integer.parseInt(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult()+"");
    }
    
    public int queryNumGolsMarcadosSelecao(Short idSelecao)
    {
        String hql;
        
        hql = "select count (g.idoc) "
                + "from Gol g "
                + "where g.tipo = 'A Favor' and g.jogadorByIdjogadorautor.selecao.id = " + idSelecao;
        
        return Integer.parseInt(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult()+"");
    }
    
    public int queryNumGolsSofridosSelecaoJogo(Short idJogo, Short idSelecao, Short idSelecaoRival)
    {
        String hql;
        
        hql = "select count (g.idoc) "
                + "from Gol g "
                + "where g.ocorrencia.jogo.id = "+ idJogo +" "
                + "and ("
                + "(g.tipo = 'Contra' and g.jogadorByIdjogadorautor.selecao.id = " + idSelecao+") "
                + "or (g.tipo = 'A Favor' and g.jogadorByIdjogadorautor.selecao.id = " + idSelecaoRival + ")"
                + ")";
        
        return Integer.parseInt(SGCMFSessionManager.getCurrentSession().createQuery(hql).uniqueResult()+"");
    }

    public int queryQtdeGolsSofridos(Short idSelecao)
    {
        //Descobrir todas as selecoes que enfrentam a selecao com idSelecao
        
        //Retorna 2 colunas com os id's das selecoes
        //select j.idSelecaoi, j.idSelecaoii 
        //from jogo j join selecao s on (j.idSelecaoi = s.id or j.idSelecaoii = s.id) 
        //where s.id=idSelecao
        
        //Ver todos os gols contra que o idSelecao fez
        //"select count(g.idoc) 
        //from Gol g 
        //where g.tipo = 'Contra' and g.jogadorByIdjogadorAutor.selecao.id = "+idSelecao;
        
        //ver todos os gols a favor que as outras selecoes fizeram contra idSelecao.
        //ideia... fore(array(do primeiro select)) com a seguinte condicao...
        //se(idselecao != array[i]) qtde golssofridos = qtdegolssofridos+ queryNumGolsMarcadosselecao 
        //(idSelecao[i] do array)
        String hql;
        hql = "select coung (g.idoc) from Gol g where g.tipo = 'Contra' and g.";
        return 0;
    }
}
