package projetomc322;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * representa uma receita
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Receita extends Movimentacao {
    private float valorMensalizado;

    /**
     * Construtor vazio para o JAXB
     */
    public Receita() {
        super();
    }

    public Receita(String nome, float valor, int freq){
        super(nome, valor, freq);
        this.valorMensalizado = this.valor*this.freq;
    }

    public float getValorMensalizado() {
        return valorMensalizado;
    }
}

