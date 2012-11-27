package sgcmf.model.other;

import sgcmf.model.hibernate.Selecao;

public class ResultadoSelecao implements Comparable<ResultadoSelecao>
{
    private Selecao selecao;
    private int numPontos;
    private int saldoGols;
    private int numGolsMarcados;

    public ResultadoSelecao(Selecao selecao, int numPontos, int saldoGols, int numGolsMarcados)
    {
        this.selecao = selecao;
        this.numPontos = numPontos;
        this.saldoGols = saldoGols;
        this.numGolsMarcados = numGolsMarcados;
    }

    public Selecao getSelecao()
    {
        return selecao;
    }

    public int getNumPontos()
    {
        return numPontos;
    }

    public int getSaldoGols()
    {
        return saldoGols;
    }

    public int getNumGolsMarcados()
    {
        return numGolsMarcados;
    }

    @Override
    public int compareTo(ResultadoSelecao o)
    {
        if (getNumPontos() > o.getNumPontos())
        {
            return 1;
        }
        else if (getNumPontos() < o.getNumPontos())
        {
            return -1;
        }
        else 
        {
            //se tiver empatado no número de pontos
            // usa o saldo de gols como desempate
            if (getSaldoGols() > o.getSaldoGols())
            {
                return 1;
            }
            else if(getSaldoGols() < o.getSaldoGols())
            {
                return -1;
            }
            else
            {
                //se tiver empatado no saldo de gols
                //usa o numero de gols marcados como desempate
                if (getNumGolsMarcados() > o.getNumGolsMarcados())
                {
                    return 1;
                }
                else if(getNumGolsMarcados() < o.getNumGolsMarcados())
                {
                    return -1;
                }
                else
                {
                    //se permanecer empatado faz um sorteio
                    int randomicoBinario;
                    randomicoBinario = (int) Math.round(Math.random());
                    if (randomicoBinario == 1)
                    {
                        return 1;
                    }
                    else
                    {
                        return -1;
                    }
                }                    
            }
        }
    }

    @Override
    public String toString()
    {
        return selecao.getPais()+ ": " + getNumPontos() + " | " + getSaldoGols();
    }
}
