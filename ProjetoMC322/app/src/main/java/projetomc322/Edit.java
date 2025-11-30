package projetomc322;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Edit {
    public static <T extends Movimentacao> void mostrar(T item, TableView<T> tabela, Runnable aoSalvar) {
        
        Stage janela = new Stage();
        janela.setTitle("Editar");

        TextField txtNome = new TextField(item.getNome());
        TextField txtValor = new TextField(String.valueOf(item.getValor()));
        TextField txtFreq = new TextField(String.valueOf(item.getFreq()));
        Button btn = new Button("Salvar");

        btn.setOnAction(e -> {
            try {
                float novoValor = Float.parseFloat(txtValor.getText());
                int novaFreq = Integer.parseInt(txtFreq.getText());

                if (novoValor < 0 || novaFreq < 0) {
                    new Alert(Alert.AlertType.WARNING, "Não use números negativos!").show();
                    return;
                }

                tabela.refresh();
                aoSalvar.run();
                janela.close();
            } catch (Exception ex) {
                alerta("Erro nos dados!");
            }
        });

        VBox layoutV = new VBox(10, new Label("Nome:"), txtNome, new Label("Valor:"), txtValor, new Label("Freq:"), txtFreq, btn);
        layoutV.setPadding(new Insets(20));
        janela.setScene(new Scene(layoutV, 250, 300));
        janela.show();
    }

    private static void alerta(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).show();
    }
}