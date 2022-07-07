package util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utilities {

    /**
     *
     * @param ap
     * @param location
     * @param title
     * @throws IOException
     */
    public void setUi(AnchorPane ap,String location,String title) throws IOException {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.setTitle(title);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Set Form on to a specific AP
     * @param ap --> replacing anchorpane
     * @param location --> Form which want to add
     * @throws IOException
     */
    public void setUiChildren(AnchorPane ap,String location) throws IOException {
        ap.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        ap.getChildren().add(parent);
    }

    /**
     * Make a new window and set a fxml file
     * @param location --> the form which want to set
     * @param title --> the title name of new window
     * @throws IOException
     */
    public void setUiNew(String location, String title) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle(title);
        stage.show();
    }

    /**
     * Get date and all relative details
     * @param date --> date label
     * @param day --> day label
     * @param time --> time label
     */
    public void curDateTime(Label date,Label day,Label time) {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            date.setText(LocalDate.now().toString());
            time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            day.setText(LocalDate.now().getDayOfWeek().toString());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /**
     * close the stage
     * @param ap --> The anchorpane which we want to close
     */
    public void close(AnchorPane ap){
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void clearFields(TextField...param){
        for (TextField txt : param) {
            txt.setText("");
        }
    }

    public void clearCmb(ComboBox...param){
        for (ComboBox cmb : param) {
            cmb.getSelectionModel().select(-1);
        }
    }

    public void clearLabels(Label...param){
        for (Label lbl : param) {
            lbl.setText("");
        }
    }

}
