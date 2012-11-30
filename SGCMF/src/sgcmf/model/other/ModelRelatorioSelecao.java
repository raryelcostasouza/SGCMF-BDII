/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.model.other;

/**
 *
 * @author Helio
 */
public class ModelRelatorioSelecao
{
    private String nomeSelecao;
    private AproveitamentoSelecao objAproveitamentoSelecao;
    private int qtdeFaltas;
    private int qtdeCartoes;
    private ResultadoGolsSelecao rgs;

    public ModelRelatorioSelecao(String nomeSelecaoI, AproveitamentoSelecao objAproveitamentoSelecaoI,
            int qtdeFaltasI, int qtdeCartoesI, ResultadoGolsSelecao rgsI)
    {
        this.nomeSelecao = nomeSelecaoI;
        this.objAproveitamentoSelecao = objAproveitamentoSelecaoI;
        this.qtdeFaltas = qtdeFaltasI;
        this.qtdeCartoes = qtdeCartoesI;
        this.rgs = rgsI;
    }

    public String getNomeSelecao()
    {
        return nomeSelecao;
    }

    public AproveitamentoSelecao getObjAproveitamentoSelecao()
    {
        return objAproveitamentoSelecao;
    }

    public int getQtdeFaltas()
    {
        return qtdeFaltas;
    }

    public int getQtdeCartoes()
    {
        return qtdeCartoes;
    }

    public ResultadoGolsSelecao getRgs()
    {
        return rgs;
    }
}
