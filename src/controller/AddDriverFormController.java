package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database.Database;
import entity.Driver;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.Utilities;

import java.io.IOException;
import java.util.Optional;

public class AddDriverFormController extends Utilities {
    public AnchorPane addDriverContext;

    public JFXTextField txtName;
    public JFXTextField txtDrivingLicenseNo;
    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtContactNo;
    // regex labels
    public Label lblNameRegex;
    public Label lblNicRegex;
    public Label lblLicenseNoRegex;
    public Label lblAddressRegex;
    public Label lblContactRegex;
    // On update ap
    public AnchorPane updateDriverContext;
    public JFXTextField txtDrivingLicenseUpdate;
    public JFXTextField txtAddressUpdate;
    public JFXTextField txtNicUpdate;
    public JFXTextField txtContactUpdate;
    public JFXComboBox cmbDriverName;
    public JFXButton btnUpdateMenu;
    public JFXButton btnAddMenu;
    public JFXTextField txtDriverNameUpdate;
    // regex labels for update ap
    public Label lblDriverNameUpdateRegex;
    public Label lblNicUpdateRegex;
    public Label lblDrivingLicenseUpdateRegex;
    public Label lblAddressUpdateRegex;
    public Label lblContactUpdateRegex;
    public Label lblDriverNameCmbRegex;

    Driver selectedDriver;


