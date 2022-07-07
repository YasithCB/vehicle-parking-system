package controller;

import database.Database;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.OnDeliveryTM;

public class TableOnDeliveryFormController {
    public AnchorPane tableOnDeliveryContext;
    public TableView tblTable;
        public TableColumn colVehicleNumber;
        public TableColumn colVehicleType;
        public TableColumn colDriverName;
        public TableColumn colLeftTime;

    public void initialize(){
        // introduce columns
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("vehicleNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory("DriverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory("leftTime"));

        // load data to table
        loadRelativeVehicles();
    }

    private void loadRelativeVehicles() {
        ObservableList<OnDeliveryTM> tbl = FXCollections.observableArrayList();
        for (Vehicle v : Database.vehicleOnDelivery){
            OnDeliveryTM tm = new OnDeliveryTM(v.getVehicleNumber(),v.getVehicleType(),v.getdriverName(),v.getLeaveTime());
            tbl.add(tm);
        }
        tblTable.setItems(tbl);
    }
}
