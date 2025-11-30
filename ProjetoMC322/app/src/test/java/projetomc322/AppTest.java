package projetomc322;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void deveCalcularTotalMensalCorretamente() {
        CampoReceita campoReceita = new CampoReceita("Receitas");

        Receita receitaMensal = new Receita("Salário", 100f, 1);
        Receita receitaSemanal = new Receita("Freelancer", 25f, 4);

        campoReceita.addReceita(receitaMensal);
        campoReceita.addReceita(receitaSemanal);

        float esperado = 200f;
        assertEquals(esperado, campoReceita.getValorTotal(), 0.0001f);
    }

    @Test
    void deveLancarExcecaoQuandoListaInternaForNull() throws Exception {
        CampoReceita campoReceita = new CampoReceita("Receitas");

        // força a lista interna a ser null (nome do campo corrigido)
        var field = CampoReceita.class.getDeclaredField("listaReceitas");
        field.setAccessible(true);
        field.set(campoReceita, null);

        assertThrows(NullPointerException.class, () -> {
            campoReceita.getValorTotal();
        });
    }

    @Test
    void deveCalcularTotalDespesasCorretamente() {
        CampoDespesa campoDespesa = new CampoDespesa("Despesas Fixas");

        Despesa despesaMensal = new Despesa("Aluguel", 100f, 1);
        Despesa despesaSemanal = new Despesa("Transporte", 25f, 4);

        campoDespesa.addDespesa(despesaMensal);
        campoDespesa.addDespesa(despesaSemanal);

        float esperado = 200f;
        assertEquals(esperado, campoDespesa.calculaValorTotal(), 0.0001f);
    }
}