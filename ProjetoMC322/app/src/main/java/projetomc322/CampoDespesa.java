package projetomc322;

import java.util.ArrayList;
import java.util.List;

/**
 * representa um campo de despesa
 */
public class CampoDespesa extends Campo {
    private List<Despesa> listaDespesas = new ArrayList<>();
    private float valorLimite;

    public CampoDespesa(String nome){
        this.nome = nome;
    }

    /**
     * define o valor limite do campo de despesa
     * @param limite valor limite a ser definido
     */
    public void setValorLimite(float limite){
        this.valorLimite = limite;
    }

    /**
     * adiciona uma nova despesa
     * @param despesa despesa a ser adicionada
     */
    public void addDespesas(Despesa despesa){
        listaDespesas.add(despesa);
    }

    /**
     * calcula o total de despesas mensais
     * @return total de despesas mensais
     */
    public float calculaDespesasTotal(){
        float soma = 0;
        for (Despesa despesa : listaDespesas){
            soma += despesa.getValor() * despesa.freq;
        }
        return soma;
    }

    /**
     * implementação unificada para obter o total do campo
     */
    @Override
    public float calculaValorTotal(){
        return calculaDespesasTotal();
    }

    /**
     * retorna o valor limite do campo de despesa
     * @return valor limite
     */
    public float getValorLimite(){
        return this.valorLimite;
    }

    /**
     * verifica se ja existe uma despesa com o nome dado
     * @param nome nome da despesa
     * @return true se existe, false caso contrario
     */
    protected boolean existeDespesa(String nome){
        for (Despesa despesa : listaDespesas){
            if(despesa.getNome().equals(nome)) return true;
        }
        return false;
    }
}
