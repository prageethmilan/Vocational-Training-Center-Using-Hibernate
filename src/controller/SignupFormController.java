package controller;

import bo.BOFactory;
import bo.custom.SignUpFormBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.UserDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SignupFormController {
    public AnchorPane signUpFormContext;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirmPassword;
    SignUpFormBO signUpFormBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERSIGNUP);

    public void backToLoginForm_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) signUpFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void signUpUser_OnAction(MouseEvent mouseEvent) {
        if (!txtFirstName.getText().matches("^[A-z]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid First Name", ButtonType.CLOSE).showAndWait();
            txtFirstName.requestFocus();
            return;
        } else if (!txtLastName.getText().matches("^[A-z]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Last Name", ButtonType.CLOSE).showAndWait();
            txtLastName.requestFocus();
            return;
        } else if (!txtUserName.getText().matches("^[A-z0-9]{6,10}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid username.Username must be at least 6-10 characters long", ButtonType.CLOSE).showAndWait();
            txtUserName.requestFocus();
            return;
        } else if (!txtPassword.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid password.Password must be at least 8 characters long including A-Z,a-z,0-9", ButtonType.CLOSE).showAndWait();
            txtPassword.requestFocus();
            return;
        } else if (txtConfirmPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill Confirm Password Field", ButtonType.CLOSE).showAndWait();
            txtConfirmPassword.requestFocus();
            return;
        }
        if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
            UserDTO userDTO = new UserDTO(txtFirstName.getText(), txtLastName.getText(), txtUserName.getText(), txtPassword.getText());
            try {
                boolean saved= signUpFormBO.SaveUser(userDTO);
                if(saved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Sign up completed.Please login now.",ButtonType.OK).show();
                    txtFirstName.clear();
                    txtLastName.clear();
                    txtUserName.clear();
                    txtPassword.clear();
                    txtConfirmPassword.clear();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try again",ButtonType.CLOSE).show();
                }
            } catch (Exception e) {

            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Password not matched with confirm password",ButtonType.CLOSE).showAndWait();
        }
    }
}
