package sgcmf.model.hibernate;
// Generated 23/11/2012 22:56:22 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Selecao generated by hbm2java
 */
public class Selecao  implements java.io.Serializable {


     private short id;
     private Usuario usuario;
     private String pais;
     private String caminhoimgbandeira;
     private char grupo;
     private Set jogosForIdselecaoii = new HashSet(0);
     private Set jogosForIdselecaoi = new HashSet(0);
     private Set jogadors = new HashSet(0);

    public Selecao() {
    }

	
    public Selecao(short id, Usuario usuario, String pais, String caminhoimgbandeira, char grupo) {
        this.id = id;
        this.usuario = usuario;
        this.pais = pais;
        this.caminhoimgbandeira = caminhoimgbandeira;
        this.grupo = grupo;
    }
    public Selecao(short id, Usuario usuario, String pais, String caminhoimgbandeira, char grupo, Set jogosForIdselecaoii, Set jogosForIdselecaoi, Set jogadors) {
       this.id = id;
       this.usuario = usuario;
       this.pais = pais;
       this.caminhoimgbandeira = caminhoimgbandeira;
       this.grupo = grupo;
       this.jogosForIdselecaoii = jogosForIdselecaoii;
       this.jogosForIdselecaoi = jogosForIdselecaoi;
       this.jogadors = jogadors;
    }
   
    public short getId() {
        return this.id;
    }
    
    public void setId(short id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getCaminhoimgbandeira() {
        return this.caminhoimgbandeira;
    }
    
    public void setCaminhoimgbandeira(String caminhoimgbandeira) {
        this.caminhoimgbandeira = caminhoimgbandeira;
    }
    public char getGrupo() {
        return this.grupo;
    }
    
    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }
    public Set getJogosForIdselecaoii() {
        return this.jogosForIdselecaoii;
    }
    
    public void setJogosForIdselecaoii(Set jogosForIdselecaoii) {
        this.jogosForIdselecaoii = jogosForIdselecaoii;
    }
    public Set getJogosForIdselecaoi() {
        return this.jogosForIdselecaoi;
    }
    
    public void setJogosForIdselecaoi(Set jogosForIdselecaoi) {
        this.jogosForIdselecaoi = jogosForIdselecaoi;
    }
    public Set getJogadors() {
        return this.jogadors;
    }
    
    public void setJogadors(Set jogadors) {
        this.jogadors = jogadors;
    }




}


