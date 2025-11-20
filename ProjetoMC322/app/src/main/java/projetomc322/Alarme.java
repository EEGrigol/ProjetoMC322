package projetomc322;

public interface Alarme {
    abstract public boolean checkLimiteCampoDespesa(CampoDespesa campoDespesa);

    abstract public boolean checkLimiteTotal(Gerenciador gerenciador);
}
