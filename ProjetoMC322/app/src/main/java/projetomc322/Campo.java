package projetomc322;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * representa um campo financeiro
 */
@XmlSeeAlso({CampoDespesa.class, CampoReceita.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Campo {
    protected String nome;

    /**
     * subclasses devem implementar cálculo do valor total do campo (mensalizado)
     */
    public abstract float calculaValorTotal();

    /**
     * retorna o nome do campo
     * @return nome do campo
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * retorna o valor total do campo (delegando ao cálculo)
     * @return valor total do campo
     */
    public float getValorTotal(){
        return calculaValorTotal();
    }

}
