package sgcmf.model.hibernate;
// Generated 17/11/2012 16:51:03 by Hibernate Tools 3.2.1.GA



/**
 * Gol generated by hbm2java
 */
public class Gol  implements java.io.Serializable {


     private short idoc;
     private Jogador jogadorByIdjogadorautor;
     private Ocorrencia ocorrencia;
     private Jogador jogadorByIdjogadorassistencia;
     private String tipo;
     private String modo;

    public Gol() {
    }

	
    public Gol(short idoc, Jogador jogadorByIdjogadorautor, Ocorrencia ocorrencia, String tipo, String modo) {
        this.idoc = idoc;
        this.jogadorByIdjogadorautor = jogadorByIdjogadorautor;
        this.ocorrencia = ocorrencia;
        this.tipo = tipo;
        this.modo = modo;
    }
    public Gol(short idoc, Jogador jogadorByIdjogadorautor, Ocorrencia ocorrencia, Jogador jogadorByIdjogadorassistencia, String tipo, String modo) {
       this.idoc = idoc;
       this.jogadorByIdjogadorautor = jogadorByIdjogadorautor;
       this.ocorrencia = ocorrencia;
       this.jogadorByIdjogadorassistencia = jogadorByIdjogadorassistencia;
       this.tipo = tipo;
       this.modo = modo;
    }
   
    public short getIdoc() {
        return this.idoc;
    }
    
    public void setIdoc(short idoc) {
        this.idoc = idoc;
    }
    public Jogador getJogadorByIdjogadorautor() {
        return this.jogadorByIdjogadorautor;
    }
    
    public void setJogadorByIdjogadorautor(Jogador jogadorByIdjogadorautor) {
        this.jogadorByIdjogadorautor = jogadorByIdjogadorautor;
    }
    public Ocorrencia getOcorrencia() {
        return this.ocorrencia;
    }
    
    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }
    public Jogador getJogadorByIdjogadorassistencia() {
        return this.jogadorByIdjogadorassistencia;
    }
    
    public void setJogadorByIdjogadorassistencia(Jogador jogadorByIdjogadorassistencia) {
        this.jogadorByIdjogadorassistencia = jogadorByIdjogadorassistencia;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getModo() {
        return this.modo;
    }
    
    public void setModo(String modo) {
        this.modo = modo;
    }




}


