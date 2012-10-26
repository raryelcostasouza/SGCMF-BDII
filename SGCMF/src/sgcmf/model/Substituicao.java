package sgcmf.model;
// Generated Oct 26, 2012 6:12:10 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;

/**
 * Substituicao generated by hbm2java
 */
public class Substituicao  implements java.io.Serializable {


     private short idoc;
     private Jogador jogadorByIdjogadorentrou;
     private Jogador jogadorByIdjogadorsaiu;
     private Ocorrencia ocorrencia;
     private Serializable motivo;

    public Substituicao() {
    }

    public Substituicao(short idoc, Jogador jogadorByIdjogadorentrou, Jogador jogadorByIdjogadorsaiu, Ocorrencia ocorrencia, Serializable motivo) {
       this.idoc = idoc;
       this.jogadorByIdjogadorentrou = jogadorByIdjogadorentrou;
       this.jogadorByIdjogadorsaiu = jogadorByIdjogadorsaiu;
       this.ocorrencia = ocorrencia;
       this.motivo = motivo;
    }
   
    public short getIdoc() {
        return this.idoc;
    }
    
    public void setIdoc(short idoc) {
        this.idoc = idoc;
    }
    public Jogador getJogadorByIdjogadorentrou() {
        return this.jogadorByIdjogadorentrou;
    }
    
    public void setJogadorByIdjogadorentrou(Jogador jogadorByIdjogadorentrou) {
        this.jogadorByIdjogadorentrou = jogadorByIdjogadorentrou;
    }
    public Jogador getJogadorByIdjogadorsaiu() {
        return this.jogadorByIdjogadorsaiu;
    }
    
    public void setJogadorByIdjogadorsaiu(Jogador jogadorByIdjogadorsaiu) {
        this.jogadorByIdjogadorsaiu = jogadorByIdjogadorsaiu;
    }
    public Ocorrencia getOcorrencia() {
        return this.ocorrencia;
    }
    
    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }
    public Serializable getMotivo() {
        return this.motivo;
    }
    
    public void setMotivo(Serializable motivo) {
        this.motivo = motivo;
    }




}


