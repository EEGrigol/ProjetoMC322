package projetomc322;

import java.util.ArrayList;
import java.util.List;

public class CampoReceita extends Campo {
    private List<Receita> ListaReceitas = new ArrayList<>();

    public CampoReceita(String nome){
        this.nome = nome;
    }

    public void AddReceita(Receita receita){
        ListaReceitas.add(receita);
    }

    public float CalulaReceitaTotal(){
        float soma = 0;
        for (Receita receita : ListaReceitas){
            soma += receita.getValor();
        }
        return soma;
    }
}
