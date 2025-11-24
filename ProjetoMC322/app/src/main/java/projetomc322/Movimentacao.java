package projetomc322;

/**
 * representa uma movimentacao financeira
 */
public abstract class Movimentacao {
    protected String nome;
    protected float valor;
    protected int freq;

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
