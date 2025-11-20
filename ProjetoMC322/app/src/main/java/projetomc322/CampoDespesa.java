package projetomc322;

import java.util.ArrayList;
import java.util.List;

public class CampoDespesa extends Campo {
    private List<Despesa> ListaDespesas = new ArrayList<>();
    private float ValorLimite;

    public CampoDespesa(String nome){
        this.nome = nome;
    }

    public void setValorLimite(float limite){
        this.ValorLimite = limite;
    }

    public void AddDespesas(Despesa despesa){
        ListaDespesas.add(despesa);
    }

    public float CalulaDespesasTotal(){
        float soma = 0;
        for (Despesa despesa : ListaDespesas){
            soma += despesa.getValor();
        }
        return soma;
    }

    public float getValorLimite(){
        return this.ValorLimite;
    }
}
