package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database.Database;
import entity.Driver;
import entity.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DashboardController extends Utilities {
    public AnchorPane DashboardContext;

    public Label lblDate;
    public Label lblDay;
    public Label lblTime;

    public JFXComboBox cmbVehicle;
    public JFXComboBox cmbDriver;
    public JFXTextField txtVehicleType;
    public Label lblSlotNumber;

    public JFXButton btnManagementLogin;
    public JFXButton btnParkVehicle;
    public JFXButton btnOnDeliveryShift;
    public ImageView imgVan;
    public ImageView imgBus;
    public ImageView imgLorry;
    public JFXButton btnParkingView;
    // regex labels
    public Label lblSelectVehicleRegex;
    public Label lblDriverRegex;

    int selectedVehicleIndex;
    Vehicle selectedVehicle;
    String selectedDriverName;
    int selectedDriverIndex;
    int slot;


    public void initialize() {

        // set realtime clock
        curDateTime(lblDate, lblDay, lblTime);

        // initialize cmb
        initializeCmb();

        // add listener to vehicleType cmb
        cmbVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // make regex label invisible when select a vehicle
            lblSelectVehicleRegex.setText("");

            try {
                // update lbl Vehicle Type
                updateLabel((String) newValue);

            } catch (NullPointerException e) {
            }

            // update image
            updateImage();

            // decide the parking slot of vehicle
            decideSlot();

            if (Database.vehiclesInPark.contains(selectedVehicle)) {
                btnParkVehicle.setDisable(true);
                btnOnDeliveryShift.setDisable(false);
            } else {
                btnParkVehicle.setDisable(false);
                btnOnDeliveryShift.setDisable(true);
            }

        });

        // add listener to driver cmb
        cmbDriver.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // make invisible error label when select driver
            lblDriverRegex.setText("");
            // get selected drivers name to a variable
            selectedDriverName = (String) newValue;
        });
    }

    private void updateImage() {
        if (txtVehicleType.getText().equals("Bus")) {
            imgVan.setVisible(false);
            imgLorry.setVisible(false);
            imgBus.setVisible(true);
        } else if (txtVehicleType.getText().equals("Van")) {
            imgBus.setVisible(false);
            imgLorry.setVisible(false);
            imgVan.setVisible(true);
        } else {
            imgBus.setVisible(false);
            imgVan.setVisible(false);
            imgLorry.setVisible(true);
        }
    }

    private void decideSlot() {
        // reserve slot
        slot = -1;
        boolean isOver = false;
        while (!isOver) {
            switch (txtVehicleType.getText()) {

                case "Bus": {
                    slot = 14;
                    isOver = true;
                }
                break;

                case "Van": {
                    int[] vanSlots = {1, 2, 3, 4, 12, 13};
                    for (int i : vanSlots) {
                        if (Database.slots.get(i).isAvailable()) {
                            slot = i;
                            isOver = true;
                            break;
                        } else {
                            isOver = true;
                        }
                    }
                }
                break;

                case "Cargo Lorry": {
                    int[] lorrySlots = {5, 6, 7, 8, 9, 10, 11};
                    for (int i : lorrySlots) {
                        if (Database.slots.get(i).isAvailable()) {
                            slot = i;
                            isOver = true;
                            break;
                        } else {
                            isOver = true;
                        }
                    }
                }
                break;
            }
        }
        // update the label slot
        if (Database.vehiclesInPark.contains(selectedVehicle)) {
            lblSlotNumber.setText(String.valueOf(selectedVehicle.getSlot()));
            slot = selectedVehicle.getSlot();
        } else {
            lblSlotNumber.setText(String.valueOf(slot));
        }
    }

    private void updateLabel(String vn) {
        for (int i = 0; i < Database.vehicles.size(); i++) {
            if (vn.equals(Database.vehicles.get(i).getVehicleNumber())) {
                txtVehicleType.setText(Database.vehicles.get(i).getVehicleType());
                selectedVehicleIndex = i;
                selectedVehicle = Database.vehicles.get(i);
            }
        }
    }

    private void initializeCmb() {
        // cmbVehicle
        for (int i = 0; i < Database.vehicles.size(); i++) {
            cmbVehicle.getItems().add(Database.vehicles.get(i).getVehicleNumber());
        }
        // cmbDriver
        for (int i = 0; i < Database.drivers.size(); i++) {
            cmbDriver.getItems().add(Database.drivers.get(i).getname());
        }

    }

    public void parkVehicleOnAction() throws IOException {

        if (cmbVehicle.getSelectionModel().getSelectedIndex() == -1 || cmbDriver.getSelectionModel().getSelectedIndex() == -1) {
            // show error labels
            lblSelectVehicleRegex.setText(cmbVehicle.getSelectionModel().getSelectedIndex() == -1 ? "*Required" : "");
            lblDriverRegex.setText(cmbDriver.getSelectionModel().getSelectedIndex() == -1 ? "*Required" : "");
        } else {
            // get current time
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String time = sdf.format(cal.getTime());

            // mark vehicle as parked and update label to on delivery shift
            selectedVehicle.setParkedTime(time);
            selectedVehicle.setdriverName(selectedDriverName);
            Database.vehiclesInPark.add(selectedVehicle);

            // Alert
            new Alert(Alert.AlertType.INFORMATION, "Fine! Now you can park your " + txtVehicleType.getText() + " at slot " + lblSlotNumber.getText()).show();
            /*TrayNotification tray = new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("Fine");
            tray.setMessage("Now you can Park");
            tray.setNotificationType(NotificationType.SUCCESS);
            //tray.setRectangleFill(Color.valueOf("1C428C"));
            tray.showAndDismiss(Duration.millis(2000));*/

            btnParkVehicle.setDisable(true);
            btnOnDeliveryShift.setDisable(false);
            // remove from on delivery array
            Database.vehicleOnDelivery.remove(selectedVehicle);
            // make driver is available
            findDriverIndex();
            Database.drivers.get(selectedDriverIndex).setAvailable(true);

            // set slot to vehicle
            selectedVehicle.setSlot(slot);
            Database.slots.get(slot).setAvailable(false);

            // clear data
            clearCmb(cmbVehicle, cmbDriver);
            clearLabels(lblSlotNumber);
            clearFields(txtVehicleType);
        }
    }

    private void findDriverIndex() {
        int i = -1;
        for (Driver d : Database.drivers) {
            i++;
            if (d.getname().equals(selectedDriverName)) {
                selectedDriverIndex = i;
                break;
            }
        }
    }

    public void onDeliveryShiftOnAction(ActionEvent actionEvent) {

        if (cmbVehicle.getSelectionModel().getSelectedIndex() == -1 || cmbDriver.getSelectionModel().getSelectedIndex() == -1) {
            // show error labels
            lblSelectVehicleRegex.setText(cmbVehicle.getSelectionModel().getSelectedIndex() == -1 ? "*Required" : "");
            lblDriverRegex.setText(cmbDriver.getSelectionModel().getSelectedIndex() == -1 ? "*Required" : "");
        } else {
            // get current time
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String time = sdf.format(cal.getTime());

            // mark vehicle as parked and update label to on delivery shift
            selectedVehicle.setLeaveTime(time);
            selectedVehicle.setdriverName(selectedDriverName);
            Database.vehicleOnDelivery.add(selectedVehicle);

            // Alert
            new Alert(Alert.AlertType.INFORMATION, "Fine! Have a good journey " + selectedDriverName).show();
            // update buttons
            btnParkVehicle.setDisable(false);
            btnOnDeliveryShift.setDisable(true);

            //set driver
            selectedVehicle.setdriverName(selectedDriverName);
            // mark driver as no available --> because he is now on a delivery
            findDriverIndex();
            Database.drivers.get(selectedDriverIndex).setAvailable(false);

            // remove from in parking array
            Database.vehiclesInPark.remove(selectedVehicle);

            // set slot to vehicle
            Database.slots.get(slot).setAvailable(true);
            selectedVehicle.setSlot(-1);

            // clear data
            clearCmb(cmbVehicle, cmbDriver);
            clearLabels(lblSlotNumber);
            clearFields(txtVehicleType);
        }
    }

    public void btnManagementLogin(ActionEvent actionEvent) throws IOException {
        //setUi(DashboardContext,"LoginForm");
        Database.belowAp = DashboardContext;
        setUiNew("LoginForm", "M a n a g e m e n t");
    }

    public void parkingViewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ParkingViewForm.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("P a r k i n g  V i e w");
        stage.show();
    }
}
