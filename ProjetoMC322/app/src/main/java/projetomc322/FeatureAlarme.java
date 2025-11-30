package projetomc322;

public class FeatureAlarme {
    private boolean alarmeLigado = false;

    public void checkLimiteCampoDespesa(CampoDespesa campoDespesa){
        float valorTotal = campoDespesa.calculaValorTotal();
        // Verifica se tem limite (>0) e se estourou
        if(campoDespesa.getValorLimite() > 0 && valorTotal > campoDespesa.getValorLimite()) {
            this.alarmeLigado = true;
        } else {
            this.alarmeLigado = false;
        }
    }

    public boolean isAlarmeLigado() {
        return this.alarmeLigado;
    }
}