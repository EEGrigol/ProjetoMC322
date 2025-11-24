package projetomc322;

public interface Alarme {
    abstract public void checkLimiteCampoDespesa(CampoDespesa campoDespesa);

    abstract public void checkLimiteTotal(Gerenciador gerenciador);
}
