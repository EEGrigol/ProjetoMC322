package projetomc322;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Gerenciador
 * Classe responsavel por gerenciar os campos de receita e despesa
 * e suas respectivas movimentacoes
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Gerenciador {
    private List<CampoDespesa> listaCampoDespesa = new ArrayList<>();
    private List<CampoReceita> listaCampoReceita = new ArrayList<>();
    private float valorLimiteGeral;

    @XmlTransient
    private FeatureAlarme alarme;

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
    public int addCampo(char tipo, String nome){
        if(tipo == 'r'){
            if(this.existeCampoReceita(nome)) return 1;
            listaCampoReceita.add(new CampoReceita(nome));
        }
        if( tipo == 'd'){
            if(this.existeCampoDespesa(nome)) return 1;
            listaCampoDespesa.add(new CampoDespesa(nome));
        }
        return 0;
    }

    /**
     * adiciona uma nova movimentacao de receita ou despesa
     * @param tipo 'r' para receita, 'd' para despesa
     * @param nomeCampo nome do campo onde a movimentacao sera adicionada
     * @param nomeMov nome da movimentacao
     * @param valor valor da movimentacao
     * @param freq frequencia da movimentacao
     * @return 0 se sucesso, 1 se ja existe movimentacao com esse nome
     */
    public int addMov(char tipo, String nomeCampo, String nomeMov, float valor, int freq){
        if(tipo == 'r'){
            Receita receita = new Receita(nomeMov, valor, freq);
            for(CampoReceita campoReceita : listaCampoReceita){
                if(campoReceita.getNome().equals(nomeCampo)){
                    if(campoReceita.existeReceita(nomeMov)) return 1;
                    campoReceita.addReceita(receita);
                    return 0;
                }
            }
        }
        if(tipo == 'd'){
            Despesa despesa = new Despesa(nomeMov, valor, freq);
            for(CampoDespesa campoDespesa : listaCampoDespesa){
                if(campoDespesa.getNome().equals(nomeCampo)){
                    if(campoDespesa.existeDespesa(nomeMov)) return 1;
                    campoDespesa.addDespesas(despesa);
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
    public float calculaReceitaTotal() {
        float soma = 0;
        for (CampoReceita campo : listaCampoReceita) {
            soma += campo.calculaValorTotal();
        }
        return soma;
    }

    /**
     * calcula o total de despesas mensais
     * @return total de despesas mensais
     */
    public float calculaDespesaTotal(){
        float soma = 0;
        for(CampoDespesa campoDespesa : listaCampoDespesa){
            soma += campoDespesa.calculaValorTotal();
        }
        return soma;
    }

    /**
     * calcula o saldo final (receitas - despesas)
     */
    public double getSaldoFinal() {
        return calculaReceitaTotal() - calculaDespesaTotal();
    }

    /**
     * verifica se ja existe um campo de receita com o nome dado
     * @param nome nome do campo
     * @return true se existe, false caso contrario
     */
    private boolean existeCampoReceita(String nome){
        for(CampoReceita campoReceita : listaCampoReceita){
            if(campoReceita.getNome().equals(nome)) 
                return true;
        }
        return false;
    }

    /**
     * verifica se ja existe um campo de despesa com o nome dado
     * @param nome nome do campo
     * @return true se existe, false caso contrario
     */
    private boolean existeCampoDespesa(String nome){
        for(CampoDespesa campoDespesa : listaCampoDespesa){
            if( campoDespesa.getNome().equals(nome)) 
                return true;
        }
        return false;
    }
}
