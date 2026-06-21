package com.arthsetu.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import com.arthsetu.Models.Model;
import com.arthsetu.Models.Transaction;
import com.arthsetu.Viwes.TransactionCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    public ListView<Transaction> transactionsListView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionsListView.setItems(Model.getInstance().getTransactionsList(-1));
        transactionsListView.setCellFactory(e -> new TransactionCellFactory());
    }
}
