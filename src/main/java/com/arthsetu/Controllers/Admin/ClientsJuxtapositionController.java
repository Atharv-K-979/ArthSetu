package com.arthsetu.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import com.arthsetu.Models.Client;
import com.arthsetu.Models.Model;
import com.arthsetu.Viwes.ClientCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsJuxtapositionController implements Initializable {
    public ListView<Client> clientsJuxtaposition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeData();
        clientsJuxtaposition.setItems(Model.getInstance().getClientsList());
        clientsJuxtaposition.setCellFactory(e -> new ClientCellFactory());
    }

    private void initializeData() {
        if (Model.getInstance().getClientsList().isEmpty()) {
            Model.getInstance().setClientsList();
        }
    }

}
