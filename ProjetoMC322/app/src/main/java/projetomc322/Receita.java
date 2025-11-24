package projetomc322;

/**
 * representa uma receita
 */
public class Receita extends Movimentacao {
    private float valorMensalizado;

    public Receita(String nome, float valor, int freq){
        this.nome = nome;
        this.valor = valor;
        this.freq = freq;
        this.valorMensalizado = this.valor*this.freq;
    }
}

