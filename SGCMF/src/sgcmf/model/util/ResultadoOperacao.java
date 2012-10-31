package sgcmf.model.util;

public class ResultadoOperacao
{
	private String msg;
	private TipoResultadoOperacao tipo;
	
	public ResultadoOperacao(String msg, TipoResultadoOperacao tipo)
	{
		this.msg = msg;
		this.tipo = tipo;
	}
	
	public String getMsg()
	{
		return msg;
	}

	public TipoResultadoOperacao getTipo()
	{
		return tipo;
	}
	
}
