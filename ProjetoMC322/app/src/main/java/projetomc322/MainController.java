package projetomc322;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private void abrirReceitas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("receitas.fxml"));
            Scene scene = new Scene(loader.load(), 400, 300);

            Stage stage = new Stage();
            stage.setTitle("Receitas");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
