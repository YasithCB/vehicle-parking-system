package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.Database;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Utilities;

import java.io.IOException;

public class LoginController extends Utilities {
    public AnchorPane LoginContext;
    public JFXTextField txtUserName;
    public JFXPasswordField pwPassword;
    // regex labels
    public Label lblUnRequired;
    public Label lblPwRequired;

    /**
     * Login button pressing
     * @param actionEvent
     * @throws IOException
     *
     */
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("") || pwPassword.getText().equals("")){
            lblUnRequired.setText("*Required");
            lblPwRequired.setText("*Required");
        }else {
            //test period use
            close(LoginContext);
            setUi(Database.belowAp,"ManagementForm","M a n a g e m e n t");
            return;

            // load AdminDashboard
            /*if (Database.admins.size() > 0){
                for (Admin a : Database.admins) {
                    if (txtUserName.getText().equals(a.getUserName())){
                        if (pwPassword.getText().equals(a.getPassword())){
                            close(LoginContext);
                            // log to the management window
                            setUi(Database.belowAp,"ManagementForm","M a n a g e m e n t");
                            return;
                        }else {
                            new Alert(Alert.AlertType.ERROR,"Password Incorrect!").show();
                            return;
                        }
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Username doesn't found!").show();
                    }
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"There are no admins registered yet!").show();
            }
*/


        }
    }

    /**
     * when press this, close the login window and only visible the main dashboard
     * @param actionEvent
     * @throws IOException
     */
    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        close(LoginContext);
    }

    public void lblNewManagerOnClicked(MouseEvent mouseEvent) throws IOException {
        setUi(LoginContext,"NewAdminForm","R e g i s t r a t i o n");
    }
}
