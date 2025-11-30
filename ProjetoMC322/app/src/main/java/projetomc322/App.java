package projetomc322;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        LayoutFinanceiro layout = new LayoutFinanceiro();
        FinanceController controller = new FinanceController(layout);
        
        controller.iniciar();

        //EXIBIÇÃO
        stage.setTitle("Gerenciador Financeiro Completo");
        stage.setScene(layout.CenaSetup());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}