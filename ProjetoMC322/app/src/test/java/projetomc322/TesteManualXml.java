package projetomc322;

public class TesteManualXml {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTE MANUAL DE XML ---");

        Gerenciador g = new Gerenciador();

        try {
            //simula cadastro de campos
            System.out.println("1. Criando campos...");
            g.addCampo('r', "Salario");
            g.addCampo('d', "Alimentacao");
            
            //simular cadastro de movimentações
            System.out.println("2. Adicionando transações...");
            //receita R$5000
            g.addMov('r', "Salario", "Pagamento Novembro", 5000.0f, 1);
            
            //despesa R$50 (4x no mês) = R$ 200
            g.addMov('d', "Alimentacao", "Pizza Fim de Semana", 50.0f, 4);

            //verifica saldo final
            System.out.println("Saldo na memória: R$ " + g.getSaldoFinal());

            System.out.println("3. Tentando salvar XML...");
            Persistencia.salvar(g, "Debug"); //dados_Debug.xml

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--- TESTE FINALIZADO ---");
    }
}