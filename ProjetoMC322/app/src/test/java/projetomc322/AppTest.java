package projetomc322;

// importações solicitadas
import projetomc322.CampoReceita;
import projetomc322.CampoDespesa;
import projetomc322.Receita;
import projetomc322.Despesa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void deveCalcularTotalMensalCorretamente() {
        // instância movida para dentro do teste
        CampoReceita campoReceita = new CampoReceita("Receitas");

        Receita receitaMensal = new Receita("Salário", 100f, 1);
        Receita receitaSemanal = new Receita("Freelancer", 25f, 4);

        campoReceita.AddReceita(receitaMensal);
        campoReceita.AddReceita(receitaSemanal);

        float esperado = 200f;

        assertEquals(esperado, campoReceita.CalulaReceitaTotal());
    }

    @Test
    void deveLancarExcecaoQuandoListaNull() {
        CampoReceita campoReceita = new CampoReceita("Receitas");

        // Este teste verifica se o método calculaTotalMensal(null)
        // lança uma NullPointerException
        assertThrows(NullPointerException.class, () -> {
            campoReceita.CalulaValorTotal(null);
        });
    }

    /**
     * Teste equivalente para CampoDespesa
     * valida o cálculo total de despesas mensais
     */
    @Test
    void deveCalcularTotalDespesasCorretamente() {
        CampoDespesa campoDespesa = new CampoDespesa("Despesas Fixas");

        Despesa despesaMensal = new Despesa("Aluguel", 100f, 1);
        Despesa despesaSemanal = new Despesa("Transporte", 25f, 4);

        campoDespesa.AddDespesa(despesaMensal);
        campoDespesa.AddDespesa(despesaSemanal);

        float esperado = 200f;

        assertEquals(esperado, campoDespesa.CalulaValorTotal());
    }
}
