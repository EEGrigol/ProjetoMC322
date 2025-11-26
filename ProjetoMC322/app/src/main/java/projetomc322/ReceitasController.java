package projetomc322;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReceitasController {

    @FXML private TextField campoNome;
    @FXML private TextField campoValor;
    @FXML private ComboBox<String> campoFrequencia;
    @FXML private Label mensagem;

    private CampoReceita campoReceita = new CampoReceita("Receitas");

    @FXML
    private void adicionarReceita() {
        try {
            String nome = campoNome.getText();
            float valor = Float.parseFloat(campoValor.getText());
            String freq = campoFrequencia.getValue();

            Receita receita = new Receita(nome, valor, converterFrequencia(freq));
            campoReceita.AddReceita(receita);

            mensagem.setText("Receita adicionada com sucesso!");

            limparCampos();

        } catch (Exception e) {
            mensagem.setText("Erro: verifique os valores preenchidos");
        }
    }

    private int converterFrequencia(String freq) {
        return switch (freq) {
            case "Mensal" -> 1;
            case "Semanal" -> 4;
            case "Anual" -> 1 / 12;
            default -> 1;
        };
    }

    private void limparCampos() {
        campoNome.clear();
        campoValor.clear();
        campoFrequencia.getSelectionModel().clearSelection();
    }
}
