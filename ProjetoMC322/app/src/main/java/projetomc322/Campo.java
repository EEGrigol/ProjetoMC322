package projetomc322;

/**
 * representa um campo financeiro
 */
public abstract class Campo {
    protected String nome;

    /**
     * subclasses devem implementar cálculo do valor total do campo (mensalizado)
     */
    public abstract float CalulaValorTotal();

    /**
     * retorna o nome do campo
     * @return nome do campo
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * retorna o valor total do campo (delegando ao cálculo)
     * @return valor total do campo
     */
    public float getValorTotal(){
        return CalulaValorTotal();
    }

}
