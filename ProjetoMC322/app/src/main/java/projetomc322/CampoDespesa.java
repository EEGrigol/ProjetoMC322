package projetomc322;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CampoDespesa {
    public String nome; 
    private float valorLimite;
    
    @XmlElement(name = "despesa")
    private List<Despesa> listaDespesas = new ArrayList<>();
  
    public CampoDespesa() {} // Necessário para JAXB

    //Construtor
    public CampoDespesa(String nome){
        this.nome = nome;
    }

    //Setters e Getters
    public void setValorLimite(float limite){
        this.valorLimite = limite;
    }

    public float getValorLimite(){
        return this.valorLimite;
    }


    public List<Despesa> getListaDespesas() {
        if (listaDespesas == null) {
            listaDespesas = new ArrayList<>();
        }
        return listaDespesas;
    }

    // ------------ Métodos -------------------
    public void addDespesas(Despesa despesa){
        listaDespesas.add(despesa);
    }

    public void removerDespesa(Despesa despesa) {
        listaDespesas.remove(despesa);
    }

    public float calculaValorTotal(){
        float soma = 0;
        for (Despesa despesa : listaDespesas){
            soma += despesa.getValor() * despesa.getFreq();
        }
        return soma;
    }
}