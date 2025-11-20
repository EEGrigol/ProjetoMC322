package projetomc322;

public class Despesa extends Movimentacao {
    private float valorMensalizado;

    public Despesa(String nome, float valor, int freq){
        this.nome = nome;
        this.valor = valor;
        this.freq = freq;
        this.valorMensalizado = this.mensalizaValor();
    }

    public float mensalizaValor(){
        return (this.valor*this.freq)/30;
    }
}
