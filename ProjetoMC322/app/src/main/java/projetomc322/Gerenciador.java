package projetomc322;

import java.util.ArrayList;
import java.util.List;

/**
 * Gerenciador
 * Classe responsavel por gerenciar os campos de receita e despesa
 * e suas respectivas movimentacoes
 */
public class Gerenciador {
    private List<CampoDespesa> ListaCampoDespesa = new ArrayList<>();
    private List<CampoReceita> ListaCampoReceita = new ArrayList<>();
    private FeatureAlarme alarme;
    private float valorLimiteGeral;

    public Gerenciador(){
        alarme = new FeatureAlarme();
    };

    public float getValorLimiteGeral(){
        return this.valorLimiteGeral;
    }

    /**
     * adiciona um novo campo de receita ou despesa
     * @param tipo 'r' para receita, 'd' para despesa
     * @param nome nome do campo
     * @return 0 se sucesso, 1 se ja existe campo com esse nome
     */
    public int AddCampo(char tipo, String nome){
        if(tipo == 'r'){
            if(this.existeCampoReceita(nome)) return 1;
            ListaCampoReceita.add(new CampoReceita(nome));
        }
        if( tipo == 'd'){
            if(this.existeCampoDespesa(nome)) return 1;
            ListaCampoDespesa.add(new CampoDespesa(nome));
        }
        return 0;
    }

    /**
     * adiciona uma nova movimentacao de receita ou despesa
     * @param tipo 'r' para receita, 'd' para despesa
     * @param nomeCampo nome do campo onde a movimentacao sera adicionada
     * @param nomeMov nome da movimentacao
     * @param Valor valor da movimentacao
     * @param freq frequencia da movimentacao
     * @return 0 se sucesso, 1 se ja existe movimentacao com esse nome
     */
    public int AddMov(char tipo, String nomeCampo, String nomeMov, float Valor, int freq){
        if(tipo == 'r'){
            Receita receita = new Receita(nomeMov, Valor, freq);
            for(CampoReceita campoReceita : ListaCampoReceita){
                if(campoReceita.getNome().equals(nomeCampo)){
                    if(campoReceita.existeReceita(nomeMov)) return 1;
                    campoReceita.AddReceita(receita);
                    return 0;
                }
            }
        }
        if(tipo == 'd'){
            Despesa despesa = new Despesa(nomeMov, Valor, freq);
            for(CampoDespesa campoDespesa : ListaCampoDespesa){
                if(campoDespesa.getNome().equals(nomeCampo)){
                    if(campoDespesa.existeDespesa(nomeMov)) return 1;
                    campoDespesa.AddDespesas(despesa);
                    return 0;
                }
            }
        }
        return 0;
    }


    /**
     * calcula o total de receitas mensais
     * @return total de receitas mensais
     */
    public float CalulaDespesaTotal(){
        float soma = 0;
        for(CampoDespesa campoDespesa : ListaCampoDespesa){
            soma += campoDespesa.CalculaValorTotal();
        }
        return soma;
    }

    /**
     * verifica se ja existe um campo de receita com o nome dado
     * @param nome nome do campo
     * @return true se existe, false caso contrario
     */
    private boolean existeCampoReceita(String nome){
        for(CampoReceita campoReceita : ListaCampoReceita){
            if( campoReceita.getNome().equals(nome)) return true;
        }
        return false;
    }

    /**
     * verifica se ja existe um campo de despesa com o nome dado
     * @param nome nome do campo
     * @return true se existe, false caso contrario
     */
    private boolean existeCampoDespesa(String nome){
        for(CampoDespesa campoDespesa : ListaCampoDespesa){
            if( campoDespesa.getNome().equals(nome)) return true;
        }
        return false;
    }
}
