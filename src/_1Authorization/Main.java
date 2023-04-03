package _1Authorization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        /* Основное окно, окно при запуске приложения - Авторизация. */
        Parent root = FXMLLoader.load(getClass().getResource("/_1Authorization/Authorization.fxml"));

        primaryStage.setTitle("Авторизация/");

        Image Icon = new Image("/assets/book.png");
        primaryStage.getIcons().add(Icon);

        primaryStage.setScene(new Scene(root, 700, 400));

        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
