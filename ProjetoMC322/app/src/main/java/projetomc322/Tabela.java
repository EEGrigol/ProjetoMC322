package projetomc322;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Tabela {
    public static <T extends Movimentacao> TableView<T> criar() {
        TableView<T> tabela = new TableView<>();
        
        tabela.getColumns().add(criarColuna("Nome", "nome"));
        tabela.getColumns().add(criarColuna("Valor", "valor"));
        tabela.getColumns().add(criarColuna("Freq", "freq"));
        
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tabela.setPrefHeight(150);
        
        return tabela;
    }

    private static <T> TableColumn<T, ?> criarColuna(String titulo, String propriedade) {
        TableColumn<T, ?> coluna = new TableColumn<>(titulo);
        coluna.setCellValueFactory(new PropertyValueFactory<>(propriedade));
        return coluna;
    }
}
