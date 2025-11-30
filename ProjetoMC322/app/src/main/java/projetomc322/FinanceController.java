package projetomc322;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FinanceController {
    private Gerenciador gerenciador = new Gerenciador();
    private FeatureAlarme monitorDeLimites = new FeatureAlarme();
    private LayoutFinanceiro layout;

    //Construtor
    public FinanceController(LayoutFinanceiro layout) {
        this.layout = layout;
    }

    //Inicialização dos botões
    public void iniciar() {
        //Atribuições dos botões
        layout.btnSalvar.setOnAction(e -> acaoSalvar());
        layout.btnCarregar.setOnAction(e -> acaoCarregar());
        
        layout.btnNovoCampoReceita.setOnAction(e -> abrirJanelaNovoCampoReceita());
        layout.btnAddReceita.setOnAction(e -> abrirJanelaNovaReceita());
        
        layout.btnNovoCampoDespesa.setOnAction(e -> abrirJanelaNovoCampoDespesa());
        layout.btnAddDespesa.setOnAction(e -> abrirJanelaNovaDespesa());
    }

    // PERSISTÊNCIA
    private void acaoSalvar() {
        Persistencia.salvar(gerenciador, "usuario");
        new Alert(Alert.AlertType.INFORMATION, "Dados salvos!").show();
    }

    private void acaoCarregar() {
        Gerenciador carregado = Persistencia.carregar("usuario");
        if (carregado != null) { //Verifica se tem save para ler
            this.gerenciador = carregado;
            reconstruirInterface();
            new Alert(Alert.AlertType.INFORMATION, "Dados carregados!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Arquivo não encontrado.").show();
        }
    }

    //Atualização geral do layout para acomodar qualquer alteração
    private void reconstruirInterface() {
        layout.accordionReceitas.getPanes().clear();
        layout.accordionDespesas.getPanes().clear();

        for (CampoReceita c : gerenciador.getCamposReceita()) { //Para cada campo de receita
            criarVisualReceita(c);
            TitledPane p = layout.accordionReceitas.getPanes().get(layout.accordionReceitas.getPanes().size() - 1);
            TableView<Receita> t = (TableView<Receita>) p.getContent();
            t.getItems().addAll(c.getListaReceitas());
            atualizarTituloPainel(c);
        }

        for (CampoDespesa c : gerenciador.getCamposDespesa()) { //Para cada campo de despesa
            criarVisualDespesa(c);
            TitledPane p = layout.accordionDespesas.getPanes().get(layout.accordionDespesas.getPanes().size() - 1);
            TableView<Despesa> t = (TableView<Despesa>) p.getContent();
            t.getItems().addAll(c.getListaDespesas());
            atualizarTituloPainel(c);
        }
        atualizarTodosTotais();
    }

    // RECEITAS

    private void abrirJanelaNovoCampoReceita() { //+Campo Receita
        //Cria uma janela de texto para receber os dados
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Novo Campo Receita");
        dialog.setHeaderText("Nome do campo:");
        //Atribuição dos dados fornecidos
        dialog.showAndWait().ifPresent(nome -> { //Espera o usuário digitar
            CampoReceita novo = new CampoReceita(nome);
            gerenciador.getCamposReceita().add(novo);
            criarVisualReceita(novo);
        });
    }

    private void criarVisualReceita(CampoReceita campo) {
        TableView<Receita> tabela = Tabela.criar(); 
        
        configurarInteracaoTabela(tabela, campo);
        TitledPane painel = new TitledPane(campo.nome, tabela);
        painel.setUserData(campo); //Ligação de qual valor vai ser alterado ao clicar
        layout.accordionReceitas.getPanes().add(painel);
        layout.accordionReceitas.setExpandedPane(painel);
    }

    private void abrirJanelaNovaReceita() { //+Receita
        if(gerenciador.getCamposReceita().isEmpty()) { alerta("Crie um campo primeiro!"); return; } //Precisa ter um campo para ser criado
        abrirJanela("Nova Receita", gerenciador.getCamposReceita(), (nome, val, freq, campoObj) -> {
            CampoReceita c = (CampoReceita) campoObj;
            Receita r = new Receita(nome, val, freq);
            c.addReceita(r);
            atualizarTabelaVisual(layout.accordionReceitas, c, r);
        });
    }

    // DESPESAS

    private void abrirJanelaNovoCampoDespesa() { //+Campo Despesa
        Stage janela = new Stage(); //Criação personalizada da janela para criar
        janela.setTitle("Novo Campo Despesa");
        TextField txtNome = new TextField();
        TextField txtLimite = new TextField("0"); // Começa zerado
        Button btn = new Button("Criar");

        btn.setOnAction(e -> {
            try {
                String nome = txtNome.getText();
                float limite = Float.parseFloat(txtLimite.getText());

                //Verificações de Entrada
                if(nome.isEmpty()) return;
                
                if (limite < 0) {
                    alerta("O limite não pode ser negativo!");
                    return;
                
                }
                CampoDespesa novo = new CampoDespesa(nome);
                novo.setValorLimite(limite);
                gerenciador.getCamposDespesa().add(novo);
                criarVisualDespesa(novo);
                janela.close();
            } catch (Exception ex) { alerta("Erro no limite! Inserção inválida"); }
        });

        VBox box = new VBox(10, new Label("Nome:"), txtNome, new Label("Limite (R$):"), txtLimite, btn);
        box.setPadding(new Insets(20));
        janela.setScene(new Scene(box, 250, 200));
        janela.show();
    }

    private void criarVisualDespesa(CampoDespesa campo) {
        TableView<Despesa> tabela = Tabela.criar();
        
        configurarInteracaoTabela(tabela, campo);
        TitledPane painel = new TitledPane(campo.nome, tabela);
        painel.setUserData(campo); //Ligação de qual valor vai ser alterado ao clicar
        layout.accordionDespesas.getPanes().add(painel);
        layout.accordionDespesas.setExpandedPane(painel);
        atualizarTituloPainel(campo);
    }

    private void abrirJanelaNovaDespesa() { //+Despesa
        if(gerenciador.getCamposDespesa().isEmpty()) { alerta("Crie um campo primeiro!"); return; }
        abrirJanela("Nova Despesa", gerenciador.getCamposDespesa(), (nome, val, freq, campoObj) -> {
            CampoDespesa c = (CampoDespesa) campoObj;
            Despesa d = new Despesa(nome, val, freq);
            c.addDespesas(d);
            atualizarTabelaVisual(layout.accordionDespesas, c, d);
            
            // Verifica a cada adição se o limite foi estourado
            monitorDeLimites.checkLimiteCampoDespesa(c); 
            if(monitorDeLimites.isAlarmeLigado()) alerta("LIMITE ESTOURADO EM " + c.nome);
        });
    }

    // MÉTODOS GERAIS

    private <T extends Movimentacao> void configurarInteracaoTabela(TableView<T> tabela, Object campo) {
        ContextMenu menu = new ContextMenu();
        MenuItem excluir = new MenuItem("Excluir Item"); //Deletar no clique do botão direito

        excluir.setOnAction(e -> {
            T item = tabela.getSelectionModel().getSelectedItem();

            if (item == null)
                return;

            if (campo instanceof CampoDespesa)
                ((CampoDespesa)campo).removerDespesa((Despesa)item);
            else
                ((CampoReceita)campo).removerReceita((Receita)item);
            tabela.getItems().remove(item);
            atualizarTodosTotais();
            atualizarTituloPainel(campo);
        });
        
        if (campo instanceof CampoDespesa) {
            MenuItem limite = new MenuItem("Alterar Limite");
            limite.setOnAction(e -> {
                TextInputDialog d = new TextInputDialog(); //Cria janela personalizada para edição
                d.setTitle("Limite");
                d.setHeaderText("Novo Limite (0 = sem):");
                d.showAndWait().ifPresent(v -> {
                    try {
                        float novoLimite = Float.parseFloat(v);
            
                        if (novoLimite < 0) {
                            alerta("Limite não pode ser negativo!");
                            return;
                        
                        }
                        ((CampoDespesa)campo).setValorLimite(Float.parseFloat(v));
                        atualizarTituloPainel(campo);
                    } catch(Exception ex) {
                        alerta("Numéro inválido!");
                    }
                });
            });
            menu.getItems().add(limite);
        }
        
        menu.getItems().add(excluir);
        tabela.setContextMenu(menu);

        tabela.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(ev -> {
                if (ev.getClickCount() == 2 && !row.isEmpty()) { //Se foi clique duplo, abrir edição
                    abrirJanelaEdicao(row.getItem(), campo, tabela);
                }
            });
            return row;
        });
    }

    private <T extends Movimentacao> void abrirJanelaEdicao(T item, Object campo, TableView<T> tabela) {
        Edit.mostrar(item, tabela, () -> {
            atualizarTodosTotais();
            atualizarTituloPainel(campo);
        });
    }

    private void abrirJanela(String titulo, java.util.List<?> campos, AcaoSalvar acao) {
        Stage janela = new Stage();
        janela.setTitle(titulo);

        ComboBox<Object> combo = new ComboBox<>(FXCollections.observableArrayList(campos));
        combo.setCellFactory(p -> new ListCell<>() { //Não são strings, são campos, então precisa disso
            @Override protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null) ? "" : ((item instanceof CampoReceita) ? ((CampoReceita)item).nome : ((CampoDespesa)item).nome));
            }
        });

        combo.setButtonCell(combo.getCellFactory().call(null));
        combo.getSelectionModel().selectFirst();

        TextField txtNome = new TextField();
        TextField txtValor = new TextField();
        TextField txtFreq = new TextField("1");
        Button btn = new Button("Salvar");

        btn.setOnAction(e -> {
            try {
                String nome = txtNome.getText();
                float val = Float.parseFloat(txtValor.getText());
                int freq = Integer.parseInt(txtFreq.getText());
        
                if (val < 0 || freq < 0) {
                    alerta("Valores e Frequência não podem ser negativos!");
                    return; 
        }

        acao.executar(nome, val, freq, combo.getValue());
        atualizarTodosTotais();
        janela.close();

    } catch(Exception ex) { 
        alerta("Dados inválidos! Verifique se digitou os números corretos"); 
    }
});

        //Layout vertical
        VBox layoutV = new VBox(10, new Label("Campo:"), combo, new Label("Nome:"), txtNome, new Label("Valor:"), txtValor, new Label("Freq:"), txtFreq, btn);
        layoutV.setPadding(new Insets(20)); layoutV.setAlignment(Pos.CENTER);
        janela.setScene(new Scene(layoutV, 300, 350));
        janela.show();
    }

    private <T> void atualizarTabelaVisual(Accordion acc, Object campo, T item) {
        for (TitledPane p : acc.getPanes()) {
            if (p.getUserData() == campo) {
                ((TableView<T>)p.getContent()).getItems().add(item);
                atualizarTituloPainel(campo);
                break;
            }
        }
    }

    private void atualizarTituloPainel(Object campo) {
        Accordion acc = (campo instanceof CampoReceita) ? layout.accordionReceitas : layout.accordionDespesas;
        for (TitledPane p : acc.getPanes()) {
            if (p.getUserData() == campo) {
                if (campo instanceof CampoReceita) { 
                    CampoReceita c = (CampoReceita) campo;
                    p.setText(String.format("%s (R$ %.2f)", c.nome, c.calculaValorTotal())); //Somo total padrão
                } else {
                    CampoDespesa c = (CampoDespesa) campo;
                    monitorDeLimites.checkLimiteCampoDespesa(c); //Checa se não estourou o limite
                    float total = c.calculaValorTotal();
                    float lim = c.getValorLimite();

                    String txt = (lim > 0) ? String.format("%s (R$ %.2f / %.2f)", c.nome, total, lim) : String.format("%s (R$ %.2f)", c.nome, total);

                    if (monitorDeLimites.isAlarmeLigado()) {
                        txt += "ALERTA!";
                        p.setStyle("-fx-text-fill: red; -fx-font-weight: bold;"); //Define a cor vermelha para caso tenha estourado
                    } else {
                        p.setStyle("-fx-text-fill: black;"); //Se estiver dentro do limite, volta a cor normal
                    }
                    p.setText(txt);
                }
                break;
            }
        }
    }

    private void atualizarTodosTotais() {
        float r = 0, d = 0;
        for(CampoReceita c : gerenciador.getCamposReceita())
            r += c.calculaValorTotal();
        for(CampoDespesa c : gerenciador.getCamposDespesa())
            d += c.calculaValorTotal();
        float saldo = r - d;

        //Formatação
        layout.labelTotalReceitas.setText(String.format("Total: R$ %.2f", r));
        layout.labelTotalDespesas.setText(String.format("Total: R$ %.2f", d));
        layout.labelSaldoFinal.setText(String.format("SALDO: R$ %.2f", saldo));
        layout.labelSaldoFinal.setTextFill(saldo >= 0 ? Color.GREEN : Color.RED);
    }

    private void alerta(String msg) { //Pop-Up genérico que podemos mandar qualquer mensagem
        new Alert(Alert.AlertType.WARNING, msg).show(); 
    }
}