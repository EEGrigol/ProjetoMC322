package projetomc322;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    // variáveis de instância para manter os dados do usuário acessíveis em toda a classe
    private String nomeUsuario = "";
    private int idadeUsuario = 0;

    @Override
    public void start(Stage stage) {

        Label titulo = new Label("Cadastro");

        Label nomeLabel = new Label("Nome:");
        TextField nomeInput = new TextField();
        HBox linhaNome = new HBox(10, nomeLabel, nomeInput);

        Label idadeLabel = new Label("Idade:");
        TextField idadeInput = new TextField();
        HBox linhaIdade = new HBox(10, idadeLabel, idadeInput);

        Label mensagem = new Label();
        mensagem.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 14;");

        Button continuar = new Button("Continuar");

        VBox telaCadastro = new VBox(15);
        telaCadastro.setStyle("-fx-padding: 20; -fx-alignment: center;");
        telaCadastro.getChildren().addAll(titulo, linhaNome, linhaIdade, continuar, mensagem);

        Scene cenaCadastro = new Scene(telaCadastro, 350, 250);

        continuar.setOnAction(e -> {
            String nome = nomeInput.getText().trim();
            String idade = idadeInput.getText().trim();

            // verifica se algum campo está vazio
            if (nome.isEmpty() || idade.isEmpty()) {
                mensagem.setText("Preencha todos os campos!");
                return;
            }

            // valida nome: apenas letras e espaços (aceita acentos)
            if (!nome.matches("[A-Za-zÀ-ÖØ-öø-ÿ ]+")) {
                mensagem.setText("O nome deve conter apenas letras.");
                return;
            }

            // valida idade: apenas números
            if (!idade.matches("\\d+")) {
                mensagem.setText("A idade deve conter apenas números.");
                return;
            }

            // salva nos campos da classe para uso posterior
            nomeUsuario = nome;
            idadeUsuario = Integer.parseInt(idade);

            // troca de cena
            stage.setScene(criarTelaListas());
        });

        stage.setTitle("Sistema");
        stage.setScene(cenaCadastro);
        stage.show();
    }

private Scene criarTelaListas() {

    Label nomeUsuarioLabel = new Label("Usuário: " + nomeUsuario);
    nomeUsuarioLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-padding: 10;");

    // ======== CAIXA DE RECEITAS (TEMPLATE) ========

    // título acima da tabela
    Label tituloReceitas = new Label("Receitas");
    tituloReceitas.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

    // tabela vazia apenas como layout
    TableView<Receita> tabelaReceitas = new TableView<>();

    // coluna Nome
    TableColumn<Receita, String> colNomeR = new TableColumn<>("Nome");
    colNomeR.setCellValueFactory(new PropertyValueFactory<>("nome"));

    // coluna Valor
    TableColumn<Receita, String> colValorR = new TableColumn<>("Valor");
    colValorR.setCellValueFactory(new PropertyValueFactory<>("valor"));

    // coluna Frequência
    TableColumn<Receita, String> colFreqR = new TableColumn<>("Frequência");
    colFreqR.setCellValueFactory(new PropertyValueFactory<>("frequencia"));

    // adiciona as três colunas
    tabelaReceitas.getColumns().addAll(colNomeR, colValorR, colFreqR);

    // ajusta tamanho automático das colunas
    tabelaReceitas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    tabelaReceitas.setPrefHeight(200);

    // texto abaixo da tabela (sem cálculo real)
    Label totalReceitas = new Label("Total no mês: R$ 0,00");
    totalReceitas.setStyle("-fx-font-weight: bold; -fx-padding: 10 0 0 0;");

    // botão para adicionar nova receita
    Button btnAdicionarReceita = new Button("Adicionar Receita");
    btnAdicionarReceita.setStyle("-fx-padding: 5 10; -fx-font-size: 12;");

    VBox colunaReceitas = new VBox(10,
            tituloReceitas,
            tabelaReceitas,
            totalReceitas,
            btnAdicionarReceita
    );
    colunaReceitas.setStyle("-fx-padding: 20; -fx-alignment: top-center;");


    // ======== CAIXA DE DESPESAS ========

    Label tituloDespesas = new Label("Despesas");
    tituloDespesas.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

    TableView<Despesa> tabelaDespesas = new TableView<>();

    TableColumn<Despesa, String> colNomeD = new TableColumn<>("Nome");
    colNomeD.setCellValueFactory(new PropertyValueFactory<>("nome"));

    TableColumn<Despesa, String> colValorD = new TableColumn<>("Valor");
    colValorD.setCellValueFactory(new PropertyValueFactory<>("valor"));

    TableColumn<Despesa, String> colFreqD = new TableColumn<>("Frequência");
    colFreqD.setCellValueFactory(new PropertyValueFactory<>("frequencia"));

    tabelaDespesas.getColumns().addAll(colNomeD, colValorD, colFreqD);
    tabelaDespesas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    tabelaDespesas.setPrefHeight(200);

    Label totalDespesas = new Label("Total no mês: R$ 0,00");
    totalDespesas.setStyle("-fx-font-weight: bold; -fx-padding: 10 0 0 0;");

    // botão para criar novo CAMPO de despesa
    Button btnNovoCampoDespesa = new Button("Novo Campo de Despesa");
    btnNovoCampoDespesa.setStyle("-fx-padding: 5 10; -fx-font-size: 12;");

    // botão para adicionar despesa dentro do CAMPO atual
    Button btnAdicionarDespesa = new Button("Adicionar Despesa");
    btnAdicionarDespesa.setStyle("-fx-padding: 5 10; -fx-font-size: 12;");

    // organiza os botões juntos
    HBox botoesDespesas = new HBox(10, btnNovoCampoDespesa, btnAdicionarDespesa);
    botoesDespesas.setStyle("-fx-alignment: center; -fx-padding: 5 0 0 0;");

    VBox colunaDespesas = new VBox(10,
            tituloDespesas,
            tabelaDespesas,
            totalDespesas,
            botoesDespesas
    );
    colunaDespesas.setStyle("-fx-padding: 20; -fx-alignment: top-center;");


    // ======== LAYOUT FINAL ========

    HBox conteudoCentral = new HBox(20, colunaReceitas, colunaDespesas);
    conteudoCentral.setStyle("-fx-alignment: center; -fx-padding: 20;");

    BorderPane layout = new BorderPane();

    HBox topBox = new HBox(nomeUsuarioLabel);
    topBox.setStyle("-fx-padding: 5 10 0 10; -fx-alignment: top-left;");
    layout.setTop(topBox);

    layout.setCenter(conteudoCentral);

    return new Scene(layout, 700, 450);

}

    public static void main(String[] args) {
        launch(args);
    }
}
