package projetomc322;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * representa uma despesa
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Despesa extends Movimentacao {
    private float valorMensalizado;

    public Despesa(String nome, float valor, int freq){
        super(nome, valor, freq);
        this.valorMensalizado = this.valor * this.freq;
    }
}

