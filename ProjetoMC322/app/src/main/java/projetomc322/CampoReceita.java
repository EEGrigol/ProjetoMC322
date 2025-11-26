package projetomc322;

import java.util.ArrayList;
import java.util.List;

/**
 * representa um campo de receita
 */
public class CampoReceita extends Campo {
    private List<Receita> ListaReceitas = new ArrayList<>();

    public CampoReceita(String nome){
        this.nome = nome;
    }

    /**
     * adiciona uma nova receita
     * @param receita receita a ser adicionada
     */
    public void AddReceita(Receita receita){
        ListaReceitas.add(receita);
    }

    /**
     * calcula o total de receitas mensais
     * @return total de receitas mensais
     */
    public float CalculaReceitaTotal(){
        float soma = 0;
        for (Receita receita : ListaReceitas){
            soma += receita.getValor() * receita.freq;
        }
        return soma;
    }

    /**
     * implementação unificada para obter o total do campo
     */
    @Override
    public float CalculaValorTotal(){
        return CalculaReceitaTotal();
    }

    /**
     * verifica se ja existe uma receita com o nome dado
     * @param nome nome da receita
     * @return true se existe, false caso contrario
     */
    protected boolean existeReceita(String nome){
        for (Receita receita : ListaReceitas){
            if(receita.getNome().equals(nome)) return true;
        }
        return false;
    }
}
