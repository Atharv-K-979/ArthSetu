package com.arthsetu.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.arthsetu.Models.Client;
import com.arthsetu.Models.Model;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField firstNameField;
    public TextField surnameField;
    public TextField passwordField;
    public CheckBox payeeAddressCheckBox;
    public Label payeeAddressLbl;
    public CheckBox checkingAccountCheckBox;
    public TextField checkingAccountBalanceField;
    public CheckBox savingsAccountCheckBox;
    public TextField savingsAccountBalanceField;
    public Button createClientBtn;
    public Label errorLbl;
    private String payeeAddress;
    private boolean createCheckingAccountFlag = false;
    private boolean createSavingsAccountFlag = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createClientBtn.setOnAction(actionEvent -> createClient());
        payeeAddressCheckBox.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {;
                onCreatePayeeAddress();
            } else {
                payeeAddressLbl.setText("");
            }
        });
        savingsAccountCheckBox.selectedProperty().addListener((observableValue, oldVal, newVal) -> createSavingsAccountFlag = newVal);
        checkingAccountCheckBox.selectedProperty().addListener((observableValue, oldVal, newVal) -> createCheckingAccountFlag = newVal);
        errorLbl.setText("");

    }

    public void createClient() {
        if (firstNameField.getText().isEmpty() || surnameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorLbl.setStyle("-fx-text-fill:red;");
            errorLbl.setText("Please fill First Name, Surname, and Password.");
            return;
        }

        if (payeeAddress == null || payeeAddress.isEmpty()) {
            payeeAddress = createPayeeAddress();
            if (payeeAddress.isEmpty()) {
                errorLbl.setStyle("-fx-text-fill:red;");
                errorLbl.setText("Failed to generate payee address.");
                return;
            }
        }

        if (createCheckingAccountFlag) {
            createAccount("checking");
        }
        if (createSavingsAccountFlag) {
            createAccount("savings");
        }

        String firstName = firstNameField.getText();
        String lastName = surnameField.getText();
        String password = passwordField.getText();
        Client client = new Client(firstName, lastName, payeeAddress, Model.getInstance().getCheckingAccount(payeeAddress), Model.getInstance().getCheckingAccount(payeeAddress), LocalDate.now());
        Model.getInstance().getDatabaseDriver().createClient(client, password);
        errorLbl.setStyle("-fx-text-fill:blue;");
        errorLbl.setText("Saved successfully");
        emptyFields();

    }


    private void createAccount(String accountType) {

        payeeAddress=createPayeeAddress();
        String firstSection = "3201";
        String lastSection = Integer.toString((new Random().nextInt(8999)) + 1000);
        String accountNumber = firstSection + lastSection;
        if (accountType.equals("checking")) {
            double balance = checkingAccountBalanceField.getText().isEmpty() ? 0.0 : Double.parseDouble(checkingAccountBalanceField.getText());
            Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress, accountNumber, 10, balance);
        } else {
            double balance = savingsAccountBalanceField.getText().isEmpty() ? 0.0 : Double.parseDouble(savingsAccountBalanceField.getText());
            Model.getInstance().getDatabaseDriver().createSavingsAccount(payeeAddress, accountNumber, 10000, balance);
        }

    }

    private void onCreatePayeeAddress() {
        if (firstNameField.getText() != null && !firstNameField.getText().isEmpty() &&
            surnameField.getText() != null && !surnameField.getText().isEmpty()) {
            payeeAddress=createPayeeAddress();
            payeeAddressLbl.setText(payeeAddress);
            errorLbl.setText("");
        } else {
            errorLbl.setStyle("-fx-text-fill:red;");
            errorLbl.setText("Please fill First Name and Surname first.");
            payeeAddressCheckBox.setSelected(false);
        }
    }

    private String createPayeeAddress() {
        if (firstNameField.getText() == null || firstNameField.getText().isEmpty() ||
            surnameField.getText() == null || surnameField.getText().isEmpty()) {
            return "";
        }
        int id = Model.getInstance().getDatabaseDriver().getLastClientId() + 1;
        char c = Character.toLowerCase((firstNameField.getText().charAt(0)));
        return "@" + c + id + surnameField.getText();
    }

    private void emptyFields() {
        firstNameField.setText("");
        surnameField.setText("");
        passwordField.setText("");
        payeeAddressCheckBox.setSelected(false);
        payeeAddressLbl.setText("");
        checkingAccountBalanceField.setText("");
        savingsAccountBalanceField.setText("");
        checkingAccountCheckBox.setSelected(false);
        savingsAccountCheckBox.setSelected(false);
    }

}