    public void initialize(){
        validateFieldsWhenAddVehicle();
        validateFieldsWhenUpdateVehicle();

        // transition of update ap
        updateDriverContext.setTranslateY(610);
        btnUpdateMenu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.2));
            slide.setNode(updateDriverContext);
            slide.setToY(0);
            slide.play();
        });
        btnAddMenu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.2));
            slide.setNode(updateDriverContext);
            slide.setToY(610);
            slide.play();
        });

        // initialize driver name cmb
        for (int i = 0; i < Database.drivers.size(); i++) {
            cmbDriverName.getItems().add(Database.drivers.get(i).getname());
        }

        // fil all fields when selecting a driver name
        fillFieldsWhenUpdate();


    }

    private void fillFieldsWhenUpdate() {
        cmbDriverName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Driver d : Database.drivers) {
                if (d.getname().equals(newValue)){
                    selectedDriver = d;
                    // fill field details
                    txtDriverNameUpdate.setText(d.getname());
                    txtAddressUpdate.setText(d.getAddress());
                    txtContactUpdate.setText(d.getContactNum());
                    txtNicUpdate.setText(d.getNic());
                    txtDrivingLicenseUpdate.setText(d.getDrivingLicenseNumber());
                }
            }
        });
    }

    /**
     * Validate all fields of the form with regex.
     * if there are invalid pattern of the text, there will show a red message on below
     */
    private void validateFieldsWhenAddVehicle() {
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            lblNameRegex.setText(newValue.equals("") || newValue.matches("[A-Z][a-z]* [A-Z][a-z]*")? "" : "*Name must be like 'Sumith Kumara'" );
        });
        txtContactNo.textProperty().addListener((observable, oldValue, newValue) -> {
            lblContactRegex.setText(newValue.equals("") || newValue.matches("[+][0-9]{11}")? "" : "*Contact must be like '+94761234567'" );
        });
        txtAddress.textProperty().addListener((observable, oldValue, newValue) -> {
            lblAddressRegex.setText(newValue.equals("") || newValue.matches("[A-Z][a-z]*")? "" : "*Address must be like 'Maharagama'" );
        });
        txtNic.textProperty().addListener((observable, oldValue, newValue) -> {
            lblNicRegex.setText(newValue.equals("") || newValue.matches("[0-9]{9}[V]") || newValue.matches("[0-9]{12}")? "" : "*Invalid NIC" );
        });
        txtDrivingLicenseNo.textProperty().addListener((observable, oldValue, newValue) -> {
            lblLicenseNoRegex.setText(newValue.equals("") || newValue.matches("[B][0-9]{7}")? "" : "*License no must be like 'B1234567'" );
        });
    }

    private void validateFieldsWhenUpdateVehicle() {
        txtDriverNameUpdate.textProperty().addListener((observable, oldValue, newValue) -> {
            lblDriverNameUpdateRegex.setText(newValue.equals("") || newValue.matches("[A-Z][a-z]* [A-Z][a-z]*")? "" : "*Name must be like 'Sumith Kumara'" );
        });
        txtContactUpdate.textProperty().addListener((observable, oldValue, newValue) -> {
            lblContactUpdateRegex.setText(newValue.equals("") || newValue.matches("[+][0-9]{11}")? "" : "*Contact must be like '+94761234567'" );
        });
        txtAddressUpdate.textProperty().addListener((observable, oldValue, newValue) -> {
            lblAddressUpdateRegex.setText(newValue.equals("") || newValue.matches("[A-Z][a-z]*")? "" : "*Address must be like 'Maharagama'" );
        });
        txtNicUpdate.textProperty().addListener((observable, oldValue, newValue) -> {
            lblNicUpdateRegex.setText(newValue.equals("") || newValue.matches("[0-9]{9}[V]") || newValue.matches("[0-9]{12}")? "" : "*Invalid NIC" );
        });
        txtDrivingLicenseUpdate.textProperty().addListener((observable, oldValue, newValue) -> {
            lblDrivingLicenseUpdateRegex.setText(newValue.equals("") || newValue.matches("[B][0-9]{7}")? "" : "*License no must be like 'B1234567'" );
        });
    }

    public void addOnAction(ActionEvent actionEvent) throws IOException {
        boolean isAllFilled = !txtName.getText().equals("") && !txtNic.getText().equals("") && !txtNic.getText().equals("") &&
                !txtAddress.getText().equals("") && !txtContactNo.getText().equals("");
        if (isAllFilled){
            if (lblNameRegex.getText().equals("") || lblNicRegex.getText().equals("") || lblLicenseNoRegex.getText().equals("") || lblContactRegex.getText().equals("") ||
                    lblAddressRegex.getText().equals("")){
                // add to db
                Database.drivers.add(new Driver(txtName.getText(),txtNic.getText(),txtDrivingLicenseNo.getText(),txtAddress.getText(),txtContactNo.getText()));
                new Alert(Alert.AlertType.INFORMATION,"Driver Added!").show();
                //refresh the initialize(because the update ui must get the added driver to cmb)
                setUi(addDriverContext,"AddDriverForm","A d d  d r i v e r");

                //clear data
                clearFields(txtName,txtAddress,txtNic,txtContactNo,txtDrivingLicenseNo);

            }else
                new Alert(Alert.AlertType.ERROR,"Enter valid details!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Fill All Fields!").show();
        }
    }

    public void backOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi(addDriverContext,"ManagementForm","M a n a g e m e n t");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (cmbDriverName.getSelectionModel().getSelectedItem() == null){
            lblDriverNameCmbRegex.setText("*Select a driver first");
        }else {
            if (lblNicUpdateRegex.getText().equals("") && lblAddressUpdateRegex.getText().equals("") && lblContactUpdateRegex.getText().equals("") && lblDrivingLicenseUpdateRegex.getText().equals("")){
                updateDriverDetails();
                //alert
                new Alert(Alert.AlertType.INFORMATION,"Driver Details Updated!").show();
                // clear fields
                clearFields(txtDriverNameUpdate,txtAddressUpdate,txtContactUpdate,txtNicUpdate,txtDrivingLicenseUpdate);
                clearCmb(cmbDriverName);
                // refresh
                initialize();

            }else {
                new Alert(Alert.AlertType.WARNING,"Give attention to red labels!").show();
            }
        }
    }

    private void updateDriverDetails() {
        selectedDriver.setname(txtDriverNameUpdate.getText());
        selectedDriver.setAddress(txtAddressUpdate.getText());
        selectedDriver.setContactNum(txtContactUpdate.getText());
        selectedDriver.setNic(txtNicUpdate.getText());
        selectedDriver.setDrivingLicenseNumber(txtDrivingLicenseUpdate.getText());
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        if (cmbDriverName.getSelectionModel().getSelectedItem() == null){
            lblDriverNameCmbRegex.setText("*Select a driver first");
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure? This action can't undone", ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)){
                Database.drivers.remove(selectedDriver);
                new Alert(Alert.AlertType.INFORMATION,"Driver Deleted!").show();
                // clear fields
                clearFields(txtDriverNameUpdate,txtAddressUpdate,txtContactUpdate,txtNicUpdate,txtDrivingLicenseUpdate);
                clearCmb(cmbDriverName);
                // refresh
                initialize();
            }
        }
    }
}
