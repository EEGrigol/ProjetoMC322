package projetomc322;

/**
 * representa uma despesa
 */
public class Despesa extends Movimentacao {
    private float valorMensalizado;

    public Despesa(String nome, float valor, int freq){
        this.nome = nome;
        this.valor = valor;
        this.freq = freq;
        this.valorMensalizado = this.valor * this.freq;
    }
}

