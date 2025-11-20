package projetomc322;

import java.util.ArrayList;
import java.util.List;

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

    public void AddCampo(char tipo, String nome){
        if(tipo == 'r'){
            ListaCampoReceita.add(new CampoReceita(nome));
        }
        if( tipo == 'd'){
            ListaCampoDespesa.add(new CampoDespesa(nome));
        }
    }

    public void AddMov(char tipo, String nomeCampo, String nomeMov, float Valor, int freq){
        if(tipo == 'r'){
            Receita receita = new Receita(nomeMov, Valor, freq);
            for(CampoReceita campoReceita : ListaCampoReceita){
                if(campoReceita.getNome() == nomeCampo){
                    campoReceita.AddReceita(receita);
                }
            }
        }
        if(tipo == 'd'){
            Despesa despesa = new Despesa(nomeMov, Valor, freq);
            for(CampoDespesa campoDespesa : ListaCampoDespesa){
                if(campoDespesa.getNome() == nomeCampo){
                    campoDespesa.AddDespesas(despesa);
                }
            }
        }
    }

    public float CalulaDespesaTotal(){
        float soma = 0;
        for(CampoDespesa campoDespesa : ListaCampoDespesa){
            soma += campoDespesa.CalulaValorTotal;
        }
        return soma;
    }
}
