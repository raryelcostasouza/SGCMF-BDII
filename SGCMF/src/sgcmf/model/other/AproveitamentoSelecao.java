/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.model.other;

/**
 *
 * @author Helio
 */
public class AproveitamentoSelecao
{
    private int jogosDisputados;
    private int vitorias;
    private int derrotas;
    private int empates;
    private float aproveitamento;

    public AproveitamentoSelecao(int jogosDisputados, int vitorias, int derrotas, int empates, float aproveitamento)
    {
        this.jogosDisputados = jogosDisputados;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.aproveitamento = aproveitamento;
    }

    public int getJogosDisputados()
    {
        return jogosDisputados;
    }

    public int getVitorias()
    {
        return vitorias;
    }

    public int getDerrotas()
    {
        return derrotas;
    }

    public int getEmpates()
    {
        return empates;
    }

    public float getAproveitamento()
    {
        return aproveitamento;
    }
}
