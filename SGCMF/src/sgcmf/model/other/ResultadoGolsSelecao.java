package sgcmf.model.other;

public class ResultadoGolsSelecao
{
    private int numGolsMarcados;
    private int saldoGols;
    private int numGolsSofridos;

    public ResultadoGolsSelecao(int numGolsMarcados, int numGolsSofridos, int saldoGols)
    {
        this.numGolsMarcados = numGolsMarcados;
        this.numGolsSofridos = numGolsSofridos;        
        this.saldoGols = saldoGols;        
    }

    public int getNumGolsMarcados()
    {
        return numGolsMarcados;
    }

    public int getNumGolsSofridos()
    {
        return numGolsSofridos;
    }

    public int getSaldoGols()
    {
        return saldoGols;
    }
}
