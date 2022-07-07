package controller;

import database.Database;
import entity.Bus;
import entity.CargoLorry;
import entity.Van;
import entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class TableInParkingFormController {
    public AnchorPane tableInParkingContext;
    public TableView tblTable;
        public TableColumn colVehicleNumber;
        public TableColumn colVehicleType;
        public TableColumn colParkingSlot;
        public TableColumn colParkedTime;

    public void initialize(){
        // introduce columns
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory<>("parkedTime"));
        // load data to table
        loadRelativeVehicles();
        }

    private void loadRelativeVehicles() {
            ObservableList<Vehicle> obList = FXCollections.observableArrayList();
            //add in park vehicles to oblist
            for (Vehicle v : Database.vehiclesInPark) {
                switch (v.getVehicleType()){
                    case "Bus"              : obList.add(new Bus(v.getVehicleNumber(),"Bus",v.getMaxWeight(),v.getNoOfPassengers(),v.getParkedTime(),"",v.getSlot()));break;
                    case "Van"              : obList.add(new Van(v.getVehicleNumber(),"Van",v.getMaxWeight(),v.getNoOfPassengers(),v.getParkedTime(),"",v.getSlot()));break;
                    case "Cargo Lorry"      : obList.add(new CargoLorry(v.getVehicleNumber(),"Cargo Lorry",v.getMaxWeight(),v.getNoOfPassengers(),v.getParkedTime(),"",v.getSlot()));break;
                }
            }
            // add oblist t table
            tblTable.setItems(obList);
    }

}
