package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.Database;
import entity.Admin;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.Utilities;

import java.io.IOException;

public class NewAdminFormController extends Utilities {
    public AnchorPane NewAdminContext;
    public JFXTextField txtUName;
    public JFXPasswordField pwPassword;
    public JFXPasswordField pwReEnterPassword;
    public JFXButton btnReg;
    // regex
    public Label lblUNRegex;
    public Label lblPWRegex;
    public Label lblReEnterPwRegex;

    public void initialize(){
        validateAllFields();
    }

    private void validateAllFields() {
        txtUName.textProperty().addListener((observable, oldValue, newValue) -> {
            lblUNRegex.setText(txtUName.getText().matches("[A-Z][a-z]*") || txtUName.getText().equals("") ? "" : "First letter must be Capital eg: 'Saman'" );
        });
        pwPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            lblPWRegex.setText(pwPassword.getText().length()>4 || pwPassword.getText().equals("") ? "" : "Password too short! least 5 characters");
        });
        pwReEnterPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            lblReEnterPwRegex.setText(pwReEnterPassword.getText().equals(pwPassword.getText()) || pwReEnterPassword.getText().equals("") ? "" : "Password doesn't match" );
        });

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        setUi(NewAdminContext,"LoginForm","L o g i n");
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        if (txtUName.getText().equals("") || pwPassword.getText().equals("") || pwReEnterPassword.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"Fill all fields!").show();
        }else if (!lblReEnterPwRegex.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"Passwords doesn't match!").show();
        }else if (lblUNRegex.getText().equals("") && lblPWRegex.getText().equals("")){
            // add admin to database
            Database.admins.add(new Admin(txtUName.getText(),pwPassword.getText()));
            new Alert(Alert.AlertType.INFORMATION,"Registration successful!").show();
            // clear all fields
            clearFields(txtUName,pwPassword,pwReEnterPassword);
            return;
        }else {
            new Alert(Alert.AlertType.ERROR,"Give attention to red labels!").show();
        }
    }
}
