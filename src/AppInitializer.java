import entity.Registration;
import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.util.List;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("view/LoginForm.fxml"));
        Scene scene=new Scene(load);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("assets/images/logo.png"));
        primaryStage.setTitle("Sipsewana Vocational Training Center");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
