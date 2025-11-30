package projetomc322;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LayoutFinanceiro {

    //Criação dos Bottões
    public Button btnSalvar = new Button("Salvar");
    public Button btnCarregar = new Button("Carregar");

    public Button btnNovoCampoReceita = new Button("+ Campo Receita");
    public Button btnAddReceita = new Button("+ Receita");

    public Button btnNovoCampoDespesa = new Button("+ Campo Despesa");
    public Button btnAddDespesa = new Button("+ Despesa");

    //Criação de listas expansíveis
    public Accordion accordionDespesas = new Accordion();
    public Accordion accordionReceitas = new Accordion();

    //Criação de Textos
    public Label labelTotalDespesas = new Label("Total Despesas: R$ 0.00");
    public Label labelTotalReceitas = new Label("Total Receitas: R$ 0.00");
    public Label labelSaldoFinal = new Label("SALDO: R$ 0.00");

    public Scene CenaSetup() {
        labelSaldoFinal.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        //Bloco Horizontal para a barra superior
        HBox TopBox = new HBox(20, labelSaldoFinal, new Region(), btnSalvar, btnCarregar);

        //Deixa o Saldo e os botões espaçados com essa Região entre eles
        HBox.setHgrow(TopBox.getChildren().get(1), Priority.ALWAYS);
        TopBox.setAlignment(Pos.CENTER_LEFT);
        TopBox.setPadding(new Insets(10));
        TopBox.setStyle("-fx-background-color: #d2d0d0ff; -fx-border-color: #000000ff; -fx-border-width: 0 0 1 0;");

        // Receitas
        HBox botoesReceita = new HBox(5, btnNovoCampoReceita, btnAddReceita);
        botoesReceita.setAlignment(Pos.CENTER);
        VBox layoutReceitas = new VBox(10, new Label("RECEITAS"), labelTotalReceitas, botoesReceita, accordionReceitas);
        layoutReceitas.setPadding(new Insets(10));
        layoutReceitas.setStyle("-fx-background-color: #e8f5e9;");

        // Despesas
        HBox botoesDespesa = new HBox(5, btnNovoCampoDespesa, btnAddDespesa);
        botoesDespesa.setAlignment(Pos.CENTER);
        VBox layoutDespesas = new VBox(10, new Label("DESPESAS"), labelTotalDespesas, botoesDespesa, accordionDespesas);
        layoutDespesas.setPadding(new Insets(10));
        layoutDespesas.setStyle("-fx-background-color: #ffebee;");

        // Divisão dinâmica da tela em duas partes
        SplitPane splitPane = new SplitPane(layoutReceitas, layoutDespesas);
        splitPane.setDividerPositions(0.5);

        BorderPane root = new BorderPane();
        root.setTop(TopBox);
        root.setCenter(splitPane);

        return new Scene(root, 900, 600);
    }
}