package projetomc322;

public class FeatureAlarme implements Alarme {
    private boolean AlarmeLigado;

    public FeatureAlarme(){
        AlarmeLigado = false;
    }

    public boolean checkLimiteCampoDespesa(CampoDespesa campoDespesa){
        float valorTotal = campoDespesa.CalulaValorTotal;

        if(valorTotal > campoDespesa.getValorLimite()) return false;
        return true;
    };

    public boolean checkLimiteTotal(Gerenciador gerenciador){
        if(gerenciador.CalulaDespesaTotal() > gerenciador.getValorLimiteGeral()) return false;
        return true;
    }


    }
