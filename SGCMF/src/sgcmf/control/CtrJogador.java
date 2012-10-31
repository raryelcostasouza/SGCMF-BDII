package sgcmf.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Transaction;
import sgcmf.model.dao.GeneralDAO;
import sgcmf.model.dao.JogadorDAO;
import sgcmf.model.hibernate.Jogador;
import sgcmf.model.hibernate.Selecao;
import sgcmf.model.util.ResultadoOperacao;

public class CtrJogador
{
	public Jogador carregaJogadorById(Short idJogador)
	{
		Jogador jogador;
		JogadorDAO jdao;
		
		jogador = new Jogador();
		jdao = new JogadorDAO();
		jdao.carregar(jogador, idJogador);
		jdao.fecharSessao();
		
		return jogador;
	}
	
	public String[][] queryJogadorTodos()
	{
		GeneralDAO<Jogador> gdao;
		String[][] dadosJogadores;
		ArrayList alJogador;
		
		gdao = new GeneralDAO<Jogador>();
		alJogador = gdao.listaTodos("Jogador");
		dadosJogadores = arrayList2StringMatrix(alJogador);
		gdao.fecharSessao();
		
		return dadosJogadores;
	}
	
	public String[][] queryJogadorByNome(String nome)
	{
		JogadorDAO jDAO;
		String[][] dadosJogador;
		ArrayList<Jogador> alJogador;
		
		jDAO = new JogadorDAO();
		alJogador = jDAO.queryJogadorByNome(nome);
		dadosJogador = arrayList2StringMatrix(alJogador);
		jDAO.fecharSessao();
		
		return dadosJogador;
	}
	
	private String[][] arrayList2StringMatrix(ArrayList<Jogador> alJogador)
	{
		String[][] dadosJogadores;
		Jogador j;
		
		dadosJogadores = new String[alJogador.size()][5];
		for (int i = 0; i < alJogador.size(); i++)
		{
			j = alJogador.get(i);
			dadosJogadores[i][0] = String.valueOf(j.getId());
			dadosJogadores[i][1] = j.getSelecao().getPais();
			dadosJogadores[i][2] = String.valueOf(j.getNcamisa());
			dadosJogadores[i][3] = j.getNome();
			dadosJogadores[i][4] = j.getPosicao();
		}
		
		return dadosJogadores;
	}
	
	public ResultadoOperacao cadastrarJogador(String numCamisa, String nome, String dataNascimento,
            String altura, boolean titular, String posicao, String selecao)
    {
        Short nCamisa;
        Date dtaNascimento;
        BigDecimal aAltura;
        Short aSelecao;
        Transaction tr;
        Selecao s = new Selecao();
        ResultadoOperacao result = null;
        String errorMessege;
		GeneralDAO gdao;
        
		gdao = new GeneralDAO();
        errorMessege = validaCampos(numCamisa, dataNascimento, altura, selecao);
        if (errorMessege.equals(""))
        {
            nCamisa = Short.parseShort(numCamisa);
            dtaNascimento = new Date(dataNascimento);
            aAltura = new BigDecimal(altura);
            aSelecao = Short.parseShort(selecao);
            tr = gdao.getSessao().beginTransaction();
            
			gdao.fecharSessao();
        }
        return result;
    }
    
    public String validaCampos(String numCamisa, String dataNascimento, String altura, String selecao)
    {
        String errorMessege = "";
        Short camisa;
        Date date;
        BigDecimal aAltura;
        //Verificar se o numero da camisa esta valido.
        try
        {
            camisa = Short.parseShort(numCamisa);
            if (camisa.intValue() < 1 && camisa.intValue() > 24)
            {
                errorMessege = "O valor da camisa deve estar entre 1 e 23.";
                return errorMessege;
            }
        }
        catch (Exception e)
        {
            errorMessege = "Digite um número valido para camisa.";
        }
        if (errorMessege.equals(""))
        {
            try
            {
                date = new Date(dataNascimento);
            }
            catch (IllegalArgumentException ilae)
            {
                errorMessege = "Formato de data é inválido. Deve seguir o padrão AAAA-MM-DD.";
            }
        }
        if (errorMessege.equals(""))
        {
            try
            {
                aAltura = new BigDecimal(altura);
            }
            catch (NumberFormatException nfe)
            {
                errorMessege = "Digite um número valido para altura, usando \".\""
                        + " para separar o metro de centímetro.";
            }
        }
        if (errorMessege.equals(""))
        {
            if (selecao.equals(""))
            {
                errorMessege = "O campo de seleção é obrigatório.";
            }
        }
        return errorMessege;
    }
}
