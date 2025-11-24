package projetomc322;

/**
 * representa um alarme para verificacao de limites
 */
public class FeatureAlarme implements Alarme {
    private boolean AlarmeLigado;

    public FeatureAlarme(){
        AlarmeLigado = false;
    }

    /**
     * verifica o limite do campo de despesa
     * @param campoDespesa campo de despesa a ser verificado
     * liga o alarme se o valor total ultrapassar o limite
     */
    public void checkLimiteCampoDespesa(CampoDespesa campoDespesa){
        float valorTotal = campoDespesa.CalulaValorTotal();
        if(valorTotal > campoDespesa.getValorLimite()) this.AlarmeLigado = true;
        else this.AlarmeLigado = false;
    };

    /**
     * verifica o limite total do gerenciador
     * @param gerenciador gerenciador a ser verificado
     * liga o alarme se o valor total de despesas ultrapassar o limite geral
     */
    public void checkLimiteTotal(Gerenciador gerenciador){
        if(gerenciador.CalulaDespesaTotal() > gerenciador.getValorLimiteGeral()) this.AlarmeLigado = true;
        else this.AlarmeLigado = false;
    }

}
