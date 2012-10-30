package sgcmf.model.hibernate;
// Generated Oct 28, 2012 12:07:10 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ocorrencia generated by hbm2java
 */
public class Ocorrencia  implements java.io.Serializable {


     private short id;
     private Jogo jogo;
     private Date instantetempo;
     private Set cartaos = new HashSet(0);
     private Set faltas = new HashSet(0);
     private Set substituicaos = new HashSet(0);
     private Set jogadors = new HashSet(0);
     private Set gols = new HashSet(0);

    public Ocorrencia() {
    }

	
    public Ocorrencia(short id, Jogo jogo, Date instantetempo) {
        this.id = id;
        this.jogo = jogo;
        this.instantetempo = instantetempo;
    }
    public Ocorrencia(short id, Jogo jogo, Date instantetempo, Set cartaos, Set faltas, Set substituicaos, Set jogadors, Set gols) {
       this.id = id;
       this.jogo = jogo;
       this.instantetempo = instantetempo;
       this.cartaos = cartaos;
       this.faltas = faltas;
       this.substituicaos = substituicaos;
       this.jogadors = jogadors;
       this.gols = gols;
    }
   
    public short getId() {
        return this.id;
    }
    
    public void setId(short id) {
        this.id = id;
    }
    public Jogo getJogo() {
        return this.jogo;
    }
    
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
    public Date getInstantetempo() {
        return this.instantetempo;
    }
    
    public void setInstantetempo(Date instantetempo) {
        this.instantetempo = instantetempo;
    }
    public Set getCartaos() {
        return this.cartaos;
    }
    
    public void setCartaos(Set cartaos) {
        this.cartaos = cartaos;
    }
    public Set getFaltas() {
        return this.faltas;
    }
    
    public void setFaltas(Set faltas) {
        this.faltas = faltas;
    }
    public Set getSubstituicaos() {
        return this.substituicaos;
    }
    
    public void setSubstituicaos(Set substituicaos) {
        this.substituicaos = substituicaos;
    }
    public Set getJogadors() {
        return this.jogadors;
    }
    
    public void setJogadors(Set jogadors) {
        this.jogadors = jogadors;
    }
    public Set getGols() {
        return this.gols;
    }
    
    public void setGols(Set gols) {
        this.gols = gols;
    }




}

