package projetomc322;

import java.util.ArrayList;
import java.util.List;

/**
 * representa um campo de despesa
 */
public class CampoDespesa extends Campo {
    private List<Despesa> ListaDespesas = new ArrayList<>();
    private float ValorLimite;

    public CampoDespesa(String nome){
        this.nome = nome;
    }

    /**
     * define o valor limite do campo de despesa
     * @param limite valor limite a ser definido
     */
    public void setValorLimite(float limite){
        this.ValorLimite = limite;
    }

    /**
     * adiciona uma nova despesa
     * @param despesa despesa a ser adicionada
     */
    public void AddDespesas(Despesa despesa){
        ListaDespesas.add(despesa);
    }

    /**
     * calcula o total de despesas mensais
     * @return total de despesas mensais
     */
    public float CalculaDespesasTotal(){
        float soma = 0;
        for (Despesa despesa : ListaDespesas){
            soma += despesa.getValor() * despesa.freq;
        }
        return soma;
    }

    /**
     * implementação unificada para obter o total do campo
     */
    @Override
    public float CalculaValorTotal(){
        return CalculaDespesasTotal();
    }

    /**
     * retorna o valor limite do campo de despesa
     * @return valor limite
     */
    public float getValorLimite(){
        return this.ValorLimite;
    }

    /**
     * verifica se ja existe uma despesa com o nome dado
     * @param nome nome da despesa
     * @return true se existe, false caso contrario
     */
    protected boolean existeDespesa(String nome){
        for (Despesa despesa : ListaDespesas){
            if(despesa.getNome().equals(nome)) return true;
        }
        return false;
    }
}
