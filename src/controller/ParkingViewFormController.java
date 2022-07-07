package controller;

import database.Database;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Array;

public class ParkingViewFormController {
    public AnchorPane parkingViewContext;
    public Rectangle rec1;
    public Rectangle rec2;
    public Rectangle rec3;
    public Rectangle rec4;
    public Rectangle rec5;
    public Rectangle rec6;
    public Rectangle rec7;
    public Rectangle rec8;
    public Rectangle rec9;
    public Rectangle rec10;
    public Rectangle rec11;
    public Rectangle rec12;
    public Rectangle rec13;
    public Rectangle rec14;


    public Label lbl1;
    public Label lbl2;
    public Label lbl3;
    public Label lbl4;
    public Label lbl5;
    public Label lbl6;
    public Label lbl7;
    public Label lbl8;
    public Label lbl9;
    public Label lbl10;
    public Label lbl11;
    public Label lbl12;
    public Label lbl13;
    public Label lbl14;

    public void initialize(){
        Label[] lbl = {lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14};
        Rectangle[] rec = {rec1,rec2,rec3,rec4,rec5,rec6,rec7,rec8,rec9,rec10,rec11,rec12,rec13,rec14};

        for (int i = 0; i < 14; i++) {
            if (!Database.slots.get(i+1).isAvailable()){
                lbl[i].setText("Parked");
                rec[i].setStyle("-fx-fill: #F7AFB5;");
            }else {
                lbl[i].setText("Free");
                rec[i].setStyle("-fx-fill: #ecffd8;");
            }
        }

    }

}
