
import java.text.DecimalFormat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Helio
 */
public class CriandoUsuarios
{
    public static void main(String[] args)
    {
        //8 Titulares
        //4 Reservas
        int incrementador = 0;
        int jogador = 1;
        int nCamisa = 1;
        int idSelecao = 1;
        String posicao = "Goleiro";
        boolean titular = true;
        float altura = (float) 1.86;
        String s = "";
        DecimalFormat df = new DecimalFormat("#.00");
        int ano;
        int mes;
        int dia;
        for (int i = 0; i < 416; i++)
        {
            altura = (float) 1.5 + (float) (Math.random() * 0.5);
            ano = 1960 + (int) (Math.random() * 30);
            mes = 1 + (int) (Math.random() * 12);
            dia = 1 + (int) (Math.random() * 28);
            if (incrementador < 12)
            {
                if (incrementador == 0)
                {
                    posicao = "Goleiro";
                    titular = true;
                }
                else if (incrementador < 2)
                {
                    posicao = "Zagueiro";
                    nCamisa++;
                    titular = true;
                }
                else if (incrementador < 4)
                {
                    posicao = "Lateral Esquerdo";
                    titular = true;
                    nCamisa++;
                }
                else if (incrementador < 6)
                {
                    posicao = "Lateral Direito";
                    titular = true;
                    nCamisa++;
                }
                else if (incrementador < 7)
                {
                    posicao = "Volante";
                    titular = true;
                    nCamisa++;
                }
                else if (incrementador < 8)
                {
                    posicao = "Atacante";
                    titular = true;
                    nCamisa++;
                }
                else if (incrementador < 10)
                {
                    posicao = "Goleiro";
                    titular = false;
                    nCamisa++;
                }
                else if (incrementador < 11)
                {
                    posicao = "Zagueiro";
                    titular = false;
                    nCamisa++;
                }
                else if (incrementador < 12)
                {
                    posicao = "Atacante";
                    titular = false;
                    nCamisa++;
                }
                s = "insert into Jogador (nome, altura, nCamisa, dataNasc, posicao, titular, idSelecao)"
                        + " values ('Jogador " + jogador + "', " + df.format(altura) + ", " + nCamisa + ", '" + ano + "-" + mes + "-" + dia + "'" + ", '"
                        + posicao + "', " + titular + ", " + idSelecao + ");";
                System.out.println(s);
                incrementador++;
                jogador++;

            }
            else
            {
                idSelecao++;
                nCamisa = 1;
                incrementador = 0;
            }
        }
    }
}
