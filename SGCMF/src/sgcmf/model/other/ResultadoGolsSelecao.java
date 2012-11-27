package sgcmf.model.other;

public class ResultadoGolsSelecao
{
    private int numGolsMarcados;
    private int saldoGols;

    public ResultadoGolsSelecao(int numGolsMarcados, int saldoGols)
    {
        this.numGolsMarcados = numGolsMarcados;
        this.saldoGols = saldoGols;
    }

    public int getNumGolsMarcados()
    {
        return numGolsMarcados;
    }

    public int getSaldoGols()
    {
        return saldoGols;
    }
}
