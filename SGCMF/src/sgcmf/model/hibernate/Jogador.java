package sgcmf.model.hibernate;
// Generated Oct 30, 2012 8:06:49 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Jogador generated by hbm2java
 */
public class Jogador  implements java.io.Serializable {


     private short id;
     private Selecao selecao;
     private String nome;
     private BigDecimal altura;
     private short ncamisa;
     private Date datanasc;
     private String posicao;
     private boolean titular;
     private Set golsForIdjogadorassistencia = new HashSet(0);
     private Set substituicaosForIdjogadorentrou = new HashSet(0);
     private Set golsForIdjogadorautor = new HashSet(0);
     private Set substituicaosForIdjogadorsaiu = new HashSet(0);
     private Set faltas = new HashSet(0);
     private Set cartaos = new HashSet(0);
     private Set rodadadepenaltisesForIdjogadorcobrancai = new HashSet(0);
     private Set rodadadepenaltisesForIdjogadorcobrancaii = new HashSet(0);

    public Jogador() {
    }

	
    public Jogador(short id, Selecao selecao, String nome, BigDecimal altura, short ncamisa, Date datanasc, String posicao, boolean titular) {
        this.id = id;
        this.selecao = selecao;
        this.nome = nome;
        this.altura = altura;
        this.ncamisa = ncamisa;
        this.datanasc = datanasc;
        this.posicao = posicao;
        this.titular = titular;
    }
    public Jogador(short id, Selecao selecao, String nome, BigDecimal altura, short ncamisa, Date datanasc, String posicao, boolean titular, Set golsForIdjogadorassistencia, Set substituicaosForIdjogadorentrou, Set golsForIdjogadorautor, Set substituicaosForIdjogadorsaiu, Set faltas, Set cartaos, Set rodadadepenaltisesForIdjogadorcobrancai, Set rodadadepenaltisesForIdjogadorcobrancaii) {
       this.id = id;
       this.selecao = selecao;
       this.nome = nome;
       this.altura = altura;
       this.ncamisa = ncamisa;
       this.datanasc = datanasc;
       this.posicao = posicao;
       this.titular = titular;
       this.golsForIdjogadorassistencia = golsForIdjogadorassistencia;
       this.substituicaosForIdjogadorentrou = substituicaosForIdjogadorentrou;
       this.golsForIdjogadorautor = golsForIdjogadorautor;
       this.substituicaosForIdjogadorsaiu = substituicaosForIdjogadorsaiu;
       this.faltas = faltas;
       this.cartaos = cartaos;
       this.rodadadepenaltisesForIdjogadorcobrancai = rodadadepenaltisesForIdjogadorcobrancai;
       this.rodadadepenaltisesForIdjogadorcobrancaii = rodadadepenaltisesForIdjogadorcobrancaii;
    }
   
    public short getId() {
        return this.id;
    }
    
    public void setId(short id) {
        this.id = id;
    }
    public Selecao getSelecao() {
        return this.selecao;
    }
    
    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public BigDecimal getAltura() {
        return this.altura;
    }
    
    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }
    public short getNcamisa() {
        return this.ncamisa;
    }
    
    public void setNcamisa(short ncamisa) {
        this.ncamisa = ncamisa;
    }
    public Date getDatanasc() {
        return this.datanasc;
    }
    
    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }
    public String getPosicao() {
        return this.posicao;
    }
    
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
    public boolean isTitular() {
        return this.titular;
    }
    
    public void setTitular(boolean titular) {
        this.titular = titular;
    }
    public Set getGolsForIdjogadorassistencia() {
        return this.golsForIdjogadorassistencia;
    }
    
    public void setGolsForIdjogadorassistencia(Set golsForIdjogadorassistencia) {
        this.golsForIdjogadorassistencia = golsForIdjogadorassistencia;
    }
    public Set getSubstituicaosForIdjogadorentrou() {
        return this.substituicaosForIdjogadorentrou;
    }
    
    public void setSubstituicaosForIdjogadorentrou(Set substituicaosForIdjogadorentrou) {
        this.substituicaosForIdjogadorentrou = substituicaosForIdjogadorentrou;
    }
    public Set getGolsForIdjogadorautor() {
        return this.golsForIdjogadorautor;
    }
    
    public void setGolsForIdjogadorautor(Set golsForIdjogadorautor) {
        this.golsForIdjogadorautor = golsForIdjogadorautor;
    }
    public Set getSubstituicaosForIdjogadorsaiu() {
        return this.substituicaosForIdjogadorsaiu;
    }
    
    public void setSubstituicaosForIdjogadorsaiu(Set substituicaosForIdjogadorsaiu) {
        this.substituicaosForIdjogadorsaiu = substituicaosForIdjogadorsaiu;
    }
    public Set getFaltas() {
        return this.faltas;
    }
    
    public void setFaltas(Set faltas) {
        this.faltas = faltas;
    }
    public Set getCartaos() {
        return this.cartaos;
    }
    
    public void setCartaos(Set cartaos) {
        this.cartaos = cartaos;
    }
    public Set getRodadadepenaltisesForIdjogadorcobrancai() {
        return this.rodadadepenaltisesForIdjogadorcobrancai;
    }
    
    public void setRodadadepenaltisesForIdjogadorcobrancai(Set rodadadepenaltisesForIdjogadorcobrancai) {
        this.rodadadepenaltisesForIdjogadorcobrancai = rodadadepenaltisesForIdjogadorcobrancai;
    }
    public Set getRodadadepenaltisesForIdjogadorcobrancaii() {
        return this.rodadadepenaltisesForIdjogadorcobrancaii;
    }
    
    public void setRodadadepenaltisesForIdjogadorcobrancaii(Set rodadadepenaltisesForIdjogadorcobrancaii) {
        this.rodadadepenaltisesForIdjogadorcobrancaii = rodadadepenaltisesForIdjogadorcobrancaii;
    }




}


