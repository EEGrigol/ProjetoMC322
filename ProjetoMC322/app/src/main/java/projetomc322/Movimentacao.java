package projetomc322;

public abstract class Movimentacao {
    protected String nome;
    protected float valor;
    protected int freq;

    public float getValor(){
        return valor;
    }

    abstract public float mensalizaValor();
}
