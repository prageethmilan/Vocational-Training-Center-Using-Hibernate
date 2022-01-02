package controller;

import bo.BOFactory;
import bo.custom.LogInFormBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.UserDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public AnchorPane loginFormContext;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    LogInFormBO logInFormBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERLOGIN);

    public void openSignupForm_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/SignupForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loginFormContext.getChildren().clear();
        loginFormContext.getChildren().add(load);
    }

    public void loginToMainForm_OnAction(MouseEvent mouseEvent) throws IOException {
        if (!txtUserName.getText().matches("^[A-z0-9]{6,10}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid username.Username must be at least 6-10 characters long", ButtonType.CLOSE).showAndWait();
            txtUserName.requestFocus();
            return;
        } else if (!txtPassword.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid password.Password must be at least 8 characters long including A-Z,a-z,0-9", ButtonType.CLOSE).showAndWait();
            txtPassword.requestFocus();
            return;
        }
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        try {
            UserDTO user = logInFormBO.getUser(username,password);
            if(user != null){
                Stage stg = (Stage) loginFormContext.getScene().getWindow();
                stg.close();
                Parent load = FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(load);
                stage.setScene(scene);
                stage.setTitle("Sipsewana Vocational Training Institute");
                stage.setResizable(false);
                stage.getIcons().add(new Image("assets/images/logo.png"));
                stage.show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Username or password is incorrect.Please try again", ButtonType.CLOSE).showAndWait();
            }
        } catch (Exception e) {

        }
        /*try {
            ArrayList<UserDTO> allUsers = logInFormBO.getAllUsers();
            for (UserDTO user : allUsers) {
                if(username.equals(user.getUserName()) && password.equals(user.getPassword())) {
                    Stage stg = (Stage) loginFormContext.getScene().getWindow();
                    stg.close();
                    Parent load = FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(load);
                    stage.setScene(scene);
                    stage.setTitle("Sipsewana Vocational Training Institute");
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("assets/images/logo.png"));
                    stage.show();
                }
            }
        } catch (Exception e) {

        }*/
    }
}
