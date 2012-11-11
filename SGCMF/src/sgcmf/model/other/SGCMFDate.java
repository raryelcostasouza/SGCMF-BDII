package sgcmf.model.other;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SGCMFDate
{
    public static String toStringDataHoraFormatoBrasil(Date d)
    {
        String str;
        String strDia;
        String strMes;
        String strHora;
        String strMinuto;
        int dia;
        int mes;
        int hora;
        int minuto;
        GregorianCalendar gc;
        
        gc  = new GregorianCalendar();
        gc.setTime(d);
        
        dia = gc.get(Calendar.DAY_OF_MONTH);
        mes = gc.get(Calendar.MONTH) +1;
        hora = gc.get(Calendar.HOUR_OF_DAY);
        minuto = gc.get(Calendar.MINUTE);
        
        strDia = addZeroOnLeftIfNecessary(dia);
        strMes = addZeroOnLeftIfNecessary(mes);
        strHora = addZeroOnLeftIfNecessary(hora);
        strMinuto = addZeroOnLeftIfNecessary(minuto);
        
        str ="";
        str += strDia + "/";
        
        str += strMes + "/";
        
        
        str += gc.get(Calendar.YEAR) + " - ";
        str += strHora + ":";
        str += strMinuto;
        
        return str;       
    }
    
    private static String addZeroOnLeftIfNecessary(int i)
    {
        String str;
        
        if (i>=10)
        {
            str = String.valueOf(i);
        }
        else
        {
            str = "0" + i;
        }
        
        return str;
    }
}
