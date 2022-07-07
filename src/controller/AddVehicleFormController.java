package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database.Database;
import entity.Bus;
import entity.CargoLorry;
import entity.Van;
import entity.Vehicle;
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

public class AddVehicleFormController extends Utilities {
    public AnchorPane addVehicleContext;

    public JFXTextField txtVehicleNumber;
    public JFXComboBox<String> cmbVehicleType;
    public JFXTextField txtMaxWeight;
    public JFXTextField txtNoOfPassengers;
    // add Vehicle regex labels
    public Label lblVnRegex;
    public Label lblWeightRegex;
    public Label lblPassengerRegex;

    public JFXButton btnUpdateMenu;
    public JFXButton btnAddMenu;
    public AnchorPane vehicleContext;
    public AnchorPane updateVehicleContext;
    public JFXComboBox cmbVehicleNumberUpdate;
    public JFXTextField txtMaxWeightUpdate;
    public JFXTextField txtNoOfPassengersUpdate;
    public JFXTextField txtVehicleTypeUpdate;
    // update vehicle regex labels
    public Label lblWeightUpdateRegex;
    public Label lblPassengerUpdateRegex;
    public Label lblVTypeUpdateRegex;

    Vehicle selectedVehicle;
    String selectedType = "";

    public void initialize(){
        // initialize cmb
        cmbVehicleType.getItems().add("Bus");
        cmbVehicleType.getItems().add("Van");
        cmbVehicleType.getItems().add("Cargo Lorry");

        // add listener to cmb
        cmbVehicleType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedType = newValue;
        });

        // validate fields
        validateFields();


        // update vehicle transition
        updateVehicleContext.setTranslateY(610);

        btnUpdateMenu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.2));
            slide.setNode(updateVehicleContext);
            slide.setToY(0);
            slide.play();
        });
        btnAddMenu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.2));
            slide.setNode(updateVehicleContext);
            slide.setToY(610);
            slide.play();
        });

        // fill the fields auto when selecting a vehicle no on update process
        fillFieldsWhenUpdate();
    }

    private void fillFieldsWhenUpdate() {
        //initialize vehicle Number cmb
        for (Vehicle v : Database.vehicles) {
            cmbVehicleNumberUpdate.getItems().add(v.getVehicleNumber());
        }
        // find details according to v No
        cmbVehicleNumberUpdate.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Vehicle v : Database.vehicles) {
                try {
                    if (v.getVehicleNumber().equals(newValue.toString())) {
                        selectedVehicle = v;
                        txtVehicleTypeUpdate.setText(v.getVehicleType());
                        txtMaxWeightUpdate.setText(String.valueOf(v.getMaxWeight()));
                        txtNoOfPassengersUpdate.setText(String.valueOf(v.getNoOfPassengers()));
                    }
                } catch (NullPointerException nullPointerException) {}
            }
        });

    }

    private void validateFields() {
        // add listener to textFields --> to validate txtF with regex
        txtVehicleNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            lblVnRegex.setText(newValue.equals("") || newValue.matches("[A-Z 0-9]{2,3}[-][0-9]{4,6}")? "" : "*Enter a valid vehicle number");
        });
        txtMaxWeight.textProperty().addListener((observable, oldValue, newValue) -> {
            lblWeightRegex.setText(newValue.equals("") || newValue.matches("[0-9]{3,7}")? "" : "*Enter correct weight");
        });
        txtNoOfPassengers.textProperty().addListener((observable, oldValue, newValue) -> {
            lblPassengerRegex.setText(newValue.equals("") || newValue.matches("[0-9]{1,3}")? "" : "*Enter correct number of passengers");
        });
    }

    public void addOnAction(ActionEvent actionEvent) {
        if (!(selectedType == "" || txtVehicleNumber.getText().equals("") || txtMaxWeight.getText().equals("") || txtNoOfPassengers.getText().equals(""))){
            if (lblPassengerRegex.getText().equals("") && lblWeightRegex.getText().equals("") && lblPassengerRegex.getText().equals("")){
                // add to db
                switch (selectedType){
                    case "Bus" : Database.vehicles.add(new Bus(txtVehicleNumber.getText(),"Bus",Integer.parseInt(txtMaxWeight.getText()),Integer.parseInt(txtNoOfPassengers.getText()))); break;
                    case "Van" : Database.vehicles.add(new Van(txtVehicleNumber.getText(),"Van",Integer.parseInt(txtMaxWeight.getText()),Integer.parseInt(txtNoOfPassengers.getText()))); break;
                    case "Cargo Lorry" : Database.vehicles.add(new CargoLorry(txtVehicleNumber.getText(),"Cargo Lorry",Integer.parseInt(txtMaxWeight.getText()),Integer.parseInt(txtNoOfPassengers.getText()))); break;
                }
                new Alert(Alert.AlertType.INFORMATION,selectedType+" added!").show();

                // clear data
                clearFields(txtVehicleNumber,txtMaxWeight,txtNoOfPassengers);
                clearCmb(cmbVehicleType);

            } else
                new Alert(Alert.AlertType.ERROR,"Enter valid details!").show();
        }else
            new Alert(Alert.AlertType.ERROR,"Fill all fields!").show();
    }

    public void backOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi(addVehicleContext,"ManagementForm","M a n a g e m e n t");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (cmbVehicleNumberUpdate.getSelectionModel().getSelectedItem() == null){
            // show red labels
            lblVTypeUpdateRegex.setText("Required");
            lblPassengerUpdateRegex.setText("Required");
            lblWeightUpdateRegex.setText("Required");
        }else {
            selectedVehicle.setVehicleType(txtVehicleTypeUpdate.getText());
            selectedVehicle.setMaxWeight(Integer.parseInt(txtMaxWeightUpdate.getText()));
            selectedVehicle.setNoOfPassengers(Integer.parseInt(txtNoOfPassengersUpdate.getText()));
            // alert
            new Alert(Alert.AlertType.INFORMATION,"Vehicle details updated!").show();
            // clear fields
            clearCmb(cmbVehicleNumberUpdate);
            clearFields(txtVehicleTypeUpdate,txtMaxWeightUpdate,txtNoOfPassengersUpdate);
            // refresh
            initialize();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (cmbVehicleNumberUpdate.getSelectionModel().getSelectedItem() == null){
            // show red label
            lblPassengerUpdateRegex.setText("Select vehicle first!");
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure? This action can't undone",ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)){
                Database.vehicles.remove(selectedVehicle);
                new Alert(Alert.AlertType.INFORMATION,"Vehicle Deleted!").show();
                // clear fields
                clearFields(txtVehicleTypeUpdate,txtMaxWeightUpdate,txtNoOfPassengersUpdate);
                clearCmb(cmbVehicleNumberUpdate);
                // refresh
                initialize();
            }
        }
    }
}
