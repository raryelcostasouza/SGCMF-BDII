/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.model.other;

/**
 *
 * @author Helio
 */
public class ModelRelatorioJogo
{
    private int gols;
    private int cartoesAmarelos;
    private int cartoesVermelhos;
    private int faltas;
    private String selecao;

    public ModelRelatorioJogo(int gols, int cartoesAmarelos, int cartoesVermelhos, int faltas, String selecao)
    {
        this.gols = gols;
        this.cartoesAmarelos = cartoesAmarelos;
        this.cartoesVermelhos = cartoesVermelhos;
        this.faltas = faltas;
        this.selecao = selecao;
    }

    public String getSelecao()
    {
        return selecao;
    }

    public int getGols()
    {
        return gols;
    }

    public int getCartoesAmarelos()
    {
        return cartoesAmarelos;
    }

    public int getCartoesVermelhos()
    {
        return cartoesVermelhos;
    }

    public int getFaltas()
    {
        return faltas;
    }
}
