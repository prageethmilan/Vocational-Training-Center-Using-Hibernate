package controller;

import bo.BOFactory;
import bo.custom.DashboardFormBO;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {


    public AnchorPane mainRoot;
    public AnchorPane dashboardContext;
    public Label lblStudentCount;
    public Label lblCourseCount;
    DashboardFormBO dashboardFormBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);


    public void initialize() {
        try {
            lblStudentCount.setText(dashboardFormBO.getStudentCount());
            lblCourseCount.setText(dashboardFormBO.getCoursesCount());
        } catch (Exception e) {
        }

    }

    public void playAnimationMouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playAnimationMouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    public void openHomePageOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/HomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainRoot.getChildren().clear();
        mainRoot.getChildren().add(load);
    }

    public void logOut_OnAction(MouseEvent mouseEvent) throws IOException {
        Stage stg = (Stage) dashboardContext.getScene().getWindow();
        stg.close();
        Parent load = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Sipsewana Vocational Training Institute");
        stage.getIcons().add(new Image("assets/images/logo.png"));
        stage.setResizable(false);
        stage.show();
    }

    public void openStudentForm_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/StudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainRoot.getChildren().clear();
        mainRoot.getChildren().add(load);
    }

    public void openCoursesForm_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/CoursesForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainRoot.getChildren().clear();
        mainRoot.getChildren().add(load);
    }

    public void openRegistrationForm_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegistrationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainRoot.getChildren().clear();
        mainRoot.getChildren().add(load);
    }

    public void openRegistrationList_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegistrationListForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainRoot.getChildren().clear();
        mainRoot.getChildren().add(load);
    }
}
