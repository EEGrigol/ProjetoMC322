package projetomc322;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * representa uma movimentacao financeira
 */
@XmlSeeAlso({Receita.class, Despesa.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Movimentacao {
    protected String nome;
    protected float valor;
    protected int freq;

    /**
     * Construtor vazio para o JAXB
     */
    public Movimentacao() {

    }

    public Movimentacao(String nome, float valor, int freq) {
        this.nome = nome;
        this.valor = valor;
        this.freq = freq;
    }

    public float getValor(){
        return this.valor;
    }

    public String getNome(){
        return this.nome;
    }

    public int getFreq(){
        return this.freq;
    }

    public float getValorMensalizado(){
        return this.valor * this.freq;
    }
}
