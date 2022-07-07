package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import util.Utilities;

import java.io.IOException;

public class ManagementFormController extends Utilities {
    public AnchorPane ManagementContext;
    public JFXComboBox cmbVehicleState;
    public AnchorPane tableContext;


    public void initialize() throws IOException {
        // initialize cmb
        cmbVehicleState.getItems().add("In Parking");
        cmbVehicleState.getItems().add("On Delivery Shift");

        //set default table as in parking
        cmbVehicleState.getSelectionModel().select(0);
        setUiChildren(tableContext,"TableInParkingForm");

        // add listener
        cmbVehicleState.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("In Parking")){
                try {
                    setUiChildren(tableContext,"TableInParkingForm");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    setUiChildren(tableContext,"TableOnDeliveryForm");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addVehicleOnAction(ActionEvent actionEvent) throws IOException {
        setUi(ManagementContext,"AddVehicleForm","Add Vehicle");
    }

    public void addDriverOnAction(ActionEvent actionEvent) throws IOException {
        setUi(ManagementContext,"AddDriverForm","Add Driver");
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        setUi(ManagementContext,"DashboardForm","D a s h b o a r d");
    }
}
