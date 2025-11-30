package projetomc322;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TesteManualXml {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTE MANUAL DE XML ---");

        Gerenciador g = new Gerenciador();

        try {
            // 1. Criar campos
            System.out.println("1. Criando campos...");
            CampoReceita campoReceita = new CampoReceita("Salario");
            g.getCamposReceita().add(campoReceita);
            CampoDespesa campoDespesa = new CampoDespesa("Alimentacao");
            g.getCamposDespesa().add(campoDespesa);

            // 2. Adicionar movimentações
            System.out.println("2. Adicionando transações...");
            Receita receitaMensal = new Receita("Pagamento Novembro", 5000.0f, 1);
            Despesa despesaSemanal = new Despesa("Pizza Fim de Semana", 50.0f, 4);
            campoReceita.addReceita(receitaMensal);
            campoDespesa.addDespesa(despesaSemanal);

            // 3. Verificar saldo em memória
            System.out.printf("Saldo na memória: R$ %.2f%n", g.getSaldoFinal());

            // 4. Salvar usando Persistencia (gera dados_Debug.xml)
            System.out.println("3. Tentando salvar XML...");
            Persistencia.salvar(g, "Debug");

            // 5. Verificar arquivo gerado (dados_Debug.xml no diretório de trabalho)
            String nomeArquivo = "dados_Debug.xml";
            Path caminho = Paths.get(".").toAbsolutePath().normalize().resolve(nomeArquivo);
            File arquivo = caminho.toFile();

            if (arquivo.exists()) {
                System.out.println("Arquivo salvo em: " + arquivo.getAbsolutePath());
                System.out.println("Tamanho (bytes): " + arquivo.length());

                // Mostrar primeiras linhas do XML para verificação rápida
                try {
                    List<String> linhas = Files.readAllLines(caminho);
                    System.out.println("--- Conteúdo (primeiras 12 linhas) ---");
                    for (int i = 0; i < Math.min(12, linhas.size()); i++) {
                        System.out.println(linhas.get(i));
                    }
                } catch (Exception e) {
                    System.out.println("Não foi possível ler o conteúdo do arquivo: " + e.getMessage());
                }
            } else {
                System.out.println("Arquivo não encontrado: " + caminho);
            }

        } catch (Exception e) {
            System.out.println("Erro durante o teste:");
            e.printStackTrace(System.out);
        }

        System.out.println("--- TESTE FINALIZADO ---");
    }
}