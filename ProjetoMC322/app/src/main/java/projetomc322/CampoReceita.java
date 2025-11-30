package projetomc322;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CampoReceita extends Campo {
    public String nome;
    public CampoReceita() {}


    @XmlElement(name = "receita")
    private List<Receita> listaReceitas = new ArrayList<>();

    //Construtor
    public CampoReceita(String nome){
        this.nome = nome;
    }

    //Getter
    public List<Receita> getListaReceitas() {
        if (listaReceitas == null) {
            listaReceitas = new ArrayList<>();
        }
        return listaReceitas;
    }

    //------------ MÉTODOS --------------
    public void addReceita(Receita receita){
        listaReceitas.add(receita);
    }

    public void removerReceita(Receita receita) {
        listaReceitas.remove(receita);
    }

    public float calculaValorTotal(){
        float soma = 0;
        for (Receita receita : listaReceitas){
            soma += receita.getValor() * receita.getFreq();
        }
        return soma;
    }
}